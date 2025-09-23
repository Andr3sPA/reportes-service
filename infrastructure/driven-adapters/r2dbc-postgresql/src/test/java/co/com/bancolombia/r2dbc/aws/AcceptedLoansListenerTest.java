package co.com.bancolombia.r2dbc.aws;

import co.com.bancolombia.model.Request.AcceptedLoanEvent;
import co.com.bancolombia.r2dbc.entity.ApprovedReport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

class AcceptedLoansListenerTest {
    @Mock
    private ApprovedReportRepository reportRepository;
    @Mock
    private Logger log;
    @InjectMocks
    private AcceptedLoansListener listener;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        listener = new AcceptedLoansListener();
        listener.reportRepository = reportRepository;
    }

    @Test
    void handleMessage_shouldSaveReport_whenMessageIsValid() throws JsonProcessingException {
        AcceptedLoanEvent event = new AcceptedLoanEvent(123L, "APPROVED", "test@mail.com", BigDecimal.TEN);
        String message = new ObjectMapper().writeValueAsString(event);

        listener.handleMessage(message);

        verify(reportRepository).save(any(ApprovedReport.class));
    }

    @Test
    void handleMessage_shouldThrowException_whenMessageIsInvalid() {
        String invalidMessage = "{invalid_json}";
        try {
            listener.handleMessage(invalidMessage);
            assert false : "Exception expected";
        } catch (RuntimeException e) {
            // OK
        }
    }
}

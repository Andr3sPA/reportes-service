package co.com.bancolombia.r2dbc;

import co.com.bancolombia.model.Response.ListReportsRes;
import co.com.bancolombia.r2dbc.entity.ApprovedReport;
import co.com.bancolombia.r2dbc.aws.ApprovedReportRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.reactivecommons.utils.ObjectMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

class ApprovedReportAdapterTest {
    @Mock
    private ReportReactiveRepository reportReactiveRepository;
    @Mock
    private ApprovedReportRepository approvedReportRepository;
    @Mock
    private ObjectMapper mapper;
    @InjectMocks
    private ApprovedReportAdapter adapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        adapter = new ApprovedReportAdapter(reportReactiveRepository, approvedReportRepository, mapper);
    }

    @Test
    void findAll_shouldReturnListReportsRes() {
    List<ApprovedReport> entityReports = List.of(new ApprovedReport());
    Integer count = 1;
    BigDecimal totalAmount = BigDecimal.TEN;
    // Simular el mapeo a modelo del dominio
    co.com.bancolombia.model.ApprovedReport domainReport = new co.com.bancolombia.model.ApprovedReport("metric1", BigDecimal.TEN);
    List<co.com.bancolombia.model.ApprovedReport> domainReports = List.of(domainReport);
    ListReportsRes expected = new ListReportsRes(domainReports, count, totalAmount);

    when(approvedReportRepository.findAll()).thenReturn(entityReports);
    when(approvedReportRepository.count()).thenReturn(count);
    when(approvedReportRepository.sumAllValues()).thenReturn(totalAmount);
    when(mapper.map(entityReports, List.class)).thenReturn(domainReports);

    Mono<ListReportsRes> result = adapter.findAll();

    StepVerifier.create(result)
        .expectNextMatches(res -> res.getReports().equals(domainReports)
            && res.getApprovedLoansCount().equals(count)
            && res.getApprovedLoansTotalAmount().equals(totalAmount))
        .verifyComplete();

    verify(approvedReportRepository).findAll();
    verify(approvedReportRepository).count();
    verify(approvedReportRepository).sumAllValues();
    verify(mapper).map(entityReports, List.class);
    }
}

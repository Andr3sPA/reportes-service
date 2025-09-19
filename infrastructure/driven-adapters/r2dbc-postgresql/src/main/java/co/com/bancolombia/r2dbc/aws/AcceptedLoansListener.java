package co.com.bancolombia.r2dbc.aws;

import co.com.bancolombia.dto.Request.AcceptedLoanEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AcceptedLoansListener {

    @SqsListener("accepted_loans")
    public void handleAcceptedLoan(AcceptedLoanEvent event) {
        log.info("Evento accepted_loans recibido: {}", event);
    }
}


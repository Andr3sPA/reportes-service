package co.com.bancolombia.r2dbc.aws;

import co.com.bancolombia.model.Request.AcceptedLoanEvent;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import com.fasterxml.jackson.databind.ObjectMapper;
import co.com.bancolombia.r2dbc.entity.ApprovedReport;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
@Component
public class AcceptedLoansListener {

    private final ObjectMapper mapper = new ObjectMapper();
    @Autowired
    private ApprovedReportRepository reportRepository;

    @SqsListener("accepted_loans")
    public void handleMessage(String message) {
        log.info("Mensaje recibido de SQS: {}", message);
        try {
            AcceptedLoanEvent event = mapper.readValue(message, AcceptedLoanEvent.class);
            log.info(event.toString());
            ApprovedReport report = new ApprovedReport();
            report.setMetrica(String.valueOf(event.loanId()));
            report.setValor(event.amount());
            reportRepository.save(report);
            log.info("Registro guardado en DynamoDB: {}", report);
        } catch (Exception e) {
            log.error("Error al parsear el mensaje: {}", message, e);
            throw new RuntimeException("Error parsing message", e);
        }
    }
}

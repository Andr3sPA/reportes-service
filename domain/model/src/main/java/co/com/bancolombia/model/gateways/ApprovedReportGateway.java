package co.com.bancolombia.model.gateways;

import co.com.bancolombia.model.ApprovedReport;
import reactor.core.publisher.Flux;

public interface ApprovedReportGateway {
    Flux<ApprovedReport> findAll();
}


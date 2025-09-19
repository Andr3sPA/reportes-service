package co.com.bancolombia.usecase;

import co.com.bancolombia.model.ApprovedReport;
import co.com.bancolombia.model.gateways.ApprovedReportGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class GetApprovedReportsUseCase {
    private final ApprovedReportGateway approvedReportGateway;

    public Flux<ApprovedReport> getAllReports() {
        return approvedReportGateway.findAll();
    }
}


package co.com.bancolombia.usecase;
import co.com.bancolombia.model.ApprovedReport;
import co.com.bancolombia.model.Response.ListReportsRes;
import co.com.bancolombia.model.gateways.ApprovedReportGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetApprovedReportsUseCase {
    private final ApprovedReportGateway approvedReportGateway;
    public Mono<ListReportsRes> getAllReports() {
        return approvedReportGateway.findAll();
    }


}


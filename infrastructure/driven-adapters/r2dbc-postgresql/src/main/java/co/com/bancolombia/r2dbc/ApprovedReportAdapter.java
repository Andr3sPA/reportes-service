package co.com.bancolombia.r2dbc;

import co.com.bancolombia.model.ApprovedReport;
import co.com.bancolombia.model.gateways.ApprovedReportGateway;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Repository
public class ApprovedReportAdapter implements ApprovedReportGateway {
    private final ReportReactiveRepository reportReactiveRepository;
    private final ObjectMapper mapper;
    @Override
    public Flux<ApprovedReport> findAll() {
        return reportReactiveRepository.findAll()
                .map(reportEntity -> mapper.map(reportEntity, ApprovedReport.class));
    }
}

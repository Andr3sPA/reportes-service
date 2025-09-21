package co.com.bancolombia.r2dbc;

import co.com.bancolombia.model.Response.ListReportsRes;
import co.com.bancolombia.model.gateways.ApprovedReportGateway;
import co.com.bancolombia.r2dbc.aws.ApprovedReportRepository;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ApprovedReportAdapter implements ApprovedReportGateway {
    private final ReportReactiveRepository reportReactiveRepository;
    private final ApprovedReportRepository approvedReportRepository;
    private final ObjectMapper mapper;
    @Override
    public Mono<ListReportsRes> findAll() {
        return Mono.fromCallable(() -> {
            var reports = approvedReportRepository.findAll();
            var count = approvedReportRepository.count();
            var totalAmount = approvedReportRepository.sumAllValues();
            return new ListReportsRes(
                    mapper.map(reports, List.class),
                    count,
                    totalAmount
            );
        });
    }
}

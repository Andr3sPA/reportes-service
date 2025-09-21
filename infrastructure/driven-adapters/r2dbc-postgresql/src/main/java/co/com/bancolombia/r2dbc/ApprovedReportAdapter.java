package co.com.bancolombia.r2dbc;

import co.com.bancolombia.model.Response.ListReportsRes;
import co.com.bancolombia.model.gateways.ApprovedReportGateway;
import co.com.bancolombia.r2dbc.aws.ApprovedReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Repository
public class ApprovedReportAdapter implements ApprovedReportGateway {
    private final ReportReactiveRepository reportReactiveRepository;
    private final ApprovedReportRepository approvedReportRepository;
    private final ObjectMapper mapper;
    @Override
    public Mono<ListReportsRes> findAll() {
        return Mono.fromCallable(() -> {
            log.info("Iniciando consulta de reportes aprobados en DynamoDB");
            var reports = approvedReportRepository.findAll();
            log.debug("Reportes obtenidos: {}", reports.size());
            var count = approvedReportRepository.count();
            log.debug("Cantidad de reportes aprobados: {}", count);
            var totalAmount = approvedReportRepository.sumAllValues();
            log.debug("Suma total de valores aprobados: {}", totalAmount);
            var result = new ListReportsRes(
                    mapper.map(reports, List.class),
                    count,
                    totalAmount
            );
            log.info("Respuesta final construida: {}", result);
            return result;
        });
    }
}

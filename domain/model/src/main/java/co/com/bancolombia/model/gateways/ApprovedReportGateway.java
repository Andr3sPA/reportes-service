package co.com.bancolombia.model.gateways;

import co.com.bancolombia.model.Response.ListReportsRes;
import reactor.core.publisher.Mono;
public interface ApprovedReportGateway {
    Mono<ListReportsRes> findAll();
}


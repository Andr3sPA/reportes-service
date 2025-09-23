package co.com.bancolombia.api;

import co.com.bancolombia.api.util.RequestValidator;
import co.com.bancolombia.model.ApprovedReport;
import co.com.bancolombia.usecase.GetApprovedReportsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@RequiredArgsConstructor
public class HandlerReport {
    private static final Logger log = LoggerFactory.getLogger(HandlerReport.class);
    private final RequestValidator requestValidator;
    private final GetApprovedReportsUseCase reportsUseCase;
    public Mono<ServerResponse> getAllReports(ServerRequest serverRequest) {
        log.info("Llamada a getAllReports");
        return reportsUseCase.getAllReports()
                .flatMap(reports -> {
                    log.info("Respuesta OK con reports: {}", reports);
                    return ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(reports);
                })
                .switchIfEmpty(ServerResponse.noContent().build());
    }
    public Mono<ServerResponse> helloWorld(ServerRequest serverRequest) {
        log.info("Llamada a helloWorld");
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .bodyValue("Hola mundo");
    }
}

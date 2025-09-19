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

@Component
@RequiredArgsConstructor
public class HandlerReport {
    private final RequestValidator requestValidator;
    private final GetApprovedReportsUseCase reportsUseCase;
    public Mono<ServerResponse> getAllReports(ServerRequest serverRequest){
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(reportsUseCase.getAllReports(), ApprovedReport.class);
    }
}

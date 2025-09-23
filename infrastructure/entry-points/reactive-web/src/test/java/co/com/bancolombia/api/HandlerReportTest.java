package co.com.bancolombia.api;

import co.com.bancolombia.api.util.RequestValidator;
import co.com.bancolombia.model.ApprovedReport;
import co.com.bancolombia.model.Response.ListReportsRes;
import co.com.bancolombia.usecase.GetApprovedReportsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.*;

class HandlerReportTest {
    @Mock
    private RequestValidator requestValidator;
    @Mock
    private GetApprovedReportsUseCase reportsUseCase;
    @Mock
    private ServerRequest serverRequest;
    @InjectMocks
    private HandlerReport handlerReport;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        handlerReport = new HandlerReport(requestValidator, reportsUseCase);
    }


    @Test
    void getAllReports_shouldReturnOkWithReports() {
        List<ApprovedReport> reports = List.of(new ApprovedReport("metric1", BigDecimal.ONE));
        ListReportsRes listReportsRes = new ListReportsRes(reports, 1, BigDecimal.ONE);
        when(reportsUseCase.getAllReports()).thenReturn(Mono.just(listReportsRes));

        Mono<ServerResponse> response = handlerReport.getAllReports(serverRequest);
        StepVerifier.create(response)
                .expectNextMatches(serverResponse -> serverResponse.statusCode().is2xxSuccessful())
                .verifyComplete();
    }

    @Test
    void getAllReports_shouldReturnNoContent() {
        when(reportsUseCase.getAllReports()).thenReturn(Mono.empty());
        Mono<ServerResponse> response = handlerReport.getAllReports(serverRequest);
        StepVerifier.create(response)
                .expectNextMatches(serverResponse -> serverResponse.statusCode().value() == 204)
                .verifyComplete();
    }

    @Test
    void helloWorld_shouldReturnOk() {
        Mono<ServerResponse> response = handlerReport.helloWorld(serverRequest);
        StepVerifier.create(response)
                .expectNextMatches(serverResponse -> serverResponse.statusCode().is2xxSuccessful())
                .verifyComplete();
    }
}

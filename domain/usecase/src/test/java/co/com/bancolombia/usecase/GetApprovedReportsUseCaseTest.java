package co.com.bancolombia.usecase;

import co.com.bancolombia.model.Response.ListReportsRes;
import co.com.bancolombia.model.gateways.ApprovedReportGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class GetApprovedReportsUseCaseTest {
    private ApprovedReportGateway gateway;
    private GetApprovedReportsUseCase useCase;

    @BeforeEach
    void setUp() {
        gateway = Mockito.mock(ApprovedReportGateway.class);
        useCase = new GetApprovedReportsUseCase(gateway);
    }

    @Test
    void getAllReports_shouldReturnMonoListReportsRes() {
        ListReportsRes response = new ListReportsRes(java.util.Collections.emptyList(), 0, java.math.BigDecimal.ZERO);
        Mockito.when(gateway.findAll()).thenReturn(Mono.just(response));

        StepVerifier.create(useCase.getAllReports())
                .expectNext(response)
                .verifyComplete();
        Mockito.verify(gateway).findAll();
    }

    @Test
    void getAllReports_shouldReturnEmptyMonoWhenGatewayReturnsEmpty() {
        Mockito.when(gateway.findAll()).thenReturn(Mono.empty());

        StepVerifier.create(useCase.getAllReports())
                .verifyComplete();
        Mockito.verify(gateway).findAll();
    }
}

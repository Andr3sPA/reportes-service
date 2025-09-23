package co.com.bancolombia.api;

import co.com.bancolombia.api.config.ReportsPath;
import co.com.bancolombia.api.filter.ApiKeyAuthFilter;
import co.com.bancolombia.api.filter.GlobalExceptionFilter;
import co.com.bancolombia.model.ApprovedReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class RouterRestTest {
    @Mock
    private ApiKeyAuthFilter apiKeyAuthFilter;
    @Mock
    private ReportsPath reportsPath;
    @Mock
    private GlobalExceptionFilter globalExceptionFilter;
    @Mock
    private HandlerReport handlerReport;
    @InjectMocks
    private RouterRest routerRest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        routerRest = new RouterRest(apiKeyAuthFilter, reportsPath, globalExceptionFilter);
        // Mock para evitar IllegalArgumentException por patrón nulo
        org.mockito.Mockito.when(reportsPath.getReports()).thenReturn("/api/v1/reportes");
    }

    @Test
    void routerFunction_shouldReturnRouterFunction() {
        RouterFunction<ServerResponse> function = routerRest.routerFunction(handlerReport);
        assertThat(function).isNotNull();
    }
}

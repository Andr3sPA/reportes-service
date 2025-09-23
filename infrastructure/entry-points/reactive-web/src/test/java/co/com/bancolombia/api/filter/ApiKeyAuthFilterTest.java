package co.com.bancolombia.api.filter;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.HandlerFunction;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

class ApiKeyAuthFilterTest {
    @Test
    void filterShouldCallNextWhenApiKeyHeaderPresent() {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter();
        // Inyectar el apiKey esperado
        String expectedApiKey = "test-key";
        setApiKey(filter, expectedApiKey);

        ServerRequest request = mock(ServerRequest.class);
        ServerRequest.Headers headers = mock(ServerRequest.Headers.class);
        when(request.headers()).thenReturn(headers);
        when(headers.firstHeader("X-API-KEY")).thenReturn(expectedApiKey);
        HandlerFunction<ServerResponse> next = mock(HandlerFunction.class);
        ServerResponse response = mock(ServerResponse.class);
        when(next.handle(request)).thenReturn(Mono.just(response));

        Mono<ServerResponse> result = filter.filter(request, next);
        assertEquals(response, result.block());
        verify(next).handle(request);
    }

    @Test
    void filterShouldReturnUnauthorizedWhenApiKeyHeaderInvalid() {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter();
        String expectedApiKey = "test-key";
        setApiKey(filter, expectedApiKey);

        ServerRequest request = mock(ServerRequest.class);
        ServerRequest.Headers headers = mock(ServerRequest.Headers.class);
        when(request.headers()).thenReturn(headers);
        when(headers.firstHeader("X-API-KEY")).thenReturn("invalid-key");
        HandlerFunction<ServerResponse> next = mock(HandlerFunction.class);

        Mono<ServerResponse> result = filter.filter(request, next);
        ServerResponse response = result.block();
        assertNotNull(response);
        assertEquals(401, response.statusCode().value());
    }

    // Utilidad para inyectar el apiKey en el filtro
    private void setApiKey(ApiKeyAuthFilter filter, String apiKey) {
        try {
            java.lang.reflect.Field field = ApiKeyAuthFilter.class.getDeclaredField("apiKey");
            field.setAccessible(true);
            field.set(filter, apiKey);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

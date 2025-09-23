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
        String expectedApiKey = "test-key";
        setApiKey(filter, expectedApiKey);
        ServerRequest request = mock(ServerRequest.class);
        ServerRequest.Headers headers = mock(ServerRequest.Headers.class);
        when(request.headers()).thenReturn(headers);
        when(headers.firstHeader("X-API-KEY")).thenReturn(expectedApiKey);
        when(request.path()).thenReturn("/otro-endpoint");
        HandlerFunction<ServerResponse> next = mock(HandlerFunction.class);
        ServerResponse response = mock(ServerResponse.class);
        when(next.handle(request)).thenReturn(Mono.just(response));
        Mono<ServerResponse> result = filter.filter(request, next);
        assertEquals(response, result.block());
        verify(next).handle(request);
    }

    @Test
<<<<<<< HEAD
    void filterShouldReturnUnauthorizedWhenApiKeyHeaderInvalid() {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter();
        String expectedApiKey = "test-key";
        setApiKey(filter, expectedApiKey);

=======
    void filterShouldAllowAccessToHolaAndReportesWithoutApiKey() {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter();
        setApiKey(filter, "test-key");
        ServerRequest request = mock(ServerRequest.class);
        when(request.path()).thenReturn("/api/v1/hola");
        HandlerFunction<ServerResponse> next = mock(HandlerFunction.class);
        ServerResponse response = mock(ServerResponse.class);
        when(next.handle(request)).thenReturn(Mono.just(response));
        Mono<ServerResponse> result = filter.filter(request, next);
        assertEquals(response, result.block());
        verify(next).handle(request);
    }

    @Test
    void filterShouldReturnUnauthorizedWhenApiKeyIsInvalid() {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter();
        setApiKey(filter, "test-key");
>>>>>>> 7e7fe8715312ab7522e89d71a64708ac6901f659
        ServerRequest request = mock(ServerRequest.class);
        ServerRequest.Headers headers = mock(ServerRequest.Headers.class);
        when(request.headers()).thenReturn(headers);
        when(headers.firstHeader("X-API-KEY")).thenReturn("invalid-key");
<<<<<<< HEAD
        HandlerFunction<ServerResponse> next = mock(HandlerFunction.class);

        Mono<ServerResponse> result = filter.filter(request, next);
        ServerResponse response = result.block();
        assertNotNull(response);
        assertEquals(401, response.statusCode().value());
    }

=======
        when(request.path()).thenReturn("/otro-endpoint");
        HandlerFunction<ServerResponse> next = mock(HandlerFunction.class);
        Mono<ServerResponse> result = filter.filter(request, next);
        ServerResponse response = result.block();
        assertEquals(401, response.statusCode().value());
    }

    @Test
    void filterShouldHandleNullPathGracefully() {
        ApiKeyAuthFilter filter = new ApiKeyAuthFilter();
        setApiKey(filter, "test-key");
        ServerRequest request = mock(ServerRequest.class);
        when(request.path()).thenReturn(null);
        ServerRequest.Headers headers = mock(ServerRequest.Headers.class);
        when(request.headers()).thenReturn(headers);
        when(headers.firstHeader("X-API-KEY")).thenReturn("test-key");
        HandlerFunction<ServerResponse> next = mock(HandlerFunction.class);
        ServerResponse response = mock(ServerResponse.class);
        when(next.handle(request)).thenReturn(Mono.just(response));
        Mono<ServerResponse> result = filter.filter(request, next);
        assertEquals(response, result.block());
    }

>>>>>>> 7e7fe8715312ab7522e89d71a64708ac6901f659
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

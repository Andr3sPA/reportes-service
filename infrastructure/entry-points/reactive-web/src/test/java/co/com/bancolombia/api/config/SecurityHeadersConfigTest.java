package co.com.bancolombia.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SecurityHeadersConfigTest {
    @Test
    void filterShouldCallNext() {
        SecurityHeadersConfig config = new SecurityHeadersConfig();
        ServerWebExchange exchange = mock(ServerWebExchange.class);
        WebFilterChain chain = mock(WebFilterChain.class);
        org.springframework.http.server.reactive.ServerHttpResponse response = mock(org.springframework.http.server.reactive.ServerHttpResponse.class);
        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
        when(exchange.getResponse()).thenReturn(response);
        when(response.getHeaders()).thenReturn(headers);
        when(chain.filter(exchange)).thenReturn(Mono.empty());
        assertNotNull(config.filter(exchange, chain));
    }
}

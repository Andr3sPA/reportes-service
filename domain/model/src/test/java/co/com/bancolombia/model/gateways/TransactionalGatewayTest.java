package co.com.bancolombia.model.gateways;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class TransactionalGatewayTest {
    @Test
    void testTransactionalDefault() {
        TransactionalGateway gateway = new TransactionalGateway() {
            @Override
            public <T> Mono<T> transactional(Mono<T> action) {
                return action;
            }
        };
        Mono<String> result = gateway.transactional(Mono.just("ok"));
        assertEquals("ok", result.block());
    }
}

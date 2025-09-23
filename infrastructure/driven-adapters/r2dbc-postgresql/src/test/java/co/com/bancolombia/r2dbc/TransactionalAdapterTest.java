package co.com.bancolombia.r2dbc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.transaction.reactive.TransactionalOperator;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

class TransactionalAdapterTest {
    @Mock
    private TransactionalOperator transactionalOperator;
    @InjectMocks
    private TransactionalAdapter transactionalAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionalAdapter = new TransactionalAdapter(transactionalOperator);
    }

    @Test
    void transactional_shouldCompleteSuccessfully() {
        Mono<String> action = Mono.just("success");
        when(transactionalOperator.transactional(action)).thenReturn(action);

        StepVerifier.create(transactionalAdapter.transactional(action))
                .expectNext("success")
                .verifyComplete();

        verify(transactionalOperator).transactional(action);
    }

    @Test
    void transactional_shouldHandleError() {
        Mono<String> action = Mono.error(new RuntimeException("fail"));
        when(transactionalOperator.transactional(action)).thenReturn(action);

        StepVerifier.create(transactionalAdapter.transactional(action))
                .expectError(RuntimeException.class)
                .verify();

        verify(transactionalOperator).transactional(action);
    }
}

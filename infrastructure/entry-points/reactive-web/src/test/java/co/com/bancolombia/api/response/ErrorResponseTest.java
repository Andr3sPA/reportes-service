package co.com.bancolombia.api.response;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ErrorResponseTest {
    @Test
    void gettersAndSettersWork() {
        Instant now = Instant.now();
        ErrorResponse error = ErrorResponse.builder()
                .errorCode("E001")
                .tittle("Error")
                .message("Mensaje de error")
                .errors(Collections.singletonList("detalle"))
                .timestamp(now)
                .build();
        assertEquals("E001", error.getErrorCode());
        assertEquals("Error", error.getTittle());
        assertEquals("Mensaje de error", error.getMessage());
        assertEquals(1, error.getErrors().size());
        assertEquals(now, error.getTimestamp());
    }
}

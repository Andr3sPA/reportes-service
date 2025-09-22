package co.com.bancolombia.exception;

import org.junit.jupiter.api.Test;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BaseExceptionTest {
    @Test
    void testConstructorAndGetters() {
        String code = "CODE";
        String title = "Title";
        String detail = "Detail";
        List<String> errors = List.of("error1", "error2");
        int status = 500;
        BaseException ex = new BaseException(code, title, detail, errors, status);
        assertEquals(code, ex.getErrorCode());
        assertEquals(title, ex.getTitle());
        assertEquals(detail, ex.getMessage());
        assertEquals(errors, ex.getErrors());
        assertEquals(status, ex.getStatus());
        assertNotNull(ex.getTimestamp());
        assertTrue(ex.getTimestamp().isBefore(Instant.now().plusSeconds(1)));
    }
}

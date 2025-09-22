package co.com.bancolombia.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoanApplicationNotFoundExceptionTest {
    @Test
    void testConstructorAndFields() {
        Long id = 123L;
        LoanApplicationNotFoundException ex = new LoanApplicationNotFoundException(id);
        assertEquals("LOAN_APPLICATION_NOT_FOUND", ex.getCode());
        assertEquals("Solicitud de préstamo no encontrada", ex.getTitle());
        assertEquals("No existe la solicitud con id: 123", ex.getDetail());
        assertEquals(404, ex.getHttpStatus());
        assertNotNull(ex.getMessages());
        assertTrue(ex.getMessages().contains("No existe la solicitud con id: 123"));
    }
}

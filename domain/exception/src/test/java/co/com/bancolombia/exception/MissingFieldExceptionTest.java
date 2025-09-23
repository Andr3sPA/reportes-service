package co.com.bancolombia.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MissingFieldExceptionTest {
    @Test
    void testConstructorAndFields() {
        String fieldName = "nombre";
        MissingFieldException ex = new MissingFieldException(fieldName);
        assertEquals("MISSING_FIELD", ex.getErrorCode());
        assertEquals("Campo requerido faltante", ex.getTitle());
        assertEquals("El campo 'nombre' es obligatorio", ex.getMessage());
        assertEquals(400, ex.getStatus());
        assertNotNull(ex.getErrors());
        assertTrue(ex.getErrors().contains("Falta el campo: nombre"));
    }
}

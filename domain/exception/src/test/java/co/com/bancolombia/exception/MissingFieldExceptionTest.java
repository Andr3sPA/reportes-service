package co.com.bancolombia.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MissingFieldExceptionTest {
    @Test
    void testConstructorAndFields() {
        String fieldName = "nombre";
        MissingFieldException ex = new MissingFieldException(fieldName);
        assertEquals("MISSING_FIELD", ex.getCode());
        assertEquals("Campo requerido faltante", ex.getTitle());
        assertEquals("El campo 'nombre' es obligatorio", ex.getDetail());
        assertEquals(400, ex.getHttpStatus());
        assertNotNull(ex.getMessages());
        assertTrue(ex.getMessages().contains("Falta el campo: nombre"));
    }
}

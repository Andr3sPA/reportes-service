package co.com.bancolombia.exception;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmailMismatchExceptionTest {
    @Test
    void testConstructorAndFields() {
        String requestEmail = "a@b.com";
        String sessionEmail = "b@b.com";
        EmailMismatchException ex = new EmailMismatchException(requestEmail, sessionEmail);
        assertEquals("EMAIL_MISMATCH", ex.getCode());
        assertEquals("Correo electrónico inválido", ex.getTitle());
        assertEquals("El correo del request (a@b.com) no coincide con el de la sesión (b@b.com)", ex.getDetail());
        assertEquals(400, ex.getHttpStatus());
        assertNotNull(ex.getMessages());
        assertTrue(ex.getMessages().get(0).contains("Verifica que el correo enviado"));
    }
}

package co.com.bancolombia;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class MainApplicationTest {
    @Test
    void constructorCoversClass() {
        new MainApplication();
    }

    @Test
    @Disabled("Requiere variables de entorno de Spring Boot para ejecutarse correctamente")
    void mainMethodCoversRun() {
        // Ejecuta el método main y verifica que no lance excepción
        assertDoesNotThrow(() -> MainApplication.main(new String[]{}));
    }
}

package co.com.bancolombia.api.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class CorsConfigTest {
    @Test
    void canInstantiateCorsConfig() {
        CorsConfig config = new CorsConfig();
        assertNotNull(config);
    }
}

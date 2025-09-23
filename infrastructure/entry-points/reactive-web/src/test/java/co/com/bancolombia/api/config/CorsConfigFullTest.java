package co.com.bancolombia.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.cors.reactive.CorsWebFilter;

import static org.junit.jupiter.api.Assertions.*;

class CorsConfigFullTest {
    @Test
    void corsWebFilter_createsFilterWithAllowedOrigins() {
        CorsConfig config = new CorsConfig();
        String origins = "http://localhost,http://example.com";
        CorsWebFilter filter = config.corsWebFilter(origins);
        assertNotNull(filter);
        // Optionally, use ReflectionTestUtils to inspect the filter's config if needed
    }
}

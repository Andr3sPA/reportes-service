package co.com.bancolombia.api.config;

import org.junit.jupiter.api.Test;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class SqsConfigTest {
    @Test
    void jacksonMessageConverterReturnsInstance() {
        SqsConfig config = new SqsConfig();
        MappingJackson2MessageConverter converter = config.jacksonMessageConverter();
        assertNotNull(converter);
    }
}

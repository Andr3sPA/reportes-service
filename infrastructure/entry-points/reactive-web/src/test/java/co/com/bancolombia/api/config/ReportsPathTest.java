package co.com.bancolombia.api.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ReportsPathTest {
    @Test
    void canInstantiateReportsPath() {
        ReportsPath path = new ReportsPath();
        assertNotNull(path);
    }
}

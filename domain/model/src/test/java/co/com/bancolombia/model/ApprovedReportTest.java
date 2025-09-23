package co.com.bancolombia.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ApprovedReportTest {
    @Test
    void testNoArgsConstructorAndSetters() {
        ApprovedReport report = new ApprovedReport();
        report.setMetric("metric");
        report.setValue(BigDecimal.TEN);
        assertEquals("metric", report.getMetric());
        assertEquals(BigDecimal.TEN, report.getValue());
    }

    @Test
    void testAllArgsConstructor() {
        ApprovedReport report = new ApprovedReport("metric2", BigDecimal.ONE);
        assertEquals("metric2", report.getMetric());
        assertEquals(BigDecimal.ONE, report.getValue());
    }

    @Test
    void testEqualsAndHashCode() {
        ApprovedReport r1 = new ApprovedReport("m", BigDecimal.ZERO);
        ApprovedReport r2 = new ApprovedReport("m", BigDecimal.ZERO);
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToString() {
        ApprovedReport report = new ApprovedReport("m", BigDecimal.ZERO);
        assertTrue(report.toString().contains("m"));
    }
}

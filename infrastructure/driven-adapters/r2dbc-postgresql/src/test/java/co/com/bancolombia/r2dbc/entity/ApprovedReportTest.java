package co.com.bancolombia.r2dbc.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ApprovedReportTest {
    @Test
    void gettersAndSettersShouldWork() {
        ApprovedReport report = new ApprovedReport();
        report.setMetrica("metric1");
        report.setValor(BigDecimal.TEN);
        assertEquals("metric1", report.getMetrica());
        assertEquals(BigDecimal.TEN, report.getValor());
    }

    @Test
    void partitionKeyGetterShouldReturnMetrica() {
        ApprovedReport report = new ApprovedReport();
        report.setMetrica("partitionKey");
        assertEquals("partitionKey", report.getMetrica());
    }
}

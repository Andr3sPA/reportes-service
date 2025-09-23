package co.com.bancolombia.model.Response;

import co.com.bancolombia.model.ApprovedReport;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ListReportsResTest {
    @Test
    void testAllArgsConstructorAndGettersSetters() {
        ApprovedReport report = new ApprovedReport("metric", BigDecimal.ONE);
        ListReportsRes res = new ListReportsRes(List.of(report), 2, BigDecimal.TEN);
        assertEquals(1, res.getReports().size());
        assertEquals(2, res.getApprovedLoansCount());
        assertEquals(BigDecimal.TEN, res.getApprovedLoansTotalAmount());
        // Setters
        res.setApprovedLoansCount(3);
        res.setApprovedLoansTotalAmount(BigDecimal.ZERO);
        assertEquals(3, res.getApprovedLoansCount());
        assertEquals(BigDecimal.ZERO, res.getApprovedLoansTotalAmount());
    }
}

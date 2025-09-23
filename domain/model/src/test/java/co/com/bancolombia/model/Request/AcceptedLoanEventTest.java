package co.com.bancolombia.model.Request;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class AcceptedLoanEventTest {
    @Test
    void testRecordFields() {
        Long loanId = 1L;
        String newState = "APPROVED";
        String email = "test@mail.com";
        BigDecimal amount = BigDecimal.TEN;
        AcceptedLoanEvent event = new AcceptedLoanEvent(loanId, newState, email, amount);
        assertEquals(loanId, event.loanId());
        assertEquals(newState, event.newState());
        assertEquals(email, event.email());
        assertEquals(amount, event.amount());
    }

    @Test
    void testEqualsAndHashCode() {
        AcceptedLoanEvent e1 = new AcceptedLoanEvent(1L, "S", "a@b.com", BigDecimal.ONE);
        AcceptedLoanEvent e2 = new AcceptedLoanEvent(1L, "S", "a@b.com", BigDecimal.ONE);
        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
    }

    @Test
    void testToString() {
        AcceptedLoanEvent event = new AcceptedLoanEvent(2L, "R", "b@b.com", BigDecimal.ZERO);
        assertTrue(event.toString().contains("R"));
    }
}

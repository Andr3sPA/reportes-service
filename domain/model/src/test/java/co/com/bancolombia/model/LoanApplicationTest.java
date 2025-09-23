package co.com.bancolombia.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoanApplicationTest {
    @Test
    void testNoArgsConstructorAndSetters() {
        LoanApplication loan = new LoanApplication();
        loan.setId("id");
        loan.setEmail("mail@test.com");
        loan.setAmount(100.5);
        loan.setStatus("APPROVED");
        assertEquals("id", loan.getId());
        assertEquals("mail@test.com", loan.getEmail());
        assertEquals(100.5, loan.getAmount());
        assertEquals("APPROVED", loan.getStatus());
    }

    @Test
    void testAllArgsConstructor() {
        LoanApplication loan = new LoanApplication("id2", "mail2@test.com", 200.0, "REJECTED");
        assertEquals("id2", loan.getId());
        assertEquals("mail2@test.com", loan.getEmail());
        assertEquals(200.0, loan.getAmount());
        assertEquals("REJECTED", loan.getStatus());
    }

    @Test
    void testEqualsAndHashCode() {
        LoanApplication l1 = new LoanApplication("id", "mail", 1.0, "S");
        LoanApplication l2 = new LoanApplication("id", "mail", 1.0, "S");
        assertEquals(l1, l2);
        assertEquals(l1.hashCode(), l2.hashCode());
    }

    @Test
    void testToString() {
        LoanApplication loan = new LoanApplication("id", "mail", 1.0, "S");
        assertTrue(loan.toString().contains("id"));
    }
}

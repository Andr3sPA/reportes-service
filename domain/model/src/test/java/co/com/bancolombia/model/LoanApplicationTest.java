
package co.com.bancolombia.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class LoanApplicationTest {
	@Test
	void testConstructorAndGetters() {
		LoanApplication app = new LoanApplication("1", "test@email.com", 10000.0, "APPROVED");
		assertEquals("1", app.getId());
		assertEquals("test@email.com", app.getEmail());
		assertEquals(10000.0, app.getAmount());
		assertEquals("APPROVED", app.getStatus());
	}

	@Test
	void testSetters() {
		LoanApplication app = new LoanApplication();
		app.setId("2");
		app.setEmail("user@email.com");
		app.setAmount(5000.0);
		app.setStatus("PENDING");
		assertEquals("2", app.getId());
		assertEquals("user@email.com", app.getEmail());
		assertEquals(5000.0, app.getAmount());
		assertEquals("PENDING", app.getStatus());
	}

	@Test
	void testNoArgsConstructor() {
		LoanApplication app = new LoanApplication();
		assertNull(app.getId());
		assertNull(app.getEmail());
		assertEquals(0.0, app.getAmount());
		assertNull(app.getStatus());
	}
}

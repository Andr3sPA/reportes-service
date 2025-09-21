package co.com.bancolombia.model.Request;

import java.math.BigDecimal;

public record AcceptedLoanEvent(Long loanId, String newState, String email, BigDecimal amount) { }

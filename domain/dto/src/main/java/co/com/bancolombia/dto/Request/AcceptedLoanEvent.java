package co.com.bancolombia.dto.Request;

public record AcceptedLoanEvent(Long loanId, String newState, String email) { }

package co.com.bancolombia.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {
    private String id;
    private String email;
    private double amount;
    private String status;
}

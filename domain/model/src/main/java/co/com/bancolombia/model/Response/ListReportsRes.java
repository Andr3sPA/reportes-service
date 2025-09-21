package co.com.bancolombia.model.Response;
import co.com.bancolombia.model.ApprovedReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
public class ListReportsRes {
    List<ApprovedReport> reports;
    Integer approvedLoansCount;
    BigDecimal approvedLoansTotalAmount;
}

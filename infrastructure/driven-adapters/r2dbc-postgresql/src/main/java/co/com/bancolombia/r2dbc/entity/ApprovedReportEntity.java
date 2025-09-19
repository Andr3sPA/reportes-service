package co.com.bancolombia.r2dbc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

@Table("approved_report")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovedReportEntity {
    @Column("metric")
    private String metric;
    @Column("value")
    private BigDecimal value;
}

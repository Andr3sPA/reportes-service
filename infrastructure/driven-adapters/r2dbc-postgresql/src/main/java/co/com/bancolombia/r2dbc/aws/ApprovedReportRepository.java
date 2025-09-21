package co.com.bancolombia.r2dbc.aws;

import co.com.bancolombia.r2dbc.entity.ApprovedReport;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ApprovedReportRepository {
    private final DynamoDbEnhancedClient dynamoDbEnhancedClient;

    public void save(ApprovedReport report) {
        DynamoDbTable<ApprovedReport> table = dynamoDbEnhancedClient.table("reporte_aprobados", TableSchema.fromBean(ApprovedReport.class));
        table.putItem(report);
    }
    public List<ApprovedReport> findAll() {
        DynamoDbTable<ApprovedReport> table = dynamoDbEnhancedClient.table("reporte_aprobados", TableSchema.fromBean(ApprovedReport.class));
        return table.scan().items().stream().toList();
    }
    public List<ApprovedReport> findByMetrica(String metrica) {
        DynamoDbTable<ApprovedReport> table = dynamoDbEnhancedClient.table("reporte_aprobados", TableSchema.fromBean(ApprovedReport.class));
        return table.scan().items().stream().filter(report -> report.getMetrica().equals(metrica)).toList();
    }
    public Integer count() {
        DynamoDbTable<ApprovedReport> table = dynamoDbEnhancedClient.table("reporte_aprobados", TableSchema.fromBean(ApprovedReport.class));
        return (int) table.scan().items().stream().count();
    }
    public java.math.BigDecimal sumAllValues() {
        DynamoDbTable<ApprovedReport> table = dynamoDbEnhancedClient.table("reporte_aprobados", TableSchema.fromBean(ApprovedReport.class));
        return table.scan().items().stream()
                .map(ApprovedReport::getValor)
                .filter(java.util.Objects::nonNull)
                .reduce(java.math.BigDecimal.ZERO, java.math.BigDecimal::add);
    }
}

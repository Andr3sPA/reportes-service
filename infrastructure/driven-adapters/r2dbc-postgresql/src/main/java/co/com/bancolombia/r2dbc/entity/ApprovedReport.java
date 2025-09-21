package co.com.bancolombia.r2dbc.entity;

import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;

@Getter
@Setter
@DynamoDbBean
@TableName(name = "reporte_aprobados")
public class ApprovedReport {
    private String metrica;
    private BigDecimal valor;

    @DynamoDbPartitionKey
    public String getMetrica() {
        return metrica;
    }
}

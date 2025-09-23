package co.com.bancolombia.r2dbc.aws;

import co.com.bancolombia.r2dbc.entity.ApprovedReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.enhanced.dynamodb.model.PageIterable;
import software.amazon.awssdk.core.pagination.sync.SdkIterable;


import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ApprovedReportRepositoryTest {
    private DynamoDbEnhancedClient dynamoDbEnhancedClient;
    private DynamoDbTable<ApprovedReport> table;
    private ApprovedReportRepository repository;

    @BeforeEach
    void setUp() {
        dynamoDbEnhancedClient = mock(DynamoDbEnhancedClient.class);
        table = mock(DynamoDbTable.class);
        when(dynamoDbEnhancedClient.table(anyString(), any(TableSchema.class))).thenReturn(table);
        repository = new ApprovedReportRepository(dynamoDbEnhancedClient);
    }

    @Test
    void testSave() {
        ApprovedReport report = mock(ApprovedReport.class);
        repository.save(report);
        verify(table).putItem(report);
    }

    @Test
    void testFindAll() {
        ApprovedReport report = mock(ApprovedReport.class);
    List<ApprovedReport> list = List.of(report);
    SdkIterable<ApprovedReport> sdkIterable = new SdkIterable<>() {
        @Override
        public Iterator<ApprovedReport> iterator() { return list.iterator(); }
        @Override
        public Spliterator<ApprovedReport> spliterator() { return list.spliterator(); }
    };
    PageIterable<ApprovedReport> scanResult = mock(PageIterable.class);
    when(scanResult.items()).thenReturn(sdkIterable);
        when(table.scan()).thenReturn(scanResult);
        List<ApprovedReport> result = repository.findAll();
        assertNotNull(result);
    }

    @Test
    void testFindByMetrica() {
        ApprovedReport report = mock(ApprovedReport.class);
        when(report.getMetrica()).thenReturn("metrica");
        List<ApprovedReport> list = List.of(report);
        SdkIterable<ApprovedReport> sdkIterable = new SdkIterable<>() {
            @Override
            public Iterator<ApprovedReport> iterator() { return list.iterator(); }
            @Override
            public Spliterator<ApprovedReport> spliterator() { return list.spliterator(); }
        };
        PageIterable<ApprovedReport> scanResult = mock(PageIterable.class);
        when(scanResult.items()).thenReturn(sdkIterable);
        when(table.scan()).thenReturn(scanResult);
        List<ApprovedReport> result = repository.findByMetrica("metrica");
        assertEquals(1, result.size());
        assertEquals("metrica", result.get(0).getMetrica());
    }

    @Test
    void testCount() {
        ApprovedReport report1 = mock(ApprovedReport.class);
        ApprovedReport report2 = mock(ApprovedReport.class);
        List<ApprovedReport> list = List.of(report1, report2);
        SdkIterable<ApprovedReport> sdkIterable = new SdkIterable<>() {
            @Override
            public Iterator<ApprovedReport> iterator() { return list.iterator(); }
            @Override
            public Spliterator<ApprovedReport> spliterator() { return list.spliterator(); }
        };
        PageIterable<ApprovedReport> scanResult = mock(PageIterable.class);
        when(scanResult.items()).thenReturn(sdkIterable);
        when(table.scan()).thenReturn(scanResult);
        Integer count = repository.count();
        assertEquals(2, count);
    }

    @Test
    void testSumAllValues() {
        ApprovedReport report1 = mock(ApprovedReport.class);
        ApprovedReport report2 = mock(ApprovedReport.class);
        when(report1.getValor()).thenReturn(BigDecimal.TEN);
        when(report2.getValor()).thenReturn(BigDecimal.ONE);
        List<ApprovedReport> list = List.of(report1, report2);
        SdkIterable<ApprovedReport> sdkIterable = new SdkIterable<>() {
            @Override
            public Iterator<ApprovedReport> iterator() { return list.iterator(); }
            @Override
            public Spliterator<ApprovedReport> spliterator() { return list.spliterator(); }
        };
        PageIterable<ApprovedReport> scanResult = mock(PageIterable.class);
        when(scanResult.items()).thenReturn(sdkIterable);
        when(table.scan()).thenReturn(scanResult);
        BigDecimal sum = repository.sumAllValues();
        assertEquals(BigDecimal.valueOf(11), sum);
    }
}



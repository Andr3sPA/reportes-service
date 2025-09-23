package co.com.bancolombia.r2dbc.aws;

import co.com.bancolombia.r2dbc.entity.TableName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@TableName(name = "test_table")
class DummyEntity {}

class CustomTableNameResolverTest {
    @Test
    void resolve_shouldReturnTableNameFromAnnotation() {
        CustomTableNameResolver resolver = new CustomTableNameResolver();
        String tableName = resolver.resolve(DummyEntity.class);
        assertEquals("test_table", tableName);
    }

    @Test
    void resolve_shouldThrowExceptionIfNoAnnotation() {
        class NoAnnotation {}
        CustomTableNameResolver resolver = new CustomTableNameResolver();
        assertThrows(NullPointerException.class, () -> resolver.resolve(NoAnnotation.class));
    }
}

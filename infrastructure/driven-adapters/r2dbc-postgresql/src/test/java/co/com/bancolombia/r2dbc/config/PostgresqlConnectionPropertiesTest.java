package co.com.bancolombia.r2dbc.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostgresqlConnectionPropertiesTest {
    @Test
    void recordShouldStoreAndReturnValues() {
        PostgresqlConnectionProperties props = new PostgresqlConnectionProperties(
                "localhost", 5432, "testdb", "public", "user", "pass", "ssl=true");
        assertEquals("localhost", props.host());
        assertEquals(5432, props.port());
        assertEquals("testdb", props.database());
        assertEquals("public", props.schema());
        assertEquals("user", props.username());
        assertEquals("pass", props.password());
        assertEquals("ssl=true", props.options());
    }
}

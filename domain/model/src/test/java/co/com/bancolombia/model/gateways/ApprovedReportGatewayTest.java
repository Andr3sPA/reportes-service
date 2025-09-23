package co.com.bancolombia.model.gateways;

import co.com.bancolombia.model.Response.ListReportsRes;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

class ApprovedReportGatewayTest {
    @Test
    void testFindAllDefault() {
        ApprovedReportGateway gateway = new ApprovedReportGateway() {
            @Override
            public Mono<ListReportsRes> findAll() {
                return Mono.empty();
            }
        };
        assertNotNull(gateway.findAll());
        assertTrue(gateway.findAll().blockOptional().isEmpty());
    }
}

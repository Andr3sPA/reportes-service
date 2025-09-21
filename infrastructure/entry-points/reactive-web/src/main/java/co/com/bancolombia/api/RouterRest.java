package co.com.bancolombia.api;

import co.com.bancolombia.api.config.ReportsPath;
import co.com.bancolombia.api.filter.ApiKeyAuthFilter;
import co.com.bancolombia.api.filter.GlobalExceptionFilter;
import co.com.bancolombia.model.ApprovedReport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import lombok.RequiredArgsConstructor;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ReportsPath.class)
public class RouterRest {
    private final ApiKeyAuthFilter apiKeyAuthFilter;
    private final ReportsPath reportsPath;
    private final GlobalExceptionFilter globalExceptionFilter;

    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/reportes", // Actualiza esto si es necesario
                    produces = { MediaType.APPLICATION_JSON_VALUE },
                    method = RequestMethod.GET, // Cambiado a GET
                    beanClass = ApprovedReport.class,
                    beanMethod = "getAllReports",
                    operation = @Operation(
                            operationId = "getAllReports",
                            summary = "Obtener reportes aprobados",
                            description = "Obtiene todos los reportes de préstamos aprobados",
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Reportes obtenidos correctamente",
                                            content = @Content(
                                                    schema = @Schema(implementation = ApprovedReport.class)
                                            )
                                    ),
                                    @ApiResponse(responseCode = "400", description = "Datos inválidos"),
                                    @ApiResponse(responseCode = "401", description = "No autorizado")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(HandlerReport handlerReport) {
        return route(GET("/api/v1/hola"), handlerReport::helloWorld)
                .andRoute(GET(reportsPath.getReports()), handlerReport::getAllReports)
                .filter(apiKeyAuthFilter)
                .filter(globalExceptionFilter);
    }
}
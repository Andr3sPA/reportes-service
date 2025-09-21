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

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RouterRest {
    private final ApiKeyAuthFilter apiKeyAuthFilter;
    private final ReportsPath reportsPath;
    private final GlobalExceptionFilter globalExceptionFilter;
    @Bean
    @RouterOperations({
            @RouterOperation(
                    path = "/api/v1/solicitud",
                    produces = { MediaType.APPLICATION_JSON_VALUE },
                    method = RequestMethod.POST,
                    beanClass = ApprovedReport.class,
                    beanMethod = "saveLoanApp",
                    operation = @Operation(
                            operationId = "saveLoanApp",
                            summary = "Crear solicitud de préstamo",
                            description = "Crea una nueva solicitud de préstamo en el sistema",
                            requestBody = @RequestBody(
                                    required = true,
                                    content = @Content(
                                            schema = @Schema(implementation = ApprovedReport.class)
                                    )
                            ),
                            responses = {
                                    @ApiResponse(
                                            responseCode = "200",
                                            description = "Solicitud creada correctamente",
                                            content = @Content(
                                                    schema = @Schema(implementation = ApprovedReport.class)
                                            )
                                    ),
                                    @ApiResponse(responseCode = "400", description = "Datos inválidos")
                            }
                    )
            )
    })
    public RouterFunction<ServerResponse> routerFunction(HandlerReport handlerReport) {
        return route(GET(reportsPath.getReports()), handlerReport::getAllReports)
                .filter(globalExceptionFilter);
    }
}


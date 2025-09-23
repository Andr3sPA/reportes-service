package co.com.bancolombia.api.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.validation.Validator;
import static org.mockito.Mockito.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RequestValidatorTest {
    private Validator validator;
    private RequestValidator requestValidator;

    @BeforeEach
    void setUp() {
        validator = mock(Validator.class);
        requestValidator = new RequestValidator(validator);
    }

    @Test
    void validateShouldNotThrowForValidDto() {
        DummyDto dto = new DummyDto();
        assertDoesNotThrow(() -> requestValidator.validate(dto, DummyDto.class));
    }

    static class DummyDto {
        // Sin restricciones
    }
}

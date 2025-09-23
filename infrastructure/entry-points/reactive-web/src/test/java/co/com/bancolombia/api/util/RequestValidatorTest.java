package co.com.bancolombia.api.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.validation.Validator;
import static org.mockito.Mockito.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import co.com.bancolombia.exception.MissingFieldException;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void validateShouldThrowForInvalidDto() {
        DummyDto dto = new DummyDto();
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            org.springframework.validation.Errors e = (org.springframework.validation.Errors) args[1];
            e.reject("dummy", "Campo requerido");
            return null;
        }).when(validator).validate(any(), any());
        assertThrows(MissingFieldException.class, () -> requestValidator.validate(dto, DummyDto.class));
    }

    @Test
    void validateShouldNotThrowForNullDto() {
        assertDoesNotThrow(() -> requestValidator.validate(null, DummyDto.class));
    }

    static class DummyDto {
        // Sin restricciones
    }
}

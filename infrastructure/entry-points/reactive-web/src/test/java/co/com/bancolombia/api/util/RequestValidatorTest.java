package co.com.bancolombia.api.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import co.com.bancolombia.exception.MissingFieldException;
import static org.mockito.Mockito.*;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
<<<<<<< HEAD
=======
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import co.com.bancolombia.exception.MissingFieldException;
>>>>>>> 7e7fe8715312ab7522e89d71a64708ac6901f659
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
<<<<<<< HEAD
            Errors errors = invocation.getArgument(1);
            errors.reject("field", "Campo requerido");
            return null;
        }).when(validator).validate(any(), any());
        Exception ex = assertThrows(MissingFieldException.class, () -> requestValidator.validate(dto, DummyDto.class));
        // Opcional: verificar el mensaje
        // assertTrue(ex.getMessage().contains("Campo requerido"));
=======
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
>>>>>>> 7e7fe8715312ab7522e89d71a64708ac6901f659
    }

    static class DummyDto {
        // Sin restricciones
    }
}

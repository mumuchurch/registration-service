package com.afrikatek.registrationservice.domain;

import com.afrikatek.registrationservice.domain.enumeration.Gender;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class CongregantValidationTests {
    private static Validator validator;

    @BeforeAll
    static void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenFieldsCorrectThenValidationSucceeds() {
        var congregant = Congregant.builder()
                .title("Mr")
                .firstNames("Murukai")
                .surname("Gumbo-Mberi")
                .gender(Gender.MALE)
                .dob(LocalDate.of(1984, 11, 8))
                .email("murukai.mberi@gmail.com")
                .profession("Software Engineer")
                .build();
        Set<ConstraintViolation<Congregant>> violations = validator.validate(congregant);
        assertThat(violations).isEmpty();
    }
}

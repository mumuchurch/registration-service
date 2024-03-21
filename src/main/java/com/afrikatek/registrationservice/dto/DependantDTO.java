package com.afrikatek.registrationservice.dto;

import com.afrikatek.registrationservice.domain.enumeration.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record DependantDTO(
        Long id,
        @NotBlank(message = "Full names can not be blank.")
        String fullNames,
        @NotNull(message = "Date of birth can not be null.")
        LocalDate dateOfBirth,
        Gender gender,
        Long congregantId
) {
}

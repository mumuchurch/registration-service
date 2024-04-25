package com.afrikatek.registrationservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BaptismHistoryDTO(
        Long id,
        @NotNull(message = "Parish name should be defined.")
        String parishName,
        @NotNull(message = "Baptism date should be defined.")
        LocalDate baptismDate,
        @NotNull(message = "Confirmed date should be defined.")
        LocalDate confirmedDate,
        @NotBlank(message = "The parish you are baptised at should be defined.")
        String parishBaptisedAt,
        @NotBlank(message = "The parish you were confirmed at should be defined.")
        String parishConfirmedAt
) {
}

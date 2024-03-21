package com.afrikatek.registrationservice.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;

import java.time.Instant;
import java.time.LocalDate;

public record BaptismHistory(
        @Id
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
        String parishConfirmedAt,
        @LastModifiedDate
        Instant lastModifiedDate,
        @Version
        int version
) {
    public static BaptismHistory of(
            String parishName,
            LocalDate baptismDate,
            LocalDate confirmedDate,
            String parishBaptisedAt,
            String parishConfirmedAt
    ) {
        return new BaptismHistory(null,
                parishName,
                baptismDate,
                confirmedDate,
                parishBaptisedAt,
                parishConfirmedAt,
                null,
                0);
    }
}

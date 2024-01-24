package com.afrikatek.registrationservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;


public record BaptismHistory(
        @Id
        Long id,
        @NotNull
        @Column(name = "parish_name", nullable = false)
        String parishName,
        @NotNull
        @Column(name = "baptism_date", nullable = false)
        LocalDate baptismDate,
        @NotNull
        @Column(name = "confirmed_date", nullable = false)
        LocalDate confirmedDate,
        @NotNull
        @Column(name = "parish_baptised_at", nullable = false)
        String parishBaptisedAt,
        @NotNull
        @Column(name = "parish_confirmed_at", nullable = false)
        String parishConfirmedAt,
        @NotNull
        @Column(name = "user_id", nullable = false)
        Long userId,
        @Version
        int version
) {
        public static BaptismHistory of(
                String parishName,
                LocalDate baptismDate,
                LocalDate confirmedDate,
                String parishBaptisedAt,
                String parishConfirmedAt,
                Long userId
        ) {
                return new BaptismHistory(
                        null,
                        parishName,
                        baptismDate,
                        confirmedDate,
                        parishBaptisedAt,
                        parishConfirmedAt,
                        userId,
                        0);
        }
}

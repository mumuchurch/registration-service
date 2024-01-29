package com.afrikatek.registrationservice.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "baptism_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaptismHistory {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        @NotNull
        private String parishName;
        @NotNull
        private LocalDate baptismDate;
        @NotNull
        private LocalDate confirmedDate;
        @NotNull
        private String parishBaptisedAt;
        @NotNull
        private String parishConfirmedAt;

        @Version
        private int version;
        public static BaptismHistory of(
                String parishName,
                LocalDate baptismDate,
                LocalDate confirmedDate,
                String parishBaptisedAt,
                String parishConfirmedAt
        ) {
                return new BaptismHistory(
                        null,
                        parishName,
                        baptismDate,
                        confirmedDate,
                        parishBaptisedAt,
                        parishConfirmedAt,
                        0);
        }
}

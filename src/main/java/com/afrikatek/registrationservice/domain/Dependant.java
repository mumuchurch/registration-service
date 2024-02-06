package com.afrikatek.registrationservice.domain;

import com.afrikatek.registrationservice.domain.enumeration.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.jdbc.core.mapping.AggregateReference;
import org.springframework.data.relational.core.mapping.Table;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "dependants")
public class Dependant {
        @Id
        private Long id;
        @NotBlank(message = "Full names can not be blank.")
        private String fullNames;
        @NotNull(message = "Date of birth can not be null.")
        private LocalDate dateOfBirth;
        private Gender gender;
        @CreatedDate
        private Instant createdDate;
        @LastModifiedDate
        private Instant lastModifiedDate;
        @Version
        private int version;
        private AggregateReference<Congregant, Long> congregant;
}

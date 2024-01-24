package com.afrikatek.registrationservice.domain;

import com.afrikatek.registrationservice.domain.enumeration.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@Entity
@Table(name = "dependants")
public record Dependant(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        @NotBlank(message = "Full names can not be blank.")
        @Column(nullable = false)
        String fullNames,
        @Column(nullable = false)
        @NotNull(message = "Date of birth can not be null.")
        LocalDate dateOfBirth,
        Gender gender
) {
}

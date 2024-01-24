package com.afrikatek.registrationservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

@Entity
@Table(name = "marriage_history")
public record MarriageHistory(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        @NotNull(message = "Marriage date can not be null")
        @Column(nullable = false)
        @Past
        LocalDate marriageDate,
        @NotBlank(message = "Parish married at can not be blank.")
        @Column(nullable = false)
        String parishMarriedAt,
        @Column(nullable = false)
        @NotBlank(message = "Spousal full name can not be blank.")
        String spouseFullNames
) {
}

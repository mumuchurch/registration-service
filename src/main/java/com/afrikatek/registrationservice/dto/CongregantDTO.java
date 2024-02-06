package com.afrikatek.registrationservice.dto;

import com.afrikatek.registrationservice.domain.enumeration.Gender;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public record CongregantDTO(
        String title,
        String firstNames,
        String surname,
        String email,
        LocalDate dob,
        Gender gender,
        String profession
) {
}

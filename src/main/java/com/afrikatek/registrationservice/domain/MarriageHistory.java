package com.afrikatek.registrationservice.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;

import java.time.Instant;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarriageHistory {
    @NotNull(message = "Marriage date can not be null")
    @Past
    LocalDate marriageDate;
    @NotBlank(message = "Parish married at can not be blank.")
    String parishMarriedAt;
    @NotBlank(message = "Spousal full name can not be blank.")
    String spouseFullNames;
    @CreatedDate
    Instant createdDate;
    @LastModifiedDate
    Instant lastModifiedDate;
    @Version
    int version;
    @Transient
    Congregant congregant;
}

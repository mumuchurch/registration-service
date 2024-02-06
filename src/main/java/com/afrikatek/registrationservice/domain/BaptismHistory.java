package com.afrikatek.registrationservice.domain;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaptismHistory {
    @NotNull(message = "Parish name should be defined.")
    String parishName;
    @NotNull(message = "Baptism date should be defined.")
    LocalDate baptismDate;
    @NotNull(message = "Confirmed date should be defined.")
    LocalDate confirmedDate;
    @NotBlank(message = "The parish you are baptised at should be defined.")
    String parishBaptisedAt;
    @NotBlank(message = "The parish you were confirmed at should be defined.")
    String parishConfirmedAt;
    @CreatedDate
    Instant createdDate;
    @LastModifiedDate
    Instant lastModifiedDate;
    @Version
    int version;
    @Transient
    Congregant congregant;
}

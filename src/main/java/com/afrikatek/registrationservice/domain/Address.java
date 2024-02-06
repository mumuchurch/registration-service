package com.afrikatek.registrationservice.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @NotBlank(message = "Street address must be defined.")
    String streetAddress;
    String streetAddressOne;
    @NotBlank(message = "City must be defined.")
    String city;
    @NotBlank(message = "Province must be defined.")
    String province;
    String zipCode;
    @NotBlank(message = "Country must be defined.")
    String country;
    @CreatedDate
    Instant createdDate;
    @LastModifiedDate
    Instant lastModifiedDate;
    @Version
    int version;
    @Transient
    Congregant congregant;
}

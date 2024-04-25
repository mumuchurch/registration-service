package com.afrikatek.registrationservice.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.annotation.Version;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    private String guid;
    @NotBlank(message = "Street address must be defined.")
    private String streetAddress;
    private String streetAddressOne;
    @NotBlank(message = "City must be defined.")
    private String city;
    @NotBlank(message = "Province must be defined.")
    private String province;
    private String zipCode;
    @NotBlank(message = "Country must be defined.")
    private String country;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant lastModifiedDate;
    @Version
    private int version;
    @Transient
    private Congregant congregant;
}

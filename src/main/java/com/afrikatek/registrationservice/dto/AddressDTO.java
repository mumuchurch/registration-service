package com.afrikatek.registrationservice.dto;

import jakarta.validation.constraints.NotBlank;

public record AddressDTO(
        @NotBlank(message = "Street address must be defined.")
        String streetAddress,
        String streetAddressOne,
        @NotBlank(message = "City must be defined.")
        String city,
        @NotBlank(message = "Province must be defined.")
        String province,
        String zipCode,
        @NotBlank(message = "Country must be defined.")
        String country
) {
}

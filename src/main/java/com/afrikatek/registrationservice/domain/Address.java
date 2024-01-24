package com.afrikatek.registrationservice.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "addresses")
public record Address(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id,
        @NotBlank(message = "Street address can not be null.")
        @Column(nullable = false)
        String streetAddress,
        @Column(nullable = true)
        String streetAddressOne,
        @NotBlank(message = "City can not be blank.")
        @Column(nullable = false)
        String city,
        @NotBlank(message = "Province can not be blank.")
        @Column(nullable = false)
        String province,
        @NotBlank(message = "ZipCode can not be blank.")
        @Column(nullable = false)
        String zipCode,
        @NotBlank(message = "Country can not be blank.")
        @Column(nullable = false)
        String country,
        @Version
        int version
) {
        public static Address of(
                String streetAddress,
                String streetAddressOne,
                String city,
                String province,
                String zipCode,
                String country,
                int version) {
                return new Address(
                        null,
                        streetAddress,
                        streetAddressOne,
                        city,
                        province,
                        zipCode,
                        country,
                        0);
        }
}

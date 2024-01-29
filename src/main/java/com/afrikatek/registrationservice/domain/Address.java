package com.afrikatek.registrationservice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "addresses")
public class Address {
        @Id
        Long id;
        @NotBlank(message = "Street address can not be null.")
        String streetAddress;
        String streetAddressOne;
        @NotBlank(message = "City can not be blank.")
        String city;
        @NotBlank(message = "Province can not be blank.")
        String province;
        @NotBlank(message = "ZipCode can not be blank.")
        String zipCode;
        @NotBlank(message = "Country can not be blank.")
        String country;
        @Version
        int version;
        public static Address of(
                String streetAddress,
                String streetAddressOne,
                String city,
                String province,
                String zipCode,
                String country) {
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

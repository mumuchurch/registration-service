package com.afrikatek.registrationservice.domain;

import com.afrikatek.registrationservice.domain.enumeration.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;

import java.time.Instant;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Congregant {
    @Id
    private Long id;
    @NotBlank(message = "Title should be defined.")
    @Size(max = 15)
    private String title;
    @NotBlank(message = "First names should be defined.")
    @Size(max = 50)
    private String firstNames;
    @NotBlank(message = "Surname should be defined.")
    @Size(max = 50, message = "Surname can not contain more than 50 characters.")
    private String surname;
    @NotBlank(message = "Email should be defined.")
    @Size(max = 100)
    //@Pattern(regexp = "/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$/")
    private String email;
    @NotNull
    private LocalDate dob;
    private Gender gender;
    @Size(max = 100, message = "Profession can not be more than 100 characters.")
    private String profession;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant lastModifiedDate;
    @Version
    private int version;
    private Set<Address> addresses = new HashSet<>();
    private BaptismHistory baptismHistory;
    private MarriageHistory marriageHistory;
    public void addAddress(Address address) {
        this.addresses.add(address);
    }

    public void removeAddress(Address address) {
        this.addresses.remove(address);
    }
}

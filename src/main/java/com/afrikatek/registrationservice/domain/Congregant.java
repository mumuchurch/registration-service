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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public Instant getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Instant lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    public BaptismHistory getBaptismHistory() {
        return baptismHistory;
    }

    public void setBaptismHistory(BaptismHistory baptismHistory) {
        this.baptismHistory = baptismHistory;
    }

    public MarriageHistory getMarriageHistory() {
        return marriageHistory;
    }

    public void setMarriageHistory(MarriageHistory marriageHistory) {
        this.marriageHistory = marriageHistory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Congregant that = (Congregant) o;

        if (!title.equals(that.title)) return false;
        if (!firstNames.equals(that.firstNames)) return false;
        if (!surname.equals(that.surname)) return false;
        if (!email.equals(that.email)) return false;
        if (!dob.equals(that.dob)) return false;
        if (gender != that.gender) return false;
        return profession.equals(that.profession);
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + firstNames.hashCode();
        result = 31 * result + surname.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + dob.hashCode();
        result = 31 * result + gender.hashCode();
        result = 31 * result + profession.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Congregant{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", firstNames='" + firstNames + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                ", gender=" + gender +
                ", profession='" + profession + '\'' +
                ", createdDate=" + createdDate +
                ", lastModifiedDate=" + lastModifiedDate +
                ", version=" + version +
                ", addresses=" + addresses +
                ", baptismHistory=" + baptismHistory +
                ", marriageHistory=" + marriageHistory +
                '}';
    }
}

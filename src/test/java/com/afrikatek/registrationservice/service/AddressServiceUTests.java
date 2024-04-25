package com.afrikatek.registrationservice.service;

import com.afrikatek.registrationservice.config.DataConfig;
import com.afrikatek.registrationservice.domain.Address;
import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.domain.enumeration.Gender;
import com.afrikatek.registrationservice.dto.AddressDTO;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import com.afrikatek.registrationservice.service.impl.AddressServiceImpl;
import com.afrikatek.registrationservice.service.impl.CongregantServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class AddressServiceUTests {

    private AddressServiceImpl addressService;
    @Autowired
    private CongregantRepository congregantRepository;

    private CongregantServiceImpl congregantService;

    @BeforeEach
    void setup() {
        congregantService = new CongregantServiceImpl(congregantRepository);
        addressService = new AddressServiceImpl(congregantService, congregantRepository);
    }
    @Test
    void whenAddressAddThenSucceed(){
        var congregant = Congregant.builder()
                .title("Mr")
                .firstNames("Murukai")
                .surname("Gumbo-Mberi")
                .email("murukai.mberi@gmail.com")
                .gender(Gender.MALE)
                .profession("Software Engineer")
                .dob(LocalDate.of(1984, 11, 8))
                .build();
        Congregant saved = congregantRepository.save(congregant);
        AddressDTO address = new AddressDTO("169 Silver Stream South", "Halfway Gardens", "Midrand", "Gauteng", "Gauteng", "South Africa");
        Address addAddress = addressService.addAddress(address, saved.getId());
        assertThat(addAddress).isNotNull();
    }
}

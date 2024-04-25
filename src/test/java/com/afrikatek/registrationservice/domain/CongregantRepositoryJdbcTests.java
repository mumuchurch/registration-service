package com.afrikatek.registrationservice.domain;

import com.afrikatek.registrationservice.config.DataConfig;
import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.domain.enumeration.Gender;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
@Import(DataConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class CongregantRepositoryJdbcTests {
    @Autowired
    private CongregantRepository congregantRepository;

    @Autowired
    private JdbcAggregateTemplate jdbcAggregateTemplate;

    @Test
    void whenCreateCongregantThenSucceed() {
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
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void whenUpdateCongregantTheSucceed() {
        var congregant = Congregant.builder()
                .title("Mr")
                .firstNames("Murukai")
                .surname("Gumbo-Mberi")
                .email("murukai.mberi@gmail.com")
                .gender(Gender.MALE)
                .profession("Software Engineer")
                .dob(LocalDate.of(1984, 11, 8))
                .build();
        jdbcAggregateTemplate.insert(congregant);
        Optional<Congregant> optionalCongregant = congregantRepository.findCongregantByEmail(congregant.getEmail());
        assertThat(optionalCongregant).isPresent();

        Congregant savedCongregant = optionalCongregant.get();
        savedCongregant.setDob(LocalDate.of(1987, 2, 9));
        Congregant saved = congregantRepository.save(savedCongregant);
        assertThat(saved).isNotNull();
        assertThat(saved.getDob()).isAfterOrEqualTo(LocalDate.of(1987,2,9));
    }
}

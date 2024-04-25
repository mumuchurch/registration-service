package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.Congregant;
import com.afrikatek.registrationservice.domain.enumeration.Gender;
import com.afrikatek.registrationservice.dto.CongregantDTO;
import com.afrikatek.registrationservice.repository.CongregantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
public class CongregantResourceIT {

    private Logger logger = LoggerFactory.getLogger(CongregantResourceIT.class);
    @Autowired
    private CongregantRepository congregantRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void clearDB() {
        congregantRepository.deleteAll();
    }

    @Test
    void whenPostRequestThenCongregantIsCreatedAndReturned() {
        CongregantDTO congregantDTO = new CongregantDTO("Mr", "John", "Doe", "john.doe@example.com", LocalDate.of(1972, 3,22), Gender.MALE, "Singer / Musician");
        Congregant congregant = webTestClient.post()
                .uri("/api/congregants")
                .bodyValue(congregantDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Congregant.class).value(congregant1 -> assertThat(congregant1).isNotNull())
                .returnResult().getResponseBody();
        logger.info("Created congregant {}", congregant);
    }

    @Test
    void whenPutRequestToUpdateSurnameThenCongregantIsUpdatedAndReturned() {
        CongregantDTO congregantDTO = new CongregantDTO("Mr", "John", "Doe", "john.doe@example.com", LocalDate.of(1972, 3,22), Gender.MALE, "Singer / Musician");
        Congregant congregant = webTestClient.post()
                .uri("/api/congregants")
                .bodyValue(congregantDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Congregant.class).value(congregant1 -> assertThat(congregant1).isNotNull())
                .returnResult().getResponseBody();

        CongregantDTO congregantDTOToUpdate = new CongregantDTO(
                congregant.getTitle(),
                congregant.getFirstNames(),
                "Doeson",
                congregant.getEmail(),
                congregant.getDob(),
                congregant.getGender(),
                congregant.getProfession()
        );
        webTestClient.put()
                .uri("/api/congregants/" + congregant.getId())
                .bodyValue(congregantDTOToUpdate)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Congregant.class)
                .value(actualCongregant -> {
                    assertThat(actualCongregant).isNotNull();
                    assertThat(actualCongregant.getSurname()).isEqualTo("Doeson");
                });
    }

    @Test
    void whenPutRequestToUpdateValuesThenCongregantIsUpdatedAndReturned() {
        CongregantDTO congregantDTO = new CongregantDTO("Mr", "John", "Doe", "john.doe@example.com", LocalDate.of(1972, 3,22), Gender.MALE, "Singer / Musician");
        Congregant congregant = webTestClient.post()
                .uri("/api/congregants")
                .bodyValue(congregantDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Congregant.class).value(congregant1 -> assertThat(congregant1).isNotNull())
                .returnResult().getResponseBody();

        CongregantDTO congregantDTOToUpdate = new CongregantDTO(
                "Dr",
                "Chipo",
                "Shumba",
                "chipo.shumba@example.com",
                LocalDate.of(1973, 4, 18),
                Gender.FEMALE,
                "Graphic Designer"
        );
        webTestClient.put()
                .uri("/api/congregants/" + congregant.getId())
                .bodyValue(congregantDTOToUpdate)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody(Congregant.class)
                .value(actualCongregant -> {
                    assertThat(actualCongregant).isNotNull();
                    assertThat(actualCongregant.getFirstNames()).isEqualTo("Chipo");
                    assertThat(actualCongregant.getTitle()).isEqualTo("Dr");
                    assertThat(actualCongregant.getSurname()).isEqualTo("Shumba");
                    assertThat(actualCongregant.getEmail()).isEqualTo("chipo.shumba@example.com");
                    assertThat(actualCongregant.getDob()).isEqualTo(LocalDate.of(1973, 4, 18));
                    assertThat(actualCongregant.getGender()).isEqualTo(Gender.FEMALE);
                    assertThat(actualCongregant.getProfession()).isEqualTo("Graphic Designer");
                });
    }

    @Test
    void whenGetRequestToFindAllCongregantTheReturn200WithAllCongregants() {
        CongregantDTO congregantDTO = new CongregantDTO("Mr", "John", "Doe", "john.doe@example.com", LocalDate.of(1972, 3,22), Gender.MALE, "Singer / Musician");
        webTestClient.post()
                .uri("/api/congregants")
                .bodyValue(congregantDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Congregant.class).value(congregant1 -> assertThat(congregant1).isNotNull())
                .returnResult().getResponseBody();

        CongregantDTO congregantDTOOne = new CongregantDTO("Mrs", "Jane", "Dane", "jane.dane@example.com", LocalDate.of(1981, 7,11), Gender.MALE, "Fine Artist");
        webTestClient.post()
                .uri("/api/congregants")
                .bodyValue(congregantDTOOne)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Congregant.class).value(congregant1 -> assertThat(congregant1).isNotNull())
                .returnResult().getResponseBody();

        List<Congregant> foundCongregants = webTestClient.get()
                .uri("/api/congregants")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Congregant.class).value(congregants -> assertThat(congregants).hasSize(2))
                .returnResult().getResponseBody();
        logger.info("Found congregants {}", foundCongregants);
    }

    @Test
    void whenDeleteRequestThenCongregantIsDeleted() {
        CongregantDTO congregantDTO = new CongregantDTO("Mr", "John", "Doe", "john.doe@example.com", LocalDate.of(1972, 3,22), Gender.MALE, "Singer / Musician");
        Congregant congregant = webTestClient.post()
                .uri("/api/congregants")
                .bodyValue(congregantDTO)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Congregant.class).value(congregant1 -> assertThat(congregant1).isNotNull())
                .returnResult().getResponseBody();

        webTestClient.delete()
                .uri("/api/congregants/" + congregant.getId())
                .exchange()
                .expectStatus().isNoContent();

        webTestClient.get()
                .uri("/api/congregants/" + congregant.getId())
                .exchange()
                .expectStatus().isNotFound();
    }
}

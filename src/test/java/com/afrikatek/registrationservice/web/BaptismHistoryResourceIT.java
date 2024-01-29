package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.BaptismHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration")
class BaptismHistoryResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenCreateBaptismHistoryThenSucceed() {
        var baptismHistoryCreate = BaptismHistory.of(
                "Midrand",
                LocalDate.of(2022, 2, 22),
                LocalDate.of(2022, 2, 22),
                "Midrand",
                "Midrand");

        BaptismHistory expectedBook = webTestClient
                .post()
                .uri("/api/v1.0/baptism-histories")
                .bodyValue(baptismHistoryCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BaptismHistory.class).value(history -> assertThat(history).isNotNull())
                .returnResult().getResponseBody();

    }

    @Test
    void whenPutRequestThenBaptismHistoryUpdated() {
        var baptismHistoryCreate = BaptismHistory.of(
                "Midrand",
                LocalDate.of(2022, 2, 22),
                LocalDate.of(2022, 2, 22),
                "Midrand",
                "Midrand");

        BaptismHistory createdBaptismHistory = webTestClient
                .post()
                .uri("/api/v1.0/baptism-histories")
                .bodyValue(baptismHistoryCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BaptismHistory.class).value(history -> assertThat(history).isNotNull())
                .returnResult().getResponseBody();

        var baptismHistoryUpdate = new BaptismHistory(
                createdBaptismHistory.getId(),
                createdBaptismHistory.getParishName(),
                createdBaptismHistory.getBaptismDate(),
                createdBaptismHistory.getConfirmedDate(),
                "Midrand Parish",
                createdBaptismHistory.getParishConfirmedAt(),
                createdBaptismHistory.getVersion()
        );

        webTestClient
                .put()
                .uri("/api/v1.0/baptism-histories")
                .bodyValue(baptismHistoryUpdate)
                .exchange()
                .expectStatus().isAccepted()
                .expectBody(BaptismHistory.class).value(history -> {
                   assertThat(history).isNotNull();
                   assertThat(history.getParishConfirmedAt()).isEqualTo(baptismHistoryUpdate.getParishConfirmedAt());
                });
    }


    @Test
    void whenDeleteRequestThenBaptismHistoryDeleted() {
        var baptismHistoryCreate = BaptismHistory.of(
                "Midrand",
                LocalDate.of(2022, 2, 22),
                LocalDate.of(2022, 2, 22),
                "Midrand",
                "Midrand");

        BaptismHistory createdBaptismHistory = webTestClient
                .post()
                .uri("/api/v1.0/baptism-histories")
                .bodyValue(baptismHistoryCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BaptismHistory.class).value(history -> assertThat(history).isNotNull())
                .returnResult().getResponseBody();

        webTestClient
                .delete()
                .uri("/api/v1.0/baptism-histories/" + createdBaptismHistory.getId())
                .exchange()
                .expectStatus().isNoContent();

        webTestClient
                .get()
                .uri("/api/v1.0/baptism-histories/" + createdBaptismHistory.getId())
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class).value(errorMessage -> {
                   assertThat(errorMessage).isEqualTo("The BaptismHistory with id 3 could not be found.");
                });
    }
}

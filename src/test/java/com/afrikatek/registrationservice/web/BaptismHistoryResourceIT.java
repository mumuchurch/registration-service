package com.afrikatek.registrationservice.web;

import com.afrikatek.registrationservice.domain.BaptismHistory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BaptismHistoryResourceIT {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenCreateBaptismHistoryThenSucceed() {
        var baptismHistoryCreate = BaptismHistory.of(
                "Midrand",
                LocalDate.of(2022, 2, 22),
                LocalDate.of(2022, 2, 22),
                "Midrand",
                "Midrand",
                2L);

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
                "Midrand",
                2L);

        BaptismHistory createdBaptismHistory = webTestClient
                .post()
                .uri("/api/v1.0/baptism-histories")
                .bodyValue(baptismHistoryCreate)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(BaptismHistory.class).value(history -> assertThat(history).isNotNull())
                .returnResult().getResponseBody();

        var baptismHistoryUpdate = new BaptismHistory(
                createdBaptismHistory.id(),
                createdBaptismHistory.parishName(),
                createdBaptismHistory.baptismDate(),
                createdBaptismHistory.confirmedDate(),
                createdBaptismHistory.parishBaptisedAt(),
                "Midrand Parish",
                createdBaptismHistory.userId(),
                createdBaptismHistory.version()
        );

        webTestClient
                .put()
                .uri("/api/v1.0/baptism-histories")
                .bodyValue(baptismHistoryUpdate)
                .exchange()
                .expectStatus().isOk()
                .expectBody(BaptismHistory.class).value(history -> {
                   assertThat(history).isNotNull();
                   assertThat(history.parishConfirmedAt()).isEqualTo(baptismHistoryUpdate.parishConfirmedAt());
                });
    }


    @Test
    void whenDeleteRequestThenBaptismHistoryDeleted() {
        var baptismHistoryCreate = BaptismHistory.of(
                "Midrand",
                LocalDate.of(2022, 2, 22),
                LocalDate.of(2022, 2, 22),
                "Midrand",
                "Midrand",
                2L);

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
                .uri("/api/v1.0/baptism-histories/" + createdBaptismHistory.id())
                .exchange()
                .expectStatus().isNoContent();

        webTestClient
                .get()
                .uri("/api/v1.0/baptism-histories/" + createdBaptismHistory.id())
                .exchange()
                .expectStatus().isNotFound()
                .expectBody(String.class).value(errorMessage -> {
                   assertThat(errorMessage).isEqualTo("The BaptismHistory could not be found.");
                });
    }
}

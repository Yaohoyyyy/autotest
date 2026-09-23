package tests.api;

import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import models.Jur;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.post;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;

public class JurTest {

    @RegisterExtension
    public WireMockExtension wireMockExtension = WireMockExtension.newInstance()
            .options(WireMockConfiguration.wireMockConfig().port(8089))
            .build();

    @Test
    public void exampleTest() {
        // 1. Настраиваем "заглушку" (stub)
        wireMockExtension.stubFor(get(urlEqualTo("/jur/1"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"id\": 1, \"name\": \"Автотестовый ЮЛ\", \"inn\": \"1234567890\", \"ogrn\": \"112233445566778\"}")));

        // 2. Отправляем реальный HTTP-запрос на порт WireMock

        Jur jur = given()
                .when()
                .baseUri("http://localhost:8080")
                .get("/api/jur/1")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(Jur.class);

        assertAll(
                () -> assertThat(jur.getId()).isEqualTo(1L),
                () -> assertThat(jur.getName()).isEqualTo("Автотестовый ЮЛ"),
                () -> assertThat(jur.getInn()).isEqualTo("1234567890"),
                () -> assertThat(jur.getOgrn()).isEqualTo("112233445566778")
        );
    }

}

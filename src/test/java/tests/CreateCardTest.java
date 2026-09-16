package tests;

import annotations.WithAuth;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import config.BaseTest;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import models.Card;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DatabaseHelper;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class CreateCardTest extends BaseTest {

    private static Long deletingCardId;

    private static String deletingCardType = "DEBIT";
    private static Long deletingCardBalance = 2500L;
    private static Long deletingCardUserId = 2L;

    @BeforeAll
    public static void dataSetup() {
        deletingCardId = dbHelper.insertCard(deletingCardType, deletingCardBalance, deletingCardUserId);
    }

    @AfterAll
    public static void deleteData() {
        dbHelper.deleteCardByCardId(deletingCardId);
    }

    @Test
    @WithAuth
    public void createCard() {
        Card card = Card.builder()
                .balance(2400)
                .cardType("DEBIT")
                .userId(2L)
                .build();

        given()
                .spec(getAuthRequestSpec())
                .body(card)
                .when()
                .post("/cards")
                .then()
                .statusCode(201);
    }

    @Test
    @WithAuth
    public void deleteCard() {
        given()
                .spec(getAuthRequestSpec())
                .when()
                .delete("/cards/" + deletingCardId)
                .then()
                .statusCode(204);
    }

    @Test
    @WithAuth
    public void pojoParseCard() throws JsonProcessingException {
        Card card = given()
                .spec(getAuthRequestSpec())
                .when()
                .get("/cards/1")
                .then()
                .log().all()
                .extract()
                .as(Card.class);

        assertAll(
                () -> assertEquals(1, card.getId()),
                () -> assertEquals("DEBIT", card.getCardType()),
                () -> assertEquals(2, card.getUserId())
        );

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("objectMapper: " + objectMapper.writeValueAsString(card));

    }

    @Test
    @WithAuth
    public void extractBodyRsTest() {
        Map<String, Object> response = given()
                .spec(getAuthRequestSpec())
                .when()
                .get("/cards/1")
                .then()
                .log().all()
                .extract()
                .as(Map.class);

        response.entrySet().forEach(System.out::println);
        assertThat(response.get("id").equals(1));
    }

    @Test
    @WithAuth
    public void basicHamcrestCheckBodyRsTest() {
        given()
                .spec(getAuthRequestSpec())
                .when()
                .get("/cards/1")
                .then()
                .log().all()
                .body("id", equalTo(1))
                .body("cardType", containsString("EBI"));
    }

    @Test
    @WithAuth
    public void extractRsTest() {
        Response response = given()
                .spec(getAuthRequestSpec())
                .when()
                .get("/cards/1")
                .then()
                .extract()
                .response();

        response.then().log().all();
    }
}

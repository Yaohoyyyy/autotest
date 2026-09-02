package tests;

import annotations.WithAuth;
import config.BaseTest;
import models.Card;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.DatabaseHelper;

import static io.restassured.RestAssured.given;

public class CreateCardTest extends BaseTest {

    private static Long deletingCardId;

    private static String deletingCardType = "DEBIT";
    private static Long deletingCardBalance = 2500L;
    private static Long deletingCardUserId = 2L;

    @BeforeAll
    public static void dataSetup() {
        deletingCardId = dbHelper.insertCard(deletingCardType, deletingCardBalance, deletingCardUserId);
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
}

package tests;

import io.cucumber.java.Before;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assumptions.*;

public class ExampleTest {

    @Before
    public static void setup() {
        authentication = oauth2("my_token");
    }

    @Test
    public void exampleTest_1() {

        // 1
        given()
                .contentType(ContentType.JSON)
                .body("{\"name\": \"Alex\", \"age\": 30}")
                .when()
                .post("http://foobar")
                .then()
                .statusCode(200)
                .body("id", notNullValue());

        // 2

        Map<String, Object> json = new HashMap<>();
        json.put("name", "Alex");
        json.put("age", 30);

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("http://foobar")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    public void exampleTest_2() {
        String token = "Bearer " + "anyToken";
        given()
                .auth().oauth2(token)
                .when()
                .post("http://foobar");
    }

    @Test
    public void exampleTest_3() {
                when()
                .get("http://foobar")
                .then()
                        .body("data.users.find{it.id == 2}", notNullValue())
                        .body("data.users.id", hasItem(2))
                        .body("data.users.find{it.id == 2}.name", equalTo("John"))
                        .body("data.users.find{it.id == 5}", empty())
                        .body("data.users.id", not(hasItem(5)));
    }

    @Test
    public void exampleTest_4() {
        Map<String, Object> json = new HashMap<>(Map.ofEntries(
                Map.entry("login", "Vasya"),
                Map.entry("password", "admin")
        ));

        String authToken = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(json)
                .post("/login")
                .then()
                .extract()
                .path("authToken");

        given()
                .log().all()
                .auth().oauth2(authToken)
                .when()
                .get("/profile")
                .then()
                .log().ifValidationFails()
                .body("role", equalTo("ADMIN"));
    }

    @Test
    public void exampleTest_5() {
        assumeTrue(false);
    }
}

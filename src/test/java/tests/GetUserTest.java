package tests;

import annotations.WithAuth;
import config.BaseTest;
import models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

@DisabledIfEnvironmentVariable(named = "CI", matches = "true")
public class GetUserTest extends BaseTest {

    @Test
    @WithAuth
    public void testGetUserById() {

        // Arrange
        String firstName = "Ivan";
        String lastName = "Petrov";
        String email = "getuser_" + System.currentTimeMillis() + "@test.com";

        Map<String, Object> userData = new HashMap<>();
        userData.put("lastName", lastName);
        userData.put("firstName", firstName);
        userData.put("email", email);

        int userId = given()
                .spec(getAuthRequestSpec())
                .body(userData)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .path("id");

        // Act
        User user = given()
                .spec(getAuthRequestSpec())
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .body("id", equalTo(userId))
                .body("firstName", equalTo(firstName))
                .body("lastName", equalTo(lastName))
                .body("email", equalTo(email))
                .body("createdAt", notNullValue())
                .body("updatedAt", notNullValue())
                .extract()
                .as(User.class);

        // Assert
        assertEquals(userId, user.getId());
        assertEquals(firstName, user.getFirstName());
        assertEquals(lastName, user.getLastName());
        assertEquals(email, user.getEmail());

        // Cleanup
        dbHelper.deleteUserByEmail(email);
    }

    @Test
    @WithAuth
    public void testGetUserByNonExistentId() {

        String nonExistentId = "999999";

        // Act + Assert
        given()
                .spec(getAuthRequestSpec())
                .when()
                .get("/users/" + nonExistentId)
                .then()
                .statusCode(400)
                .body("message", equalTo("user not found with id: " + nonExistentId));
    }

    @Test
    @WithAuth
    public void testGetUserByInvalidId() {

        String inсorrectId = "abc";

        // Act + Assert
        given()
                .spec(getAuthRequestSpec())
                .when()
                .get("/users/" + inсorrectId)
                .then()
                .statusCode(400)
                .body("message", equalTo("user not found with id: " + inсorrectId));
    }
}

package tests.api;

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
public class CreateUserTest extends BaseTest {

    @Test
    @WithAuth
    public void testCreateUser() {

        // Arrange
        String firstName = "Ivan";
        String lastName = "Petrov";
        String email = "petrov@yandex.ru";

        Map<String, Object> userData = new HashMap<>();
        userData.put("lastName", lastName);
        userData.put("firstName", firstName);
        userData.put("email", email);

        // Act
        User createdUser = given()
                .spec(getAuthRequestSpec())
                .body(userData)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(201)
                .body("id", notNullValue())
                .extract()
                .body()
                .as(User.class);

        // Assert
        assertEquals(lastName, createdUser.getLastName());
        assertEquals(firstName, createdUser.getFirstName());
        assertNotNull(createdUser.getCreatedAt());
        assertNotNull(createdUser.getUpdatedAt());

        // Проверка создания записи в БД
        Map<String, Object> dbUser = dbHelper.getUserByEmail(email);
        assertNotNull(dbUser, "User not found in DB");
        assertAll("DB user",
                () -> assertEquals(lastName, dbUser.get("last_name")),
                () -> assertEquals(firstName, dbUser.get("first_name")),
                () -> assertEquals(email, dbUser.get("email")),
                () -> assertNotNull(dbUser.get("created_at")),
                () -> assertNotNull(dbUser.get("updated_at"))
        );

        // Cleanup
        dbHelper.deleteUserByEmail(email);
    }

    @Test
    @WithAuth
    public void testCreateUserWithoutEmail() {

        // Arrange
        String firstName = "Ivan";
        String lastName = "Petrov";
        String email = "nonexistent_" + System.currentTimeMillis() + "@test.com";

        Map<String, Object> userData = new HashMap<>();
        userData.put("lastName", lastName);
        userData.put("firstName", firstName);

        // Act + Assert
        given()
                .spec(getAuthRequestSpec())
                .body(userData)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(400)
                .body("message", equalTo("email: Email is required"));

        // DB check: пользователь не создался
        assertNull(dbHelper.getUserByEmail(email), "User should not have been created");
    }

    @Test
    @WithAuth
    public void testCreateUserWithoutLastName() {

        // Arrange
        String firstName = "Ivan";
        String email = "ivan_" + System.currentTimeMillis() + "@test.com";

        Map<String, Object> userData = new HashMap<>();
        userData.put("firstName", firstName);
        userData.put("email", email);

        // Act + Assert
        given()
                .spec(getAuthRequestSpec())
                .body(userData)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(400)
                .body("message", equalTo("lastName: Last name is required"));

        // DB check: пользователь не создался
        assertNull(dbHelper.getUserByEmail(email), "User should not have been created");
    }

    @Test
    @WithAuth
    public void testCreateUserWithoutFirstName() {

        // Arrange
        String lastName = "Petrov";
        String email = "petrov_" + System.currentTimeMillis() + "@test.com";

        Map<String, Object> userData = new HashMap<>();
        userData.put("lastName", lastName);
        userData.put("email", email);

        // Act + Assert
        given()
                .spec(getAuthRequestSpec())
                .body(userData)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec)
                .statusCode(400)
                .body("message", equalTo("firstName: First name is required"));

        // DB check: пользователь не создался
        assertNull(dbHelper.getUserByEmail(email), "User should not have been created");
    }

    @Test
    @WithAuth
    public void testCreatedUserWithExistingEmail() {

        // Arrange
        String firstName = "Vasya";
        String lastName = "Kulikov";
        String email = "kulikov@yandex.ru";

        dbHelper.deleteUserByEmail(email);
        dbHelper.insertUser(firstName, lastName, email);

        Map<String, Object> userData = new HashMap<>();
        userData.put("firstName", firstName);
        userData.put("lastName", lastName);
        userData.put("email", email);

        // Act + Assert
        given()
                .spec(getAuthRequestSpec())
                .body(userData)
                .when()
                .post("/users")
                .then()
                .statusCode(400)
                .body("message", equalTo("User with email " + email + " already exists"));

        // Cleanup
        dbHelper.deleteUserByEmail(email);
    }

/*    @Test
    public void testCreateUserWithPojo() {
        // Используем POJO
        User newUser = new User("john.doe@example.com", "John", "Doe");

        User createdUser = given()
                .spec(requestSpec)
                .body(newUser)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .body("email", equalTo("john.doe@example.com"))
                .body("firstName", equalTo("John"))
                .body("lastName", equalTo("Doe"))
                .extract()
                .body()
                .as(User.class);

        assertNotNull(createdUser.getId());
    }*/
}
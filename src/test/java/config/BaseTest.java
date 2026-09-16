package config;

import annotations.WithAuth;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Header;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import utils.DatabaseHelper;

import static io.restassured.RestAssured.given;

public class BaseTest {

    // Общие спецификации для всех тестов
    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;
    protected static DatabaseHelper dbHelper;
    protected static String authToken;

    private static final String baseURI = "http://localhost:8080/api";
    private static final String AUTH_ENDPOINT = "/auth/login";
    private static final String AUTH_LOGIN = "admin";
    private static final String AUTH_PASSWORD = "pwAdmin";

    @BeforeAll
    public static void setup() {
        // 1. Базовый URI для всех запросов
        RestAssured.baseURI = baseURI;

        RestAssured.config = RestAssured.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", 5000));

        // 2. Настройка RequestSpecification (общие настройки для запросов)
        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .log(LogDetail.ALL)  // Логируем все детали запроса
                .build();

        // 3. Настройка ResponseSpecification (общие проверки для ответов)
        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)  // Логируем все детали ответа
                .build();

        // Инициализация DatabaseHelper
        dbHelper = new DatabaseHelper();
        System.out.println("🚀 db initialized");
    }

    @BeforeEach
    public void beforeEachTest(TestInfo testInfo) {
        System.out.println("=== Start new test ===");

        if (authToken == null
                && testInfo.getTestMethod().isPresent()
                && testInfo.getTestMethod().get().isAnnotationPresent(WithAuth.class)) {
            authToken = given()
                    .contentType("application/json")
                    .body("{\"login\": \"" + AUTH_LOGIN + "\", \"password\": \"" + AUTH_PASSWORD + "\"}")
                    .when()
                    .post(AUTH_ENDPOINT)
                    .then()
                    .statusCode(200)
                    .extract()
                    .path("token");
        }
    }

    // Вспомогательный метод для GET-запросов
    protected RequestSpecification getRequestSpec() {
        return given()
                .spec(requestSpec);
    }

    // Вспомогательный метод для POST-запросов с телом
    protected RequestSpecification postRequestSpec(Object body) {
        return given()
                .spec(requestSpec)
                .body(body);
    }

    // Вспомогательный метод для запросов с аутентификацией
    protected RequestSpecification getAuthRequestSpec() {
        return given()
                .spec(requestSpec)
                .header(new Header("Authorization", "Bearer " + authToken));
    }
}

package steps;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import utils.DatabaseHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class CommonSteps {

    protected static RequestSpecification requestSpec;
    protected static ResponseSpecification responseSpec;
    protected static DatabaseHelper dbHelper;
    protected static String authToken;
    protected static Response response;

    private static final String BASE_URI = "http://localhost:8080/api";
    private static final String AUTH_ENDPOINT = "/auth/login";
    private static final String AUTH_LOGIN = "admin";
    private static final String AUTH_PASSWORD = "pwAdmin";

    private static final Map<String, Object> storedValues = new HashMap<>();

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URI;

        requestSpec = new RequestSpecBuilder()
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .build();

        responseSpec = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .build();

        dbHelper = new DatabaseHelper();
    }

    protected static RequestSpecification getAuthRequestSpec() {
        return given()
                .spec(requestSpec)
                .header(new Header("Authorization", "Bearer " + authToken));
    }

    protected static String resolvePath(String path) {
        Pattern pattern = Pattern.compile("\\{(\\w+)\\}");
        Matcher matcher = pattern.matcher(path);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String key = matcher.group(1);
            Object value = storedValues.get(key);
            if (value == null) {
                throw new IllegalArgumentException("No stored value found for: " + key);
            }
            matcher.appendReplacement(sb, Matcher.quoteReplacement(value.toString()));
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    @Given("I am authenticated")
    public void iAmAuthenticated() {
        if (authToken == null) {
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

    @When("I send POST request to {string} with body:")
    public static void iSendPostRequestWithBody(String path, String body) {
        var spec = authToken != null ? getAuthRequestSpec() : given().spec(requestSpec);
        response = spec.body(body).when().post(resolvePath(path));
    }

    @When("I send GET request to {string}")
    public void iSendGetRequest(String path) {
        var spec = authToken != null ? getAuthRequestSpec() : given().spec(requestSpec);
        response = spec.when().get(resolvePath(path));
    }

    @When("I store response id as {string}")
    public void iStoreResponseIdAs(String key) {
        int id = response.jsonPath().getInt("id");
        storedValues.put(key, id);
    }

    @Then("response status is {int}")
    public void responseStatusIs(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("response body has field {string} with value {string}")
    public void responseBodyHasFieldWithValue(String field, String value) {
        assertEquals(value, response.jsonPath().getString(field));
    }

    @Then("response body has field {string} not null")
    public void responseBodyHasFieldNotNull(String field) {
        assertNotNull(response.jsonPath().get(field));
    }

    @Then("response message is {string}")
    public void responseMessageIs(String expectedMessage) {
        assertEquals(expectedMessage, response.jsonPath().getString("message"));
    }
}

package steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class UserSteps {

    @Given("user with email {string} exists in database")
    public void userWithEmailExists(String email) {
        CommonSteps.dbHelper.deleteUserByEmail(email);
        CommonSteps.dbHelper.insertUser("Test", "User", email);
    }

    @Given("I have user data:")
    public void iHaveUserData(DataTable dataTable) {
        Map<String, String> data = dataTable.asMap(String.class, String.class);
        StringBuilder json = new StringBuilder("{");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            if (json.length() > 1) json.append(", ");
            json.append("\"").append(entry.getKey()).append("\": \"").append(entry.getValue()).append("\"");
        }
        json.append("}");
        CommonSteps.iSendPostRequestWithBody("/users", json.toString());
    }

    @Then("user exists in database with email {string}")
    public void userExistsInDatabaseWithEmail(String email) {
        Map<String, Object> user = CommonSteps.dbHelper.getUserByEmail(email);
        assertNotNull(user, "User not found in DB with email: " + email);
    }

    @Then("user does not exist in database with email {string}")
    public void userDoesNotExistInDatabase(String email) {
        assertNull(CommonSteps.dbHelper.getUserByEmail(email), "User should not exist in DB with email: " + email);
    }

    @Then("response body has fields:")
    public void responseBodyHasFields(DataTable dataTable) {
        Map<String, String> expectedFields = dataTable.asMap(String.class, String.class);
        for (Map.Entry<String, String> entry : expectedFields.entrySet()) {
            String field = entry.getKey();
            String expectedValue = entry.getValue();
            if ("notNull".equals(expectedValue)) {
                assertNotNull(CommonSteps.response.jsonPath().get(field), "Field " + field + " should not be null");
            } else {
                assertEquals(expectedValue, CommonSteps.response.jsonPath().getString(field));
            }
        }
    }

    @Then("I delete user with email {string}")
    public void iDeleteUserWithEmail(String email) {
        CommonSteps.dbHelper.deleteUserByEmail(email);
    }
}

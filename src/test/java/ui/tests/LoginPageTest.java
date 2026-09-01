package ui.tests;

import com.codeborne.selenide.Condition;
import config.UITestBase;
import org.junit.jupiter.api.Test;
import ui.pages.LoginPage;

import static com.codeborne.selenide.Condition.visible;

public class LoginPageTest extends UITestBase {

    private final LoginPage loginPage = new LoginPage();

    @Test
    public void loginPageShouldLoad() {
        loginPage.openPage();
        loginPage.loginByCardButton().shouldBe(visible);
        loginPage.loginByCardButton().click();

        loginPage.enterByCardHeader().shouldBe(visible);
    }
}

package config;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class UITestBase {

    //protected static final String BASE_URL = "https://online.sberbank.ru";

    @BeforeAll
    public static void uiSetup() {
        //Configuration.baseUrl = BASE_URL;
        Configuration.browser = "chrome";
        Configuration.headless = true;
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 10000;
    }

    @BeforeEach
    public void testSetup() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide()
                .includeSelenideSteps(true));
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
        SelenideLogger.removeListener("AllureSelenide");
    }
}

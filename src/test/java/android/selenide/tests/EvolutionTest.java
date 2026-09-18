package android.selenide.tests;

import android.selenide.pages.DetailPage;
import android.selenide.pages.MainPage;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.appium.SelenideAppium;
import config.AndroidDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.appium.ScreenObject.screen;
import static com.codeborne.selenide.Condition.visible;

public class EvolutionTest {

    private MainPage mainPage;

    @BeforeEach
    public void setup() {
        // 1. Указываем Selenide, что использовать для запуска
        Configuration.browser = AndroidDriverProvider.class.getName();

        // 2. Запускаем приложение (аналог driver = new AndroidDriver(...))
        SelenideAppium.launchApp();
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }

    @Test
    public void testTitle() {
        mainPage = screen(MainPage.class);
        mainPage.properties
                .shouldBe(visible)
                .shouldHave(Condition.text("Свойства"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Быстрое", "Большой"})
    public void openDetailPage(String propertyName) {
        mainPage = screen(MainPage.class);
        DetailPage detailPage = mainPage.propertyClick(propertyName);
        detailPage.getHeader(propertyName).should(exactText(propertyName));
        //android.widget.TextView[@text="Быстрое"]
        //android.widget.TextView[@text="Быстрое"]
    }
}
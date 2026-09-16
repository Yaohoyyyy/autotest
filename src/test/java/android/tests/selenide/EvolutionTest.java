package android.tests.selenide;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.appium.SelenideAppium;
import config.AndroidDriverProvider;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static com.codeborne.selenide.Condition.visible;
import static io.appium.java_client.AppiumBy.xpath;

public class EvolutionTest {

    @Test
    void testTitle() {
        // 1. Указываем Selenide, что использовать для запуска
        Configuration.browser = AndroidDriverProvider.class.getName();

        // 2. Запускаем приложение (аналог driver = new AndroidDriver(...))
        SelenideAppium.launchApp();

        // 3. Пишем тест в знакомом стиле Selenide
        // Автоматическое ожидание видимости элемента
        $(xpath("//android.widget.TextView[@text='Свойства']"))
                .shouldBe(visible) // Selenide сам подождёт, пока элемент появится
                .shouldHave(Condition.text("Свойства")); // и проверит текст
    }
}
package ui.tests.herokuapp;

import config.UITestBase;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.herokuapp.CommonComponentsPage;
import ui.pages.herokuapp.ScrollPage;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static org.assertj.core.api.Assertions.assertThat;

@Epic("Скролл")
public class ScrollTest extends UITestBase {

    private ScrollPage scrollPage = new ScrollPage();

    private CommonComponentsPage footer;

    @BeforeEach
    public void setup() {
        scrollPage = Selenide.page(new ScrollPage());
        footer = new CommonComponentsPage();
        scrollPage.openPage();
    }

    @Story("Скролл формы")
    @Test
    public void scrollForm() throws InterruptedException {
        Actions action = new Actions(Selenide.webdriver().driver().getWebDriver());
        action.scrollByAmount(0, 500).perform();
        Thread.sleep(5000);
    }

    @Test
    public void scrollFormByJS() throws InterruptedException {
        // Начальное количество элементов
        int initialCount = $$(".scroll .jscroll-added").size();

        // Скролл вниз
        actions().moveToElement($(".scroll")).click().build().perform();

        // Ждем появления нового контента
        SelenideElement newElement = $(".scroll .jscroll-added:last-child");
        newElement.shouldBe(visible);

        // Проверяем, что контент добавился
        int newCount = $$(".scroll .jscroll-added").size();
        Thread.sleep(5000);
    }

    @Test
    public void scrollFormByFooter() throws InterruptedException {
        footer.footer.scrollIntoView(true);
        Thread.sleep(5000);
    }
}

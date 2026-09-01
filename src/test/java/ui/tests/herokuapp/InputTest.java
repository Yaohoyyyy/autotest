package ui.tests.herokuapp;

import config.UITestBase;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.bs.A;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import ui.pages.herokuapp.InputsPage;

import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InputTest extends UITestBase {

    public InputsPage inputsPage;

    @BeforeEach
    public void setup() {
        inputsPage = Selenide.page(new InputsPage());
        inputsPage.openPage();
    }

    @Test
    public void inputNumberValue() throws InterruptedException {
        inputsPage.input.setValue("123s");
        //inputsPage.input.sendKeys("123");
        inputsPage.input.shouldHave(value("123"));
        inputsPage.input.sendKeys(Keys.ARROW_UP);
        Thread.sleep(5000);
    }

    @Test
    public void inputText() throws InterruptedException {
        //inputsPage.input.setValue("123");
        Actions action = new Actions(Selenide.webdriver().driver().getWebDriver());
        action.click(inputsPage.input).perform();

        Thread.sleep(5000);

        action.sendKeys("Petya").perform();

        inputsPage.input.sendKeys("Vasya");
        //Thread.sleep(5000);
        inputsPage.input.shouldHave(text("Vasya"));
    }

    @Test
    public void testWithRawElement() throws InterruptedException {
        // Получаем SelenideElement и преобразуем в WebElement
        org.openqa.selenium.WebElement rawElement = inputsPage.input.getWrappedElement();

        // Кликаем
        rawElement.click();

        // Отправляем клавиши
        rawElement.sendKeys("Vasya");
        Thread.sleep(5000);

        // Проверяем
        String value = rawElement.getAttribute("value");
        System.out.println("Value: '" + value + "'");
        assertEquals("", value);
    }
}

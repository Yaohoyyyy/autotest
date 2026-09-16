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
import static com.codeborne.selenide.Selenide.actions;
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
        Actions action = new Actions(WebDriverRunner.getWebDriver());
        action.click(inputsPage.input).perform();

        Thread.sleep(5000);

        action.sendKeys("2").perform();

        inputsPage.input.sendKeys("1");
        inputsPage.input.setValue("3");
        //Thread.sleep(5000);
        inputsPage.input.shouldHave(value("3"));
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

    @Test
    public void testActions() {
//        inputsPage.input.sendKeys(Keys.ARROW_UP);
        actions().
                moveToElement(inputsPage.input).
                click().
                sendKeys(Keys.ARROW_UP).
                perform();
        inputsPage.input.shouldHave(value("1"));
    }
}

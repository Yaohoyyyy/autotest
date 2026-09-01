package ui.tests.herokuapp;

import config.UITestBase;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.herokuapp.CheckboxesPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;

public class CheckboxesTest extends UITestBase {

    private CheckboxesPage checkboxesPage;

    @BeforeEach
    public void setup() {
        checkboxesPage = Selenide.page(new CheckboxesPage());
        checkboxesPage.openPage();
    }

    @Test
    public void checkBoxCliker() {
        checkboxesPage.checkbox_1.click();
        checkboxesPage.checkbox_2.click();

        checkboxesPage.checkbox_1.shouldBe(checked);
        checkboxesPage.checkbox_2.shouldBe(not(checked));
    }

    @Test
    public void defaultViewCheckboxes() {
        // Заголовок
        checkboxesPage.header.shouldHave(text("Checkboxes"));
        // Общее кол-во
        checkboxesPage.checkboxList.shouldHave(size(2));
        // Состояние
        checkboxesPage.checkbox_1.shouldNotBe(checked);
        checkboxesPage.checkbox_2.shouldBe(checked);
        // Лейблы
        //checkboxesPage.checkbox_1.shouldHave(text("checkbox 1"));
        //checkboxesPage.checkbox_2.shouldHave(text("checkbox 2"));

    }

}

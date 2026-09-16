package ui.tests.herokuapp;

import config.UITestBase;

import com.codeborne.selenide.Selenide;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.herokuapp.CheckboxesPage;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assumptions.*;
import static org.assertj.core.api.Assertions.*;

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
        checkboxesPage.header.shouldHave(exactText("Checkboxes"));
        // Общее кол-во
        checkboxesPage.checkboxList.shouldHave(size(2));
        // Состояние
        checkboxesPage.checkbox_1.shouldNotBe(checked);
        checkboxesPage.checkbox_2.shouldBe(checked);
        // Лейблы
        //checkboxesPage.checkbox_1.shouldHave(text("checkbox 1"));
        //checkboxesPage.checkbox_2.shouldHave(text("checkbox 2"));

    }

    @Test
    public void assumeTrueTest() {
        assumeTrue(true);
        checkboxesPage.checkbox_2.shouldBe(checked);
    }

    @Test
    public void assumeFalseTest() {
        assumeFalse(true);
        checkboxesPage.checkbox_2.shouldBe(checked);
    }

    @Test
    public void assumeThatTest() {
        assumingThat(true,
                () -> checkboxesPage.checkbox_1.shouldNotBe(visible));
        checkboxesPage.checkbox_2.shouldNotBe(checked);
    }

    @Test
    public void assertThatTest() {
        assertThat(checkboxesPage.checkboxList).hasSize(2);
    }

}

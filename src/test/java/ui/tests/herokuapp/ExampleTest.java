package ui.tests.herokuapp;

import com.codeborne.selenide.Condition;
import config.UITestBase;

import com.codeborne.selenide.Selenide;
import extentions.Logging;
import extentions.TimingExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.pages.herokuapp.DropdownPage;

import static com.codeborne.selenide.Condition.*;

@ExtendWith({Logging.class, TimingExtension.class})
public class ExampleTest extends UITestBase {

    private DropdownPage dropdownPage = new DropdownPage();

    @BeforeEach
    public void setup() {
        dropdownPage = Selenide.page(new DropdownPage());
        dropdownPage.openPage();
    }

    @Test
    public void test_1() throws InterruptedException {
        dropdownPage.select.shouldBe(visible);
    }

}

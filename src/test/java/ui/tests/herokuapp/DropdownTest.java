package ui.tests.herokuapp;

import config.UITestBase;

import com.codeborne.selenide.Selenide;
import extentions.Logging;
import extentions.TimingExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.pages.herokuapp.DropdownPage;

@ExtendWith({Logging.class, TimingExtension.class})
public class DropdownTest extends UITestBase {

    private DropdownPage dropdownPage = new DropdownPage();

    @BeforeEach
    public void setup() {
        dropdownPage = Selenide.page(new DropdownPage());
        dropdownPage.openPage();
    }

    @Test
    public void selectItem() throws InterruptedException {
        dropdownPage.select.selectOption("Option 2");
        Thread.sleep(4000);
    }

}

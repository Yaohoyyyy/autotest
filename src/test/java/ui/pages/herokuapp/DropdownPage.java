package ui.pages.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;

public class DropdownPage extends HerokuappPage {

    @FindBy(xpath = "//select[@id = 'dropdown']")
    public SelenideElement select;

    public DropdownPage() {
        super("/dropdown");
    }

}

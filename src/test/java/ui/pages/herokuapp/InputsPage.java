package ui.pages.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;

public class InputsPage extends HerokuappPage{

    @FindBy(xpath = "//h3")
    public SelenideElement header;

    @FindBy(xpath = "//input/preceding-sibling::*")
    public SelenideElement inputHeader;

    @FindBy(xpath = "//input")
    public SelenideElement input;

    public InputsPage() {
        super("/inputs");
    }

}

package ui.pages.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class CheckboxesPage extends HerokuappPage {

    @FindBy(xpath = "//h3")
    public SelenideElement header;

    @FindBy(xpath = "//input[@type = 'checkbox'][1]")
    public SelenideElement checkbox_1;

    @FindBy(xpath = "//input[@type = 'checkbox'][2]")
    public SelenideElement checkbox_2;

    //@FindBy(xpath = "//form[@id= 'checkboxes']/input")
    public ElementsCollection checkboxList = $$x("//form[@id= 'checkboxes']/input");

    public CheckboxesPage() {
        super("/checkboxes");
    }

}

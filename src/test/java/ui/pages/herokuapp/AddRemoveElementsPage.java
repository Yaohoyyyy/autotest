package ui.pages.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;

public class AddRemoveElementsPage extends HerokuappPage{
    @FindBy(xpath = "//h3")
    public SelenideElement header;

    @FindBy(xpath = "//button[text() = 'Add Element']")
    public SelenideElement addElementBtn;

    @FindBy(xpath = "//button[text() = 'Delete']")
    public SelenideElement deleteElementBtn;

    public AddRemoveElementsPage() {
        super("/add_remove_elements/");
    }

}

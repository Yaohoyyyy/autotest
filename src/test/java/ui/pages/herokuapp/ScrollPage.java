package ui.pages.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;

public class ScrollPage extends HerokuappPage {

    @FindBy(xpath = "//h3")
    public SelenideElement header;

    public ScrollPage() {
        super("/infinite_scroll");
    }
}

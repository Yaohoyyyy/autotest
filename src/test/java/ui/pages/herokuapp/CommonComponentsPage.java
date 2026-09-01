package ui.pages.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.page;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class CommonComponentsPage extends HerokuappPage {

    @FindBy(css = "#page-footer > div > div")
    public SelenideElement footerText;

    @FindBy(css = "#page-footer * a")
    public SelenideElement footerRef;

    //@FindBy(css = "#page-footer")
    @FindBy(xpath = "//div[@id = 'page-footer']")
    public SelenideElement footer;

    public CommonComponentsPage() {
        page(this);
    }

    public void checkFooter() {
        //footerText.should(text("Powered by"));

        assertEquals(getText(), "Powered by Elemental Selenium");
    }

    public String getText() {
        return footer.getText();
    }
}

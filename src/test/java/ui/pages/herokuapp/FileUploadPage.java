package ui.pages.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;

public class FileUploadPage extends HerokuappPage {

    public static final String BASE_URL = "https://the-internet.herokuapp.com/upload";

    @FindBy(xpath = "//h3")
    public SelenideElement header;

    public void openPage() {
        open(BASE_URL);
    }


}

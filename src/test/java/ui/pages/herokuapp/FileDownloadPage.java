package ui.pages.herokuapp;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Selenide.open;

public class FileDownloadPage extends HerokuappPage {

    @FindBy(xpath = "//h3")
    public SelenideElement header;

    @FindBy(css = "#content")
    public SelenideElement content;

    public FileDownloadPage() {
        super("/download");
    }

}

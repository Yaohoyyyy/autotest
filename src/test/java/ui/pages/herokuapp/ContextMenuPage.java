package ui.pages.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class ContextMenuPage extends HerokuappPage{

    @FindBy(xpath = "//div[@id = 'hot-spot']")
    public SelenideElement contextMenuFrame;

    public ContextMenuPage() {
        super("/context_menu");
    }

}

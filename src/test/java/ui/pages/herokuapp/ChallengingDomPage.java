package ui.pages.herokuapp;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.open;

public class ChallengingDomPage extends HerokuappPage {

    @FindBy(xpath = "//div[@class = 'large-10 columns']/table")
    public ElementsCollection table;

    @FindBy(xpath = "//div[@class = 'large-10 columns']/table//th")
    public ElementsCollection tableHeaders;

    public List<String> getTableHeaders() {
        List<String> list = new ArrayList<>();

        return list;
    }

    public ChallengingDomPage() {
        super("/challenging_dom");
    }
}

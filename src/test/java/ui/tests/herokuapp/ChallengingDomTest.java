package ui.tests.herokuapp;

import config.UITestBase;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.herokuapp.ChallengingDomPage;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.*;

public class ChallengingDomTest extends UITestBase {

    private ChallengingDomPage challengingDomPage;

    @BeforeEach
    public void setup() {
        challengingDomPage = Selenide.page(new ChallengingDomPage());
        challengingDomPage.openPage();
    }

    @Test
    public void tableTest() {
        challengingDomPage.tableHeaders.should(size(7));
        challengingDomPage.tableHeaders.should(texts("Lorem", "Ipsum", "Dolor", "Sit", "Amet", "Diceret", "Action"));
    }

}

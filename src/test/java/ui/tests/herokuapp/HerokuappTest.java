package ui.tests.herokuapp;

import config.UITestBase;
import org.junit.jupiter.api.Test;
import ui.pages.herokuapp.HerokuappPage;

public class HerokuappTest extends UITestBase {

    private final HerokuappPage herokuappPage = new HerokuappPage();

    @Test
    public void checkBoxCliker() {
        herokuappPage.openPage();
    }

}

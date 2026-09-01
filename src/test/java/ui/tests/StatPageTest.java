package ui.tests;

import config.UITestBase;
import org.junit.jupiter.api.Test;
import ui.pages.StatPage;

import java.math.BigDecimal;

import static com.codeborne.selenide.Condition.visible;

public class StatPageTest extends UITestBase {

    private final StatPage statPage = new StatPage();

    @Test
    public void statPageShouldLoad() {
        statPage.openPage();
        statPage.getTotalSum();
        //Long sum = statPage.getTotalSum();
        //System.out.println("sum: " + sum);
    }
}

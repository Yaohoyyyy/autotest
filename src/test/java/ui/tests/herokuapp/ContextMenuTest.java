package ui.tests.herokuapp;

import config.UITestBase;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.FindBy;
import ui.pages.herokuapp.CommonComponentsPage;
import ui.pages.herokuapp.ContextMenuPage;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ContextMenuTest extends UITestBase {

    private ContextMenuPage contextMenuPage = new ContextMenuPage();

    private CommonComponentsPage footer;

    @BeforeEach
    public void setup() {
        contextMenuPage = Selenide.page(new ContextMenuPage());
        footer = new CommonComponentsPage();
        // Альтернатива конструктору в CommonComponentsPage.java
        // footer = Selenide.page(new CommonComponentsPage());
        contextMenuPage.openPage();
    }

    @Test
    public void contextMenuClick() throws InterruptedException {
        contextMenuPage.contextMenuFrame.contextClick();

        Alert alert = switchTo().alert();
        String alertText = alert.getText();
        assertEquals("You selected a context menu", alertText);
        alert.accept();

    }

    @Test
    public void checkHotSpot() {
        contextMenuPage.contextMenuFrame.should(attribute("style", "border-style: dashed; border-width: 5px; width: 250px; height: 150px;"));
        contextMenuPage.contextMenuFrame.should(cssValue("border-style", "dashed"));
        contextMenuPage.contextMenuFrame.should(cssValue("border-top-style", "dashed"));
        contextMenuPage.contextMenuFrame.should(cssValue("color", "rgba(34, 34, 34, 1)"));
    }

    @Test
    public void checkCommonComponents() {
        assertEquals(footer.getText(), "Powered by Elemental Selenium");
        footer.footer.should(exactText("Powered by Elemental Selenium"));
    }

}

package ui.tests.herokuapp;

import config.UITestBase;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import extentions.Logging;
import extentions.TimingExtension;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.pages.herokuapp.AddRemoveElementsPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;

@Epic("Работа с элементами")
@Feature("Добавление/удаление")
@ExtendWith({Logging.class, TimingExtension.class})
public class AddRemoveElementsTest extends UITestBase {

    private AddRemoveElementsPage addRemoveElementsPage;

    @BeforeEach
    public void setup() {
        addRemoveElementsPage = Selenide.page(new AddRemoveElementsPage());
        addRemoveElementsPage.openPage();
    }

    @Test
    @Story("Дефолтное отображение")
    @Description("Проверка отображения формы по умолчанию")
    @Severity(SeverityLevel.MINOR)
    @Owner("Луковицын Олег")
    @TmsLink("/issue/190")
    @Issue("190")
    public void defaultViewForm() {
        addRemoveElementsPage.header.shouldHave(text("Add/Remove Elements"), visible);
        addRemoveElementsPage.addElementBtn.shouldBe(visible);
        addRemoveElementsPage.deleteElementBtn.shouldNotBe(visible);
    }

    @Test
    public void  addAndDeleteElements() {
        addRemoveElementsPage.addElementBtn.click();
        addRemoveElementsPage.deleteElementBtn.should(visible, text("Delete"));
        addRemoveElementsPage.deleteElementBtn.click();
        addRemoveElementsPage.deleteElementBtn.shouldNot(visible);
    }

}

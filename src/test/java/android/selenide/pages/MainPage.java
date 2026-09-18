package android.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.appium.ScreenObject.screen;
import static com.codeborne.selenide.appium.SelenideAppium.$x;

public class MainPage {

    @FindBy(xpath = "//android.widget.TextView[@text='Свойства']")
    public SelenideElement properties;

    public DetailPage propertyClick(String propertyName) {
        $x("//android.widget.TextView[@text=\"" + propertyName + "\"]").click();
        return screen(DetailPage.class);
    }

}

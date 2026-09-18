package android.selenide.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.appium.SelenideAppium.$x;

public class DetailPage {

    public SelenideElement getHeader(String headerName) {
        return $x("//android.widget.TextView[@text=\"" + headerName + "\"]");
    }
}

package ui.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {

    private static final String URL = "/CSAFront/index.do";

    public void openPage() {
        open(URL);
    }

    public SelenideElement loginByCardButton() {
        return $x("//button[@aria-label = 'Войти по номеру карты']");
    }

    public SelenideElement loginByLoginButton() {
        return $x("//button[@aria-label = 'По логину и паролю']");
    }

    public SelenideElement enterByCardHeader() {
        return $x("//button[text() = 'Вход по номеру карты']");
    }
}

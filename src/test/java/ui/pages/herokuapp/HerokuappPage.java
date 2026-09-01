package ui.pages.herokuapp;

import config.UITestBase;

import static com.codeborne.selenide.Selenide.open;

public class HerokuappPage extends UITestBase {

    static final String BASE_URL = "https://the-internet.herokuapp.com";

    private final String path;

    public HerokuappPage() {
        this.path = "";
    }

    public HerokuappPage(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    };

    public void openPage() {
        open(BASE_URL + getPath());
    }
}

package ui.tests.herokuapp;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.pages.herokuapp.FileUploadPage;

public class FileUploadTest extends HerokuappTest{

    private FileUploadPage fileUploadPage = new FileUploadPage();

    @BeforeEach
    public void setup() {
        fileUploadPage = Selenide.page(new FileUploadPage());
        fileUploadPage.openPage();
    }

    @Test
    public void uploadFile() {

    }

}

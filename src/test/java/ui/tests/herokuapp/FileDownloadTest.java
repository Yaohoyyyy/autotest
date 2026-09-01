package ui.tests.herokuapp;

import com.codeborne.selenide.Selenide;
import config.UITestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ui.pages.herokuapp.FileDownloadPage;
import io.qameta.allure.*;

@Epic("Работа с файлами")
@Feature("Скачивание")
public class FileDownloadTest extends UITestBase {

    private FileDownloadPage fileDownloadPage = new FileDownloadPage();

    @BeforeEach
    public void setup() {
        fileDownloadPage = Selenide.page(new FileDownloadPage());
        fileDownloadPage.openPage();
    }

    @Test
    @Tag("allure")
    @DisplayName("Скачивание 2 файлов")
    @Severity(SeverityLevel.NORMAL)
    public void downloadFile() throws InterruptedException {
        fileDownloadPage.content.$x(".//a[@href = 'download/upload-test.txt']").click();
        fileDownloadPage.content.$x(".//a[@href = 'download/sample-1mb-test-file.dat']").click();
        Thread.sleep(5000);
    }
}

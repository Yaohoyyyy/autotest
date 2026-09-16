package android.tests.selenium;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.net.URL;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvolutionTest {
    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("emulator-5554"); // Замените на ваш deviceName
        // Для запуска установленного приложения используйте appPackage и appActivity
        options.setAppPackage("com.example.evolutionrules"); // Замените на ваш
        options.setAppActivity("com.example.evolutionrules.MainActivity"); // Замените на вашу
        options.setNoReset(true);

        driver = new AndroidDriver(new URL("http://localhost:4723"), options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void testTitleText() {
        WebElement title = driver.findElement(By.xpath("//android.widget.TextView[@text=\"Свойства\"]"));
        assertEquals("Свойства", title.getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

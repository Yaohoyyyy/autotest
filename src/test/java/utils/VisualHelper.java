package utils;

import com.codeborne.selenide.WebDriverRunner;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VisualHelper {

    private static final String BASE = "src/test/resources/screenshots/";

    // Делает полный скриншот страницы и сохраняет в actual/
    public static BufferedImage takeFullPageScreenshot(String testName) {
        Screenshot screenshot = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(WebDriverRunner.getWebDriver());

        BufferedImage image = screenshot.getImage();
        save(image, BASE + "actual/" + testName + ".png");
        return image;
    }

    // Сравнивает actual с etalon, сохраняет diff и возвращает ImageDiff
    public static ImageDiff compareWithEtalon(String testName) throws IOException {
        BufferedImage actual = takeFullPageScreenshot(testName);
        BufferedImage etalon = ImageIO.read(new File(BASE + "etalon/" + testName + ".png"));

        ImageDiff diff = new ImageDiffer().makeDiff(etalon, actual);

        if (diff.hasDiff()) {
            save(diff.getMarkedImage(), BASE + "diff/" + testName + "_diff.png");
        }
        return diff;
    }

    private static void save(BufferedImage image, String path) {
        try {
            File file = new File(path);
            file.getParentFile().mkdirs();
            ImageIO.write(image, "png", file);
        } catch (IOException e) {
            throw new RuntimeException("Failed to save screenshot: " + path, e);
        }
    }
}
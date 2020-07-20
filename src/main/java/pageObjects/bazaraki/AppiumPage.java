package pageObjects.bazaraki;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverFactory;

public class AppiumPage {
    AppiumDriver<?> driver;

    public AppiumPage() {
        driver = WebDriverFactory.INSTANCE.getAndroidDriver();
    }

    protected WebDriverWait getWebDriverWait(int seconds) {
        return new WebDriverWait(driver, seconds);
    }
}

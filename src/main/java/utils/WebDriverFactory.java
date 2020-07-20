package utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public enum WebDriverFactory {
    INSTANCE;

    private static final EnvironmentConfigurator environmentConfigurator = EnvironmentConfigurator.INSTANCE;
    private ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
    private ThreadLocal<AndroidDriver<?>> androidDriver = new ThreadLocal<>();

    public WebDriver getWebDriver() {
        if (driver.get() == null || driver.get().getSessionId() == null) {
            driver.set(startWebDriver());
        }
        return driver.get();
    }

    public AndroidDriver<?> getAndroidDriver() {
        if (androidDriver.get() == null || androidDriver.get().getSessionId() == null) {
            androidDriver.set(startAndroidDriver());
        }
        return androidDriver.get();
    }

    private RemoteWebDriver startWebDriver() {
        String runEnv = System.getProperty("test.browser", "local");
        RemoteWebDriver remoteDriver;
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", false);
        switch (runEnv) {
            case ("chrome"):
                capabilities.setBrowserName("chrome");
                capabilities.setVersion("84.0");
                remoteDriver = new RemoteWebDriver(getAppURL(), capabilities);
                break;
            case ("firefox"):
                capabilities.setBrowserName("firefox");
                capabilities.setVersion("78.0");
                remoteDriver = new RemoteWebDriver(getAppURL(), capabilities);
                break;
            case ("opera"):
                capabilities.setBrowserName("opera");
                capabilities.setVersion("69.0");
                remoteDriver = new RemoteWebDriver(getAppURL(), capabilities);
                break;
            default:
                WebDriverManager.chromedriver().setup();
                remoteDriver = new ChromeDriver();
                break;
        }
        remoteDriver.manage().window().fullscreen();
        return remoteDriver;
    }

    private AndroidDriver<?> startAndroidDriver() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, environmentConfigurator.getAppiumVersion());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, environmentConfigurator.getAndroidVersion());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,"Appium");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, environmentConfigurator.getAndroidDeviceName());
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
        capabilities.setCapability("newCommandTimeout", 2000);
        return new AndroidDriver<>(environmentConfigurator.getAppiumServerUrl(), capabilities);
    }

    public void setImplicitWait(long seconds) {
        getWebDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    private URL getAppURL() {
        String openUrl = environmentConfigurator.getWebDriverHubUrl();
        try {
            return URI.create(openUrl).toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopDriver() {
        try {
            if (driver.get() != null) {
                driver.get().quit();
                driver.remove();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void stopMobileDriver() {
        if (androidDriver.get() != null) {
            androidDriver.get().quit();
            androidDriver.remove();
        }
    }
}

package amazon;

import com.codeborne.selenide.WebDriverRunner;
import helpers.amazon.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WebDriverFactory;

public abstract class TestBase {
    private AmazonSearchHelper amazonSearchHelper;
    private AmazonLoginHelper amazonLoginHelper;
    private NavigationHelper navigationHelper;
    private AmazonBasketHelper amazonBasketHelper;
    private AmazonSearchResultsHelper amazonSearchResultsHelper;
    private AmazonItemHelper amazonItemHelper;

    @BeforeMethod
    public void beforeMethod() {
        WebDriver webDriver = WebDriverFactory.INSTANCE.getWebDriver();
        WebDriverRunner.setWebDriver(webDriver);
    }

    @AfterMethod
    public void afterMethod() {
        WebDriverFactory.INSTANCE.stopDriver();
    }

    protected AmazonSearchHelper getAmazonSearchHelper() {
        if (amazonSearchHelper == null) {
            amazonSearchHelper = new AmazonSearchHelper();
        }
        return amazonSearchHelper;
    }

    protected AmazonLoginHelper getAmazonLoginHelper() {
        if (amazonLoginHelper == null) {
            amazonLoginHelper = new AmazonLoginHelper();
        }
        return amazonLoginHelper;
    }

    protected NavigationHelper getNavigationHelper() {
        if (navigationHelper == null) {
            navigationHelper = new NavigationHelper();
        }
        return navigationHelper;
    }

    protected AmazonBasketHelper getAmazonBasketHelper() {
        if (amazonBasketHelper == null) {
            amazonBasketHelper = new AmazonBasketHelper();
        }
        return amazonBasketHelper;
    }

    protected AmazonSearchResultsHelper getAmazonSearchResultsHelper() {
        if (amazonSearchResultsHelper == null) {
            amazonSearchResultsHelper = new AmazonSearchResultsHelper();
        }
        return amazonSearchResultsHelper;
    }

    public AmazonItemHelper getAmazonItemHelper() {
        if (amazonItemHelper == null) {
            amazonItemHelper = new AmazonItemHelper();
        }
        return amazonItemHelper;
    }
}

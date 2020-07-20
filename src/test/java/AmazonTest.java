import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.WebDriverFactory;

public class AmazonTest extends TestBase {

    @BeforeMethod
    public void beforeTest() {
        WebDriver webDriver = WebDriverFactory.INSTANCE.getWebDriver();
        WebDriverRunner.setWebDriver(webDriver);
    }

    @AfterMethod
    public void afterTest() {
        WebDriverFactory.INSTANCE.stopDriver();
    }

    @Test
    public void amazonTaskTest() {
        getAmazonHelper().navigateToAmazon();
        getAmazonHelper().login();
        getAmazonHelper().searchFor("iphone 11 256Gb black");
        SelenideElement cheapestGood = getAmazonHelper().findCheapestGood();
        getAmazonHelper().selectItem(cheapestGood);
        getAmazonHelper().addToBasket();
        getAmazonHelper().openBasket();
        getAmazonHelper().removeGoodsFromBasket();
        getAmazonHelper().logout();
    }
}

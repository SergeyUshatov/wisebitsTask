package amazon;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.WebDriverFactory;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AmazonTest extends TestBase {

    @BeforeMethod
    public void beforeTest() {
        WebDriver webDriver = WebDriverFactory.INSTANCE.getWebDriver();
        WebDriverRunner.setWebDriver(webDriver);
        getNavigationHelper().navigateToAmazon();
        getAmazonLoginHelper().login();
    }

    @AfterMethod
    public void afterTest() {
        getAmazonLoginHelper().logout();
        WebDriverFactory.INSTANCE.stopDriver();
    }

    @Test(dataProvider = "task data")
    public void amazonTaskTest(String searchCriteria) {
        getAmazonSearchHelper().searchFor(searchCriteria);
        List<SelenideElement> itemsMatchCriteria = getAmazonSearchResultsHelper()
                .getSearchResultsItemsThatMatchCriteria(searchCriteria);

        assertThat(itemsMatchCriteria, is(not(empty())));

        SelenideElement cheapestItem = getAmazonSearchResultsHelper().findCheapestItemOf(itemsMatchCriteria);
        assertThat(cheapestItem, is(notNullValue()));
        String itemTitle = getAmazonSearchResultsHelper().getItemTitle(cheapestItem);

        getAmazonSearchResultsHelper().selectItem(cheapestItem);
        String itemTitleOnDetails = getAmazonItemHelper().getItemTitle();
        assertThat(itemTitle, equalTo(itemTitleOnDetails));

        getAmazonItemHelper().addToBasket();
        getAmazonSearchHelper().openBasket();
        MatcherAssert.assertThat(getAmazonBasketHelper().isItemInBasket(itemTitle), is(true));

        getAmazonBasketHelper().removeItemFromBasket(itemTitle);
        MatcherAssert.assertThat(getAmazonBasketHelper().isBasketEmpty(), is(true));
    }

    @DataProvider(name = "task data")
    private Object[][] testData() {
        return new Object[][] {
                {"samsung galaxy s10"}
        };
    }
}

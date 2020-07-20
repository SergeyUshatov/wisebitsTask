package pageObjects.amazon;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.WebElementUtils;

import static com.codeborne.selenide.Selenide.$$;

public class AmazonSearchResultsPage {

    public ElementsCollection getAllPricesOnThePage() {
        return $$(By.className("a-price"));
    }

    public ElementsCollection getAllPricesOnThePage(Double price) {
        String priceStr = String.valueOf(price).replace("\\.", "\n");
        return WebElementUtils.collectionByPartialText(priceStr);
    }

    public AmazonGoodsPage clickOnPrice(SelenideElement element) {
        element.scrollTo().click();
        return new AmazonGoodsPage();
    }
}

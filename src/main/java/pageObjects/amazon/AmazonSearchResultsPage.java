package pageObjects.amazon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AmazonSearchResultsPage extends AmazonBasePage {

    private SelenideElement resultsContainer = $(By.xpath("//*[@data-component-type='s-search-results']"));
    private ElementsCollection resultItems = $$(By.xpath("//*[@data-component-type='s-search-result']"));
    private By itemTitle = By.xpath(".//a[contains(@class, 'a-text-normal')]");
    private By itemPrice = By.className("a-price");

    public ElementsCollection getAllPricesOnThePage() {
        return $$(By.className("a-price"));
    }

    public List<SelenideElement> getItems() {
        resultsContainer.waitUntil(Condition.visible, 20000);
        return resultItems;
    }

    public List<SelenideElement> getItemsThatMatch(String criteria) {
        return getItems().stream()
                .filter(it -> {
                    String s = it
                            .find(By.xpath(".//a[contains(@class, 'a-text-normal')]"))
                            .getText()
                            .toLowerCase();
                    return s.contains(criteria); })
                .collect(Collectors.toList());
    }

    public Double getPriceOf(SelenideElement base) {
        SelenideElement priceElement = getPriceElement(base);
        return priceElement == null ? Double.MAX_VALUE :
                Double.parseDouble(priceElement.getText()
                        .substring(1)
                        .replaceAll("\n", "."));
    }

    private SelenideElement getPriceElement(SelenideElement base) {
        return base.$(itemPrice).exists() ? base.$(itemPrice) : null;
    }

    public AmazonItemPage clickOn(SelenideElement element) {
        SelenideElement itemTitle = getItemTitle(element);
        itemTitle.click();
        itemTitle.waitUntil(Condition.disappear, 10000);
        return new AmazonItemPage();
    }

    public SelenideElement getItemTitle(SelenideElement element) {
        return element.$(itemTitle);
    }
}

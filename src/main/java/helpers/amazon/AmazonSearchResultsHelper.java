package helpers.amazon;

import com.codeborne.selenide.SelenideElement;
import pageObjects.amazon.AmazonSearchResultsPage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AmazonSearchResultsHelper {
    private AmazonSearchResultsPage resultsPage;

    public AmazonSearchResultsHelper() {
        resultsPage = new AmazonSearchResultsPage();
    }

    public List<SelenideElement> getSearchResultsItemsThatMatchCriteria(String criteria) {
        return resultsPage.getItemsThatMatch(criteria);
    }

    public SelenideElement findCheapestItemOf(List<SelenideElement> items) {
        return items.stream()
                .collect(Collectors.toMap(
                        it -> it,
                        it -> resultsPage.getPriceOf(it)
                )).entrySet().stream()
                .min(Map.Entry.comparingByValue(Double::compareTo))
                .get()
                .getKey();
    }

    public String getItemTitle(SelenideElement item) {
        return resultsPage.getItemTitle(item).getText();
    }

    public void selectItem(SelenideElement element) {
        resultsPage.clickOn(element);
    }
}

package helpers;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import pageObjects.amazon.*;
import utils.EnvironmentConfigurator;

public class AmazonHelper {
    AmazonLoginPage amazonLoginPage;
    AmazonPage amazonPage;
    AmazonSearchResultsPage resultsPage;
    AmazonGoodsPage amazonItemPage;
    AmazonBasketPage amazonBasketPage;

    public AmazonHelper() {
        amazonLoginPage = new AmazonLoginPage();
        amazonPage = new AmazonPage();
    }

    public void navigateToAmazon() {
        amazonLoginPage.navigate();
    }

    public void login() {
        amazonLoginPage.startLogin();
        amazonLoginPage.fillUsername(EnvironmentConfigurator.INSTANCE.getTestUserName());
        amazonLoginPage.fillPassword(EnvironmentConfigurator.INSTANCE.getTestUserPassword());
        amazonPage = amazonLoginPage.submitLogin();
    }

    public void searchFor(String criteria) {
        resultsPage = amazonPage.search(criteria);
    }

    public SelenideElement findCheapestGood() {
        ElementsCollection elements = resultsPage.getAllPricesOnThePage();
        Double minPrice = elements.stream()
                .map(it -> Double
                        .parseDouble(it.text()
                                .substring(1)
                                .replaceAll("\n", "."))
                )
                .sorted()
                .distinct()
                .mapToDouble(d -> d)
                .min().orElse(Double.MIN_VALUE);

        String minPriceStr = String.valueOf(minPrice).replaceAll("\\.", "\n");
        SelenideElement firstMin = elements.stream()
                .filter(it -> it.text().contains(minPriceStr)).findFirst().orElse(null);
        assert firstMin != null;
        return firstMin;
    }

    public void selectItem(SelenideElement element) {
        amazonItemPage = resultsPage.clickOnPrice(element);
    }

    public void openBasket() {
        amazonPage = new AmazonPage();
        amazonBasketPage = amazonPage.clickOnBasket();
    }

    public void removeGoodsFromBasket() {
        amazonBasketPage.removeItem();
    }

    public void logout() {
        amazonPage.hoverAccount();
        amazonPage.clickOnSignOut();
    }

    public void addToBasket() {
        amazonItemPage.addToCart();
    }
}

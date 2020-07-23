package helpers.amazon;

import pageObjects.amazon.AmazonPage;
import pageObjects.amazon.AmazonSearchResultsPage;

public class AmazonSearchHelper {

    private AmazonPage amazonPage;

    public AmazonSearchHelper() {
        amazonPage = new AmazonPage();
    }

    public void searchFor(String criteria) {
        amazonPage.search(criteria);
    }

    public void openBasket() {
        amazonPage = new AmazonPage();
        amazonPage.clickOnBasket();
    }

}

package helpers.amazon;

import pageObjects.amazon.AmazonItemPage;

public class AmazonItemHelper {
    private AmazonItemPage amazonItemPage;

    public AmazonItemHelper() {
        amazonItemPage = new AmazonItemPage();
    }

    public void addToBasket() {
        amazonItemPage.addToCart();
    }

    public String getItemTitle() {
        return amazonItemPage.getItemTitle().getText();
    }
}

package helpers.amazon;

import pageObjects.amazon.AmazonBasketPage;

public class AmazonBasketHelper {
    private AmazonBasketPage amazonBasketPage;

    public AmazonBasketHelper() {
        amazonBasketPage = new AmazonBasketPage();
    }

    public void removeItemFromBasket(String itemTitle) {
        amazonBasketPage.removeItem(itemTitle);
    }

    public boolean isItemInBasket(String itemTitle) {
        return amazonBasketPage.findItemTitle(itemTitle).exists();
    }

    public boolean isBasketEmpty() {
        return amazonBasketPage.getEmptyBasketMessageElement().exists();
    }
}

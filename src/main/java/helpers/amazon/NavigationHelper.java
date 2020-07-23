package helpers.amazon;

import pageObjects.amazon.AmazonLoginPage;

public class NavigationHelper {
    private AmazonLoginPage amazonLoginPage;

    public NavigationHelper() {
        amazonLoginPage = new AmazonLoginPage();
    }

    public void navigateToAmazon() {
        amazonLoginPage.navigate();
    }
}

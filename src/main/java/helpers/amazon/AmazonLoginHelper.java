package helpers.amazon;

import pageObjects.amazon.AmazonLoginPage;
import pageObjects.amazon.AmazonPage;
import utils.EnvironmentConfigurator;

public class AmazonLoginHelper {
    AmazonLoginPage amazonLoginPage;
    AmazonPage amazonPage;

    public AmazonLoginHelper() {
        amazonLoginPage = new AmazonLoginPage();
    }

    public void login() {
        amazonLoginPage.startLogin();
        amazonLoginPage.fillUsername(EnvironmentConfigurator.INSTANCE.getTestUserName());
        amazonLoginPage.fillPassword(EnvironmentConfigurator.INSTANCE.getTestUserPassword());
        amazonPage = amazonLoginPage.submitLogin();
    }

    public void logout() {
        amazonPage.hoverAccount();
        amazonPage.clickOnSignOut();
    }

    public boolean isUserLoggedIn() {
        return amazonPage.getLoggedUserName().exists();
    }
}

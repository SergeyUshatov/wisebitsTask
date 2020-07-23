package pageObjects.amazon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.EnvironmentConfigurator;
import utils.WebElementUtils;

import static com.codeborne.selenide.Selenide.$;
import static utils.Constants.*;

public class AmazonPage extends AmazonBasePage {

    private SelenideElement navMenu = $(By.id("nav-hamburger-menu"));
    private SelenideElement navAccount = $(By.id("nav-link-accountList"));
    private SelenideElement signOut = $(By.id("nav-item-signout"));
    private SelenideElement yourAccount = WebElementUtils.byText("Your Account");
    private SelenideElement searchField = $(By.id("twotabsearchtextbox"));
    private SelenideElement cart = $(By.id("nav-cart"));

    public void clickOnSignOut() {
        signOut.waitUntil(Condition.visible, WAIT_10_SEC);
        signOut.click();
    }

    public AmazonSearchResultsPage search(String criteria) {
        searchField.waitUntil(Condition.enabled, WAIT_10_SEC)
                .setValue(criteria)
                .pressEnter();
        return new AmazonSearchResultsPage();
    }

    public AmazonBasketPage clickOnBasket() {
        cart.click();
        return new AmazonBasketPage();
    }

    public void hoverAccount() {
        navAccount.hover();
        yourAccount.waitUntil(Condition.visible,WAIT_20_SEC, WAIT_100_MILISEC);
    }

    public SelenideElement getLoggedUserName() {
        return $(new Selectors.ByText("Hello, " + EnvironmentConfigurator.INSTANCE.getUserNameOnUi()));
    }
}

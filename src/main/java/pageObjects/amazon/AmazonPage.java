package pageObjects.amazon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.WebElementUtils;

import static com.codeborne.selenide.Selenide.$;

public class AmazonPage {

    private SelenideElement navMenu = $(By.id("nav-hamburger-menu"));
    private SelenideElement navAccount = $(By.id("nav-link-accountList"));
    private SelenideElement signOut = $(By.id("nav-item-signout"));
    private SelenideElement yourAccount = WebElementUtils.byText("Your Account");
    private SelenideElement searchField = $(By.id("twotabsearchtextbox"));
    private SelenideElement cart = $(By.id("nav-cart"));

    public void clickOnSignOut() {
        signOut.waitUntil(Condition.visible, 10000);
        signOut.click();
    }

    public AmazonSearchResultsPage search(String criteria) {
        searchField.setValue(criteria);
        searchField.pressEnter();
        return new AmazonSearchResultsPage();
    }

    public AmazonBasketPage clickOnBasket() {
        cart.click();
        return new AmazonBasketPage();
    }

    public void hoverAccount() {
        navAccount.hover();
        yourAccount.waitUntil(Condition.visible,20, 1);
    }
}

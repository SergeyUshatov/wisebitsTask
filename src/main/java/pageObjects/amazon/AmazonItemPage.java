package pageObjects.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AmazonItemPage extends AmazonBasePage {
    private SelenideElement addToCart = $(By.id("add-to-cart-button"));
    private SelenideElement itemTitle = $(By.id("title"));

    public void addToCart() {
        addToCart.click();
    }

    public SelenideElement getItemTitle() {
        return itemTitle;
    }
}

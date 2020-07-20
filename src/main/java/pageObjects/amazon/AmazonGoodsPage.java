package pageObjects.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AmazonGoodsPage {
    private SelenideElement addToCart = $(By.id("add-to-cart-button"));

    public void addToCart() {
        addToCart.click();
    }
}

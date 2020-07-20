package pageObjects.amazon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AmazonBasketPage {
    private SelenideElement delete = $(By.xpath("//input[@value='Delete']"));

    public void removeItem() {
        delete.click();
        delete.waitUntil(Condition.disappear, 5000);
    }
}

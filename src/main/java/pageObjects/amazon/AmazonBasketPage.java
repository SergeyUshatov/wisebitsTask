package pageObjects.amazon;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.Constants;

import static com.codeborne.selenide.Selenide.$;
import static utils.Constants.RETRY_1_SEC;

public class AmazonBasketPage extends AmazonBasePage {
    private By delete = By.xpath(".//input[@value='Delete']");
    private By container = By.xpath(".//ancestor::div[contains(@class, 'sc-list-item-content')]");
    private SelenideElement emptyBasketMsg = $(By.xpath("//*[contains(text(),'Basket is empty')]"));

    public SelenideElement findItemTitle(String itemTitle) {
        return getTitleElement(itemTitle).waitUntil(Condition.visible, Constants.WAIT_20_SEC);
    }

    public SelenideElement getTitleElement(String itemTitle) {
        return $(new Selectors.ByText(itemTitle));
    }

    public void removeItem(String itemTitle) {
        SelenideElement itemContainer = getItemContainer(findItemTitle(itemTitle));
        SelenideElement deleteElement = itemContainer.$(delete);
        deleteElement.click();
        deleteElement.waitUntil(Condition.disappear, Constants.WAIT_10_SEC);
    }

    private SelenideElement getItemContainer(SelenideElement element) {
        return element.$(container).waitUntil(Condition.visible, Constants.WAIT_20_SEC);
    }

    public SelenideElement getEmptyBasketMessageElement() {
        return emptyBasketMsg.waitUntil(Condition.appear, Constants.WAIT_10_SEC, RETRY_1_SEC);
    }
}

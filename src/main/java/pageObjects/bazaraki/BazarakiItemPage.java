package pageObjects.bazaraki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BazarakiItemPage extends AppiumPage {
    public String getCurrentUrl() {
        getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='item__details']")));
        return driver.getCurrentUrl();
    }

    public WebElement getItemIdElement() {
        return driver.findElement(By.xpath("//*[@class='item__details']"));
    }

    public void clickOnAddToFavorites() {
        WebElement element = driver.findElement(By.xpath("//*[@class='shell item']//*[contains(@class,'js-add-favorites')]"));
        element.click();
    }


}

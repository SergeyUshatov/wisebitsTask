package pageObjects.bazaraki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class BazarakiSearchResultsPage extends AppiumPage {

    public List<?> getResultItems() {
        String xpath = "//*[@itemtype='http://schema.org/Product']";
        getWebDriverWait(20).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
        return driver.findElementsByXPath(xpath);
    }

    public WebElement getPhotosElementIn(WebElement element) {
        return element.findElement(By.xpath(".//*[@class='photo-commodities']"));
    }

    public WebElement getPriceElementIn(WebElement element) {
        return element.findElement(By.xpath(".//meta[@itemprop='price']"));
    }

    public WebElement getDateElement(WebElement element) {
        return element.findElement(By.xpath(".//*[@class='time-like']"));
    }
}

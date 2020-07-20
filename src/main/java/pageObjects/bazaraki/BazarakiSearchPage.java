package pageObjects.bazaraki;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.WebDriverFactory;

import java.util.List;

public class BazarakiSearchPage extends AppiumPage {
    AppiumDriver<?> driver;

    public BazarakiSearchPage() {
        driver = WebDriverFactory.INSTANCE.getAndroidDriver();
    }

    public void getCategoryElement(String category) {
        WebElement element = driver.findElement(By.xpath("//*[contains(text(),\"" + category + "\")]/following-sibling::a"));
        element.click();
    }

    public void getSubCategoryElement(String subCategory) {
        WebElement element = getWebDriverWait(20)
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//li/*[contains(text(),\"" + subCategory +"\")]//ancestor::a")));
        element.click();
    }

    public void selectFillMinPrice(int minPrice) {
        WebElement from = driver.findElement(By.xpath("//*[@data-name='price_min']"));
        from.click();
        List<?> prices = driver.findElements(By.xpath("//li[contains(@class, 'popover__item')]"));
        WebElement price = getPriceElementFromListOfPrices(prices, String.valueOf(minPrice));
        price.click();

    }

    private WebElement getPriceElementFromListOfPrices(List<?> prices, String price) {
        return (WebElement) prices.stream()
                .filter(it -> ((WebElement)it).getText().replaceAll("\\.", "").equals(price) )
                .findFirst()
                .get();
    }

    public void selectFillMaxPrice(int maxPrice) {
        WebElement to = driver.findElement(By.xpath("//*[@data-name='price_max']"));
        to.click();
        List<?> prices = driver.findElements(By.xpath("//li[contains(@class, 'popover__item')]"));
        WebElement price = getPriceElementFromListOfPrices(prices, String.valueOf(maxPrice));
        price.click();
    }

    public void selectDistrict(String district) {
        WebElement element = getWebDriverWait(20)
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//li[text()=\"" + district + "\"]/span")));
        element.click();
    }

    public void fillQueryField(String query) {
        WebElement q = driver.findElement(By.xpath("//input[@id='header-form-search-input']"));
        q.sendKeys(query + Keys.ENTER);
    }

    public void clickOnCities() {
        WebElement citiesFiller = driver.findElement(By.xpath("//*[contains(text(), 'City')]"));
        scrollTo(citiesFiller);
        citiesFiller.click();
    }

    private void scrollTo(WebElement citiesFiller) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", citiesFiller);
    }

    public void clickOnSubmit() {
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    public void clickOnSearch() {
        WebElement e = driver.findElement(By.xpath("//*[contains(@class,'filter-popup__footer')]"));
        e.click();
    }

    public void clickOnFilters() {
        WebElement element = getWebDriverWait(20)
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//*[text()='Filters']")));
        element.click();
    }
}

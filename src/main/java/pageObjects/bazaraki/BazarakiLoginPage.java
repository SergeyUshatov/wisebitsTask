package pageObjects.bazaraki;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BazarakiLoginPage extends AppiumPage {

    public void selectPrivacyCheckbox() {
        getWebDriverWait(20)
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//input[@type='checkbox']"))).click();
    }

    public void clickOnContinueButton() {
        driver.findElement(By.xpath("//button[@name='validate']")).click();
    }

    public WebElement getErrorMessage() {
        return getWebDriverWait(20)
                .until(ExpectedConditions
                        .elementToBeClickable(By.xpath("//*[contains(@class, 'validation__error')]")));
    }
}

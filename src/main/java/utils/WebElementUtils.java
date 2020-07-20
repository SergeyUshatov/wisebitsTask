package utils;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WebElementUtils {

    public static SelenideElement byText(String text) {
        return $(By.xpath(".//*[text()=\"" + text + "\"]"));
    }

    public static SelenideElement byPartialText(String text) {
        return $(By.xpath(".//*[contains(text(), \"" + text + "\")]"));
    }

    public static ElementsCollection collectionByPartialText(String text) {
        return $$(By.xpath(".//*[contains(text(), \"" + text + "\")]"));
    }
}

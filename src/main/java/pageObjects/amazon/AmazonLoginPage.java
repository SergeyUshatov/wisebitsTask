package pageObjects.amazon;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import utils.EnvironmentConfigurator;
import utils.WebElementUtils;

import static com.codeborne.selenide.Selenide.$;

public class AmazonLoginPage extends AmazonBasePage {

    private SelenideElement signIn = $(By.xpath("user.name"));
    private SelenideElement usernameField = $(By.name("email"));
    private SelenideElement submitUserNAme = $(By.id("continue"));
    private SelenideElement passwordField = $(By.name("password"));
    private SelenideElement signInSubmit = $(By.id("signInSubmit"));

    public AmazonLoginPage navigate() {
        super.navigate(EnvironmentConfigurator.INSTANCE.getDesktopTestUrl());
        return this;
    }

    public void fillUsername(String username) {
        usernameField.setValue(username);
        submitUserNAme.click();
    }

    public void fillPassword(String password) {
        passwordField.setValue(password);
    }

    public void startLogin() {
        WebElementUtils.byText("Hello, Sign in").click();
    }

    public AmazonPage submitLogin() {
        signInSubmit.click();
        return new AmazonPage();
    }
}

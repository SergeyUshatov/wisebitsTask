package pageObjects.amazon;

import static com.codeborne.selenide.Selenide.open;

public class AmazonBasePage {
    public void navigate(String url) {
        open(url);
    }
}

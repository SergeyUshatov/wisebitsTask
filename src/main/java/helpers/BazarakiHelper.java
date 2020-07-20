package helpers;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import pageObjects.bazaraki.BazarakiItemPage;
import pageObjects.bazaraki.BazarakiLoginPage;
import pageObjects.bazaraki.BazarakiSearchResultsPage;
import utils.EnvironmentConfigurator;
import utils.WebDriverFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BazarakiHelper {
    BazarakiItemPage bazarakiItemPage;
    BazarakiLoginPage bazarakiLoginPage;
    BazarakiSearchResultsPage resultsPage;

    public void navigateToBazaraki() {
        WebDriverFactory.INSTANCE
                .getAndroidDriver()
                .get(EnvironmentConfigurator.INSTANCE.
                        getMobileTestUrl());
    }

    public SearchBuilder searchBuilder() {
        return new SearchBuilder();
    }

    public List<?> getResults() {
        resultsPage = new BazarakiSearchResultsPage();
        return resultsPage.getResultItems();
    }

    private List<WebElement> filterElementsWithPhotos(List<?> list) {
        List<WebElement> resList = new ArrayList<>();
        list.forEach(it -> {
            WebElement element = (WebElement)it;
            try {
                resultsPage.getPhotosElementIn(element);
                resList.add(element);
            } catch (NoSuchElementException e) {

            }
        });
        return resList;
    }

    public WebElement getLatestWithMinPriceAndWithPhtos(final int amountOfPhotos) {
        return filterElementsWithPhotos(getResults())
                .stream()
                .filter(it -> !resultsPage.getPhotosElementIn(it).getText().isEmpty()
                        && amountOfPhotos < Integer.parseInt(resultsPage.getPhotosElementIn(it).getText())
                ).min(Comparator.comparing(it -> Double
                        .parseDouble(resultsPage.getPriceElementIn((WebElement) it).getAttribute("content"))
                ).thenComparing(it ->
                        LocalDateTime.parse(resultsPage.getDateElement((WebElement) it).getText(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
                )).orElse(null);
    }

    public BazarakiItemPage openItem(WebElement element) {
        element.click();
        bazarakiItemPage = new BazarakiItemPage();
        return bazarakiItemPage;
    }

    public String getCurrentUrl() {
        return bazarakiItemPage.getCurrentUrl();
    }

    public String getItemId() {
        String text = bazarakiItemPage.getItemIdElement().getText();
        String[] info = text.split(" ");
        return info[info.length-1];
    }

    public BazarakiHelper addItemToFavorites() {
        bazarakiItemPage.clickOnAddToFavorites();
        bazarakiLoginPage = new BazarakiLoginPage();
        return this;
    }

    public BazarakiHelper acceptPrivacyConditions() {
        bazarakiLoginPage.selectPrivacyCheckbox();
        return this;
    }

    public void continueLogin() {
        bazarakiLoginPage.clickOnContinueButton();
    }

    public boolean isLoginErrorDisplayed() {
        return bazarakiLoginPage.getErrorMessage().isDisplayed();
    }
}

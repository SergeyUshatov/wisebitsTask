package bazaraki;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BazarakiTest extends TestBase {

    @Test
    public void bazarakiTestTask() {
        getBazarakiHelper().navigateToBazaraki();
        getBazarakiHelper().searchBuilder()
                .selectCategory("Real estate")
                .selectSubCategory("Houses, apartments to rent")
                .withQuery("2 bedrooms flat")
                .openFilters()
                .selectMinPrice(200)
                .selectMaxPrice(1000)
                .selectDistrict("Limassol district")
                .search();

        WebElement element = getBazarakiHelper().getLatestWithMinPriceAndWithPhtos(5);

        assertThat(element, is(notNullValue()));

        getBazarakiHelper().openItem(element);
        String url = getBazarakiHelper().getCurrentUrl();
        String itemId = getBazarakiHelper().getItemId();

        assertThat(url, containsString(itemId));

        getBazarakiHelper().addItemToFavorites()
                .acceptPrivacyConditions()
                .continueLogin();

        boolean errorIsDisplayed = getBazarakiHelper().isLoginErrorDisplayed();
        assertThat(errorIsDisplayed, is(true));
    }
}

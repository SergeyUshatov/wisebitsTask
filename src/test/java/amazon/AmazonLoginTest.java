package amazon;

import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class AmazonLoginTest extends TestBase {

    @BeforeMethod
    public void beforeMethod() {
        getNavigationHelper().navigateToAmazon();
    }

    @Test
    public void loginTest() {
        getAmazonLoginHelper().login();

        MatcherAssert.assertThat(getAmazonLoginHelper().isUserLoggedIn(), is(true));
    }

    @Test
    public void logoutTest() {
        getAmazonLoginHelper().login();
        getAmazonLoginHelper().logout();
        MatcherAssert.assertThat(getAmazonLoginHelper().isUserLoggedIn(), is(false));
    }
}

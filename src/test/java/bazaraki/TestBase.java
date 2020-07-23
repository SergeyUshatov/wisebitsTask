package bazaraki;

import helpers.bazaraki.BazarakiHelper;
import org.testng.annotations.AfterMethod;
import utils.WebDriverFactory;

public abstract class TestBase {
    private BazarakiHelper bazarakiHelper;

    @AfterMethod
    public void afterTest( ) {
        WebDriverFactory.INSTANCE.stopMobileDriver();
    }

    protected BazarakiHelper getBazarakiHelper() {
        if (bazarakiHelper == null) {
            bazarakiHelper = new BazarakiHelper();
        }
        return bazarakiHelper;
    }
}

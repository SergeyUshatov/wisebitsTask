import helpers.AmazonHelper;
import helpers.BazarakiHelper;

public abstract class TestBase {
    private AmazonHelper amazonHelper;
    private BazarakiHelper bazarakiHelper;

    protected AmazonHelper getAmazonHelper() {
        if (amazonHelper == null) {
            amazonHelper = new AmazonHelper();
        }
        return amazonHelper;
    }

    protected BazarakiHelper getBazarakiHelper() {
        if (bazarakiHelper == null) {
            bazarakiHelper = new BazarakiHelper();
        }
        return bazarakiHelper;
    }
}

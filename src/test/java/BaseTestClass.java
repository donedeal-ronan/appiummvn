import POM.android.MyAdsPageObjectAndroid;
import POM.generic.*;
import POM.ios.MyAdsPageObjectIOS;
import org.junit.After;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public class BaseTestClass extends PlatformBase {

    protected WebDriverWait wait;

    protected static LoginPage loginPage;
    protected static MyAdsPage myAdsPage;
    protected static PlaceAdPage placeAdPage;

    @BeforeClass
    public void setup() throws MalformedURLException {
        PlatformController.instance.setup();
        wait = new WebDriverWait(driver(), 10);

        loginPage = new LoginPageObject(driver());
        placeAdPage = new PlaceAdPageObject(driver(), wait);

        switch (PlatformController.platform) {
            case ANDROID:
                myAdsPage = new MyAdsPageObjectAndroid(driver());
                break;
            case IOS:
                myAdsPage = new MyAdsPageObjectIOS(driver());
                break;
        }
    }

    @After
    public void tearDown() {
        PlatformController.instance.tearDown();
    }
}
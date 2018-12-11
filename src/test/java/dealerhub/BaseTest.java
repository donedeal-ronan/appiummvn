package dealerhub;

import dealerhub.POM.android.MyAdsPageObjectAndroid;
import dealerhub.POM.generic.*;
import dealerhub.POM.ios.MyAdsPageObjectIOS;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

public class BaseTest extends PlatformBase {

    protected WebDriverWait wait;

    protected static LoginPage loginPage;
    protected static MyAdsPage myAdsPage;
    protected static PlaceAdPage placeAdPage;

    @BeforeTest
    @Parameters({"platform", "udid", "systemPort"})
    public void setup(String platform, String udid, int systemPort) throws MalformedURLException {
        PlatformController.instance.setup(platform, udid, systemPort);
        wait = new WebDriverWait(driver(), 10);

        loginPage = new LoginPageObject(driver());
        placeAdPage = new PlaceAdPageObject(driver());

        switch (PlatformController.platform) {
            case ANDROID27:
            case ANDROID23:
                myAdsPage = new MyAdsPageObjectAndroid(driver());
                break;
            case IOS11:
            case IOS12:
                myAdsPage = new MyAdsPageObjectIOS(driver());
                break;
        }
    }

    @AfterTest
    public void tearDown() {
        PlatformController.instance.tearDown();
    }
}
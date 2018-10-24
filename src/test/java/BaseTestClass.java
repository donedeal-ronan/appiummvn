import POM.android.MyAdsPageObjectAndroid;
import POM.generic.LoginPage;
import POM.generic.LoginPageObject;
import POM.generic.MyAdsPage;
import POM.ios.MyAdsPageObjectIOS;
import org.junit.After;
import org.junit.Before;

import java.net.MalformedURLException;

public class BaseTestClass extends PlatformBase {

    protected static LoginPage loginPage;
    protected static MyAdsPage myAdsPage;

    @Before
    public void setup() throws MalformedURLException {
        PlatformController.platform = PlatformEnum.ANDROID;
        PlatformController.instance.setup();
        loginPage = new LoginPageObject(driver());

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

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AndroidSetup {

    protected static AndroidDriver driver;

    /**
     * Installs the apk on the specified device.
     * @throws MalformedURLException
     */
    protected static void prepareAndroidForAppium() throws MalformedURLException {
        File appDir = new File("apps");
        File app = new File(appDir, "dealerAppTest.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("device,", "Android");
        capabilities.setCapability("deviceName", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("appWaitActivity", "ie.distilledsch.donedeal.dealerapp.navigation.BottomNavActivity, "
                + "ie.distilledsch.donedeal.dealerapp.login.activities.LoginActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    public static AndroidDriver getDriver() {
        return driver;
    }
}

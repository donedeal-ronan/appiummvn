import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class PlatformController {

    public static PlatformController instance = new PlatformController();
    public AppiumDriver driver;
    public static PlatformEnum platform = PlatformEnum.ANDROID;

    private File appDir = new File("apps");
    private File app;

    private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    public void setup() throws MalformedURLException {
        if (driver != null) {
            return;
        }

        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (platform) {
            case ANDROID:
                app = new File(appDir, "android_dealerAppTest.apk");

                capabilities.setCapability("device,", "Android");
                capabilities.setCapability("deviceName", "Nexus 6P");
                capabilities.setCapability("platformName", "Android");
                capabilities.setCapability("app", app.getAbsolutePath());
                capabilities.setCapability("noReset", true);
                capabilities.setCapability("appWaitActivity", "ie.distilledsch.donedeal.dealerapp.navigation.BottomNavActivity, "
                        + "ie.distilledsch.donedeal.dealerapp.login.activities.LoginActivity");

                driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
                break;
            case IOS:
                app = new File(appDir + "/ios", "dealerApp.app");

                capabilities.setCapability("automationName", "XCUITest");
                capabilities.setCapability("platformName", "iOS");
                capabilities.setCapability("deviceName", "iPhone XR");
                capabilities.setCapability("platformVersion", "12.0");
                System.out.println("ABSOLUTE PATH: " + app.getAbsolutePath());
                capabilities.setCapability("app", app.getAbsolutePath());
                capabilities.setCapability("noReset", true);

                driver = new IOSDriver(new URL(APPIUM_URL), capabilities);
                break;
        }
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }


    public void tearDown() {
        driver.quit();
        driver = null;
    }
}
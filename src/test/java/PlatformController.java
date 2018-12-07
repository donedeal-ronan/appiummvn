import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Sets up the platform (which will be changed based on the param supplied to the command line at runtime but defaults
 * to Android) along with the desired capabilities for each platform.
 */

public class PlatformController {

    public static PlatformController instance = new PlatformController();
    public AppiumDriver driver;
    public static PlatformEnum platform;
    private AppiumDriverLocalService service;

    private File appDir = new File("apps");
    private File app;

    private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";
    public void setup(String platform2, String udid, int systemPort) throws MalformedURLException {
        startAppiumService();

        if (driver != null) {
            return;
        }

        platform = PlatformEnum.valueOf(platform2.toUpperCase());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        switch (platform) {
            case ANDROID27:
            case ANDROID23:
                app = new File(appDir, "android_dealerAppTest.apk");

                capabilities.setCapability("device,", "Android");
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform2.substring(0, platform2.length()-2));
                capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "ie.distilledsch.donedeal.dealerapp.navigation.BottomNavActivity, "
                        + "ie.distilledsch.donedeal.dealerapp.login.activities.LoginActivity");
                driver = new AndroidDriver(new URL(APPIUM_URL), capabilities);
                break;
            case IOS11:
            case IOS12:
                app = new File(appDir + "/ios", "dealerApp.app");

                capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform2);
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone XR");
                capabilities.setCapability("wdaLocalPort", systemPort);
                capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platform2);
                System.out.println("ABSOLUTE PATH: " + app.getAbsolutePath());
                capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
                driver = new IOSDriver(new URL(APPIUM_URL), capabilities);
                break;
        }
    }

    private void startAppiumService() {
        service = AppiumDriverLocalService.buildDefaultService();
        if (!service.isRunning()) {
            service.start();
        }
    }

    public void tearDown() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        service.stop();
    }
}
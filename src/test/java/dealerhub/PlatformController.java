package dealerhub;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;

/**
 * Sets up the platform (which will be changed based on the param supplied to the command line at runtime but defaults
 * to Android) along with the desired capabilities for each platform.
 */

public class PlatformController {

    public static PlatformController instance = new PlatformController();
    public AppiumDriver driver;
    public static PlatformEnum platform;
    private AppiumDriverLocalService service;

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
                driver = new DSCHCapabilities(platform2, udid, systemPort).buildAndroidDriver();
                break;
            case IOS11:
            case IOS12:
                driver = new DSCHCapabilities(platform2, udid, systemPort).buildIOSDriver();
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
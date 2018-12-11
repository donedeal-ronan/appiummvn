package dealerhub;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

class DSCHCapabilities {

    private String platform;
    private String udid;
    private int systemPort;
    private File appDir = new File("apps");
    private File app;
    private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    DSCHCapabilities(String platform, String udid, int systemPort) {
        this.platform = platform;
        this.udid = udid;
        this.systemPort = systemPort;
    }

    AndroidDriver buildAndroidDriver() throws MalformedURLException {
        return new AndroidDriver(new URL(APPIUM_URL), buildAndroidCapabilities());
    }

    private DesiredCapabilities buildAndroidCapabilities() {
        String BASE_ACTIVITY = "ie.distilledsch.donedeal.dealerapp.navigation.BottomNavActivity";
        String POP_UP_ACTIVITY = "ie.distilledsch.donedeal.dealerapp.login.activities.LoginActivity";

        app = new File(appDir, "android_dealerAppTest.apk");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability("device,", "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform.substring(0, platform.length()-2));
        capabilities.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, systemPort);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY,BASE_ACTIVITY + ", " + POP_UP_ACTIVITY);
        return  capabilities;
    }

    IOSDriver buildIOSDriver() throws MalformedURLException {
        return new IOSDriver(new URL(APPIUM_URL), buildIOSCapabilities());
    }

    private DesiredCapabilities buildIOSCapabilities() {
        app = new File(appDir + "/ios", "dealerApp.app");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, udid);
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, platform);
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone XR");
        capabilities.setCapability("wdaLocalPort", systemPort);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platform);
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        return capabilities;
    }
}

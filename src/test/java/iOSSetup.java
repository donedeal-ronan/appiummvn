import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class iOSSetup {

    protected static IOSDriver driver;
    private static String APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    protected static void prepareIosForAppium() throws MalformedURLException {
        File appDir = new File("apps/ios");
        File app = new File(appDir, "dealerApp.app");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("automationName", "XCUITest");
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone XR");
        capabilities.setCapability("platformVersion", "12.0");
        System.out.println("ABSOLUTE PATH: " + app.getAbsolutePath());
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("noReset", true);

        driver = new IOSDriver(new URL(APPIUM_URL), capabilities);
    }
}

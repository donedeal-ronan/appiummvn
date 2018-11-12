package base;

import io.appium.java_client.AppiumDriver;

public abstract class PlatformBase {

    protected static AppiumDriver driver() {
        return PlatformController.instance.driver;
    }
}
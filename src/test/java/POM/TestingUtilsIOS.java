package POM;

import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;

import java.util.HashMap;

public class TestingUtilsIOS {

    public static void scrollToElement(IOSDriver driver, String elementId) {
        boolean isDisplayed = driver.findElements(By.id(elementId)).size() > 0;

        while (!isDisplayed) {
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("direction", "down");
            driver.executeScript("mobile:scroll", scrollObject);
            isDisplayed = driver.findElements(By.id(elementId)).size() > 0;
        }
    }
}

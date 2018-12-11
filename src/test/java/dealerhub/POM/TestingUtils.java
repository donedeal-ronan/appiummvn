package dealerhub.POM;

import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestingUtils {

    public static void waitForView(WebDriverWait wait, MobileElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }
}

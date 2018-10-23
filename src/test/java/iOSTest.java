import io.appium.java_client.MobileElement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

public class iOSTest extends iOSSetup {

    private WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeClass
    public static void setup() throws Exception {
        prepareIosForAppium();
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void iOSProofOfConceptTest() {
        System.out.println("ID: + "+ driver.getSessionId());
        login();
    }

    private void login() {
        MobileElement emailInput = (MobileElement) driver.findElementByAccessibilityId("email");
        String emailAddress = "ronan.doyle+dhtest@distilled.ie";
        emailInput.sendKeys(emailAddress);

        MobileElement password = (MobileElement) driver.findElementByAccessibilityId("password");
        password.sendKeys("donedeal");

        driver.hideKeyboard();

        MobileElement loginBtn = (MobileElement) driver.findElementByAccessibilityId("LOGIN");
        loginBtn.click();
    }
}

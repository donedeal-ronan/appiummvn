import io.appium.java_client.MobileElement;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

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

    @Ignore
    @Test
    public void iOSProofOfConceptTest() {
        System.out.println("ID: + "+ driver.getSessionId());
        handleLoggedInUser();
        login();
        scrollToAd();
        navigateToPlaceAdPhotos();
        captureAndSavePhoto();
        navigateToMyAccount();
        verifyCorrectAccountInfo();
        logout();
    }

    private void login() {
        MobileElement emailInput = (MobileElement) driver.findElementByAccessibilityId("email");
        String emailAddress = "ronan.doyle+dhtest@distilled.ie";
        emailInput.sendKeys(emailAddress);

        MobileElement password = (MobileElement) driver.findElementByAccessibilityId("password");
        password.sendKeys("donedeal");

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    private void scrollToAd() {
        String adTitle = "BMW Z3";
        iosScrollTo(adTitle);
        driver.findElementByAccessibilityId(adTitle).click();

        waitForView(By.id("Ad Description"));
        driver.navigate().back();
    }

    private void navigateToPlaceAdPhotos() {
        driver.findElementByAccessibilityId("Place Ad").click();
        waitForView(By.id("PHOTOS"));
    }

    private void captureAndSavePhoto() {
        driver.findElementById("PHOTOS").click();

        waitForView(By.id("ADD PHOTOS"));

        driver.findElementById("ADD PHOTOS").click();
        driver.findElementById("Choose from Library").click();

        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        if (driver.findElements(By.id("OK")).size() > 0) {
            ((MobileElement) driver.findElements(By.id("OK"))).click();
        }

        driver.findElementById("All Photos").click();
        driver.findElementByName("cell0").click();
        driver.findElementById("Done").click();

//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElementById("Save").click();
    }

    private void navigateToMyAccount() {
        driver.findElementByAccessibilityId("My Account").click();
    }

    private void verifyCorrectAccountInfo() {
        assert(driver.findElementById("Ronan Test Account").isDisplayed());
        MobileElement accountMgtLink = (MobileElement) driver.findElementByXPath("//XCUIElementTypeApplication[@name=\"DH.Debug\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView");
        assert(accountMgtLink.isDisplayed());
    }

    private void logout() {
        driver.findElementById("LOGOUT").click();
    }



    private void waitForView(By viewToWaitFor) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewToWaitFor));
    }

    private void iosScrollTo(String elementId) {
        boolean isDisplayed = driver.findElements(By.id(elementId)).size() > 0;

        while (!isDisplayed) {
            HashMap<String, String> scrollObject = new HashMap<String, String>();
            scrollObject.put("direction", "down");
            driver.executeScript("mobile:scroll", scrollObject);
            isDisplayed = driver.findElements(By.id(elementId)).size() > 0;
        }
    }

    private void handleLoggedInUser() {
        if (driver.findElements(By.id("LOGIN")).size() == 0) {
            navigateToMyAccount();
            logout();

            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
    }
}
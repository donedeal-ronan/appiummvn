import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class AppiumTest extends AndroidSetup {

    private WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeClass
    public static void setup() throws Exception {
        prepareAndroidForAppium();
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void proofOfConceptTest() throws InterruptedException {
        System.out.println("ID: + "+ driver.getSessionId());

        String app_package_name = "ie.distilledsch.donedeal.dealerapp.test:id/";
        By emailAddress = By.id(app_package_name + "mEmailInputET");
        By mPasswordInputET = By.id(app_package_name + "mPasswordInputET");
        By loginBtn = By.id(app_package_name + "login_button");
        By logoutBtn = By.id(app_package_name + "my_account_logout_button");
        By btmNavBar = By.id(app_package_name + "mBottomNavView");
        login(emailAddress, mPasswordInputET, loginBtn, btmNavBar);
        scrollToAd();
        navigateToPlaceAdPhotos(app_package_name, btmNavBar);
        navigateToCamera(app_package_name);
        acceptCameraAndStoragePermissions();
        captureAndSavePhoto(app_package_name, btmNavBar);
        navigateToMyAccount(app_package_name, logoutBtn, btmNavBar);
        verifyCorrectAccountInfo(app_package_name);
        logout(loginBtn, logoutBtn);
    }

    private void login(By emailAddress, By mPasswordInputET, By loginBtn, By btmNavBar) {
        driver.findElement(emailAddress).sendKeys("ronan.doyle+dhtest@distilled.ie");
        driver.findElement(mPasswordInputET).sendKeys("donedeal");
        driver.hideKeyboard();
        driver.findElement(loginBtn).click();

        waitForView(btmNavBar);
    }

    private void scrollToAd() {
        String adTitle = "BMW Z3";
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                ".scrollIntoView(new UiSelector().textContains(\"" + adTitle + "\").instance(0))").isDisplayed();
    }

    private void navigateToPlaceAdPhotos(String app_package_name, By btmNavBar) {
        driver.findElement(btmNavBar).findElement(By.id(app_package_name + "action_place_ad")).click();

        waitForView(By.id(app_package_name + "place_ad_content_view"));
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                ".scrollIntoView(new UiSelector().textContains(\"" + "PHOTOS" + "\").instance(0))").click();
    }

    private void navigateToCamera(String app_package_name) {
        By fab = By.id(app_package_name + "photo_gallery_add_photos_fab");
        waitForView(fab);
        driver.findElement(fab).click();

        driver.findElementByXPath("//android.widget.TextView[@text='Camera']").click();
    }

    private void acceptCameraAndStoragePermissions() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        // Checking to see if the camera and storage permission dialogs appear.
        if (driver.findElementByXPath("//*[@class='android.widget.Button'][2]").isDisplayed()) {
            driver.findElementByXPath("//*[@class='android.widget.Button'][2]").click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }

        if (driver.findElementByXPath("//*[@class='android.widget.Button'][2]").isDisplayed()) {
            driver.findElementByXPath("//*[@class='android.widget.Button'][2]").click();
            driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        }
    }

    private void captureAndSavePhoto(String app_package_name, By btmNavBar) {
        driver.sendKeyEvent(27);
        waitForView(By.id("com.android.camera2:id/done_button"));

        driver.findElement(By.id("com.android.camera2:id/done_button")).click();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        waitForView(By.id(app_package_name + "action_save_photos"));

        driver.findElement(By.id(app_package_name + "action_save_photos")).click();
        waitForView(btmNavBar);
    }

    private void navigateToMyAccount(String app_package_name, By logoutBtn, By btmNavBar) {
        driver.findElement(btmNavBar).findElement(By.id(app_package_name + "action_account")).click();
        waitForView(logoutBtn);
    }

    private void verifyCorrectAccountInfo(String app_package_name) {
        assert(driver.findElement(By.id(app_package_name + "my_account_user_name_text")).getText().equals("Ronan Test Account"));
        assert(driver.findElement(By.id(app_package_name + "my_account_account_management_text")).getText().equals("For more account management options visit www.donedeal.ie/dealerhub"));
    }

    private void logout(By loginBtn, By logoutBtn) {
        driver.findElement(logoutBtn).click();
        waitForView(loginBtn);
    }

    private void waitForView(By viewToWaitFor) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewToWaitFor));
    }
}

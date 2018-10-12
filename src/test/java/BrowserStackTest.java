import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BrowserStackTest {
    public static String userName = "barryrlmurphy";
    public static String accessKey = "A3bbtnqhySfmM8tBdzpg";

    static AndroidDriver<AndroidElement> driver;
    private static WebDriverWait wait;

    public static void main(String args[]) throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("device", "Samsung Galaxy S8 Plus");
        caps.setCapability("app", "bs://a2c7ea744f6c78ca85d01fa052820de95f7d26ea");
        caps.setCapability("browserstack.local", "true");

        driver = new AndroidDriver<AndroidElement>(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), caps);
        wait = new WebDriverWait(driver, 10);

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

        driver.quit();
    }

    private static void login(By emailAddress, By mPasswordInputET, By loginBtn, By btmNavBar) {
        driver.findElement(emailAddress).sendKeys("ronan.doyle+dhtest@distilled.ie");
        driver.findElement(mPasswordInputET).sendKeys("donedeal");
        driver.hideKeyboard();
        driver.findElement(loginBtn).click();

        waitForView(btmNavBar);
    }

    private static void scrollToAd() {
        String adTitle = "BMW Z3";
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                ".scrollIntoView(new UiSelector().textContains(\"" + adTitle + "\").instance(0))").isDisplayed();
    }

    private static void navigateToPlaceAdPhotos(String app_package_name, By btmNavBar) {
        driver.findElement(btmNavBar).findElement(By.id(app_package_name + "action_place_ad")).click();

        waitForView(By.id(app_package_name + "place_ad_content_view"));
        driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))" +
                ".scrollIntoView(new UiSelector().textContains(\"" + "PHOTOS" + "\").instance(0))").click();
    }

    private static void navigateToCamera(String app_package_name) {
        By fab = By.id(app_package_name + "photo_gallery_add_photos_fab");
        waitForView(fab);
        driver.findElement(fab).click();

        driver.findElementByXPath("//android.widget.TextView[@text='Camera']").click();
    }

    private static void acceptCameraAndStoragePermissions() {
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

    private static void captureAndSavePhoto(String app_package_name, By btmNavBar) {
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

    private static void navigateToMyAccount(String app_package_name, By logoutBtn, By btmNavBar) {
        driver.findElement(btmNavBar).findElement(By.id(app_package_name + "action_account")).click();
        waitForView(logoutBtn);
    }

    private static void verifyCorrectAccountInfo(String app_package_name) {
        assert(driver.findElement(By.id(app_package_name + "my_account_user_name_text")).getText().equals("Ronan Test Account"));
        assert(driver.findElement(By.id(app_package_name + "my_account_account_management_text")).getText().equals("For more account management options visit www.donedeal.ie/dealerhub"));
    }

    private static void logout(By loginBtn, By logoutBtn) {
        driver.findElement(logoutBtn).click();
        waitForView(loginBtn);
    }

    private static void waitForView(By viewToWaitFor) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(viewToWaitFor));
    }

}
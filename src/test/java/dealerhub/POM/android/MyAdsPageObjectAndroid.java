package dealerhub.POM.android;

import dealerhub.POM.generic.MyAdsPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MyAdsPageObjectAndroid implements MyAdsPage {

    private AndroidDriver driver;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "BMW Z3" + "\").instance(0))")
    MobileElement adCard;

    public MyAdsPageObjectAndroid(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = (AndroidDriver) driver;
    }

    public void scrollToAd() {
        assert(adCard.isDisplayed());
    }

    public void clickAd() {
        System.out.println("CURRENT ACTIVITY: " + driver.currentActivity());
        adCard.click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        System.out.println("CURRENT ACTIVITY: " + driver.currentActivity());
        driver.navigate().back();
    }
}
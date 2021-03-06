package dealerhub.POM.ios;

import dealerhub.POM.TestingUtilsIOS;
import dealerhub.POM.generic.MyAdsPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

public class MyAdsPageObjectIOS implements MyAdsPage {

    @iOSFindBy(accessibility = "BMW Z3")
    MobileElement adCard;

    @iOSFindBy(accessibility = "icon custom back arrow")
    MobileElement backBtn;

    String adTitle = "BMW Z3";
    private IOSDriver driver;

    public MyAdsPageObjectIOS(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = (IOSDriver) driver;
    }

    public void scrollToAd() {
        TestingUtilsIOS.scrollToElement(driver, adTitle);
    }

    public void clickAd() {
        adCard.click();

        backBtn.click();
    }
}
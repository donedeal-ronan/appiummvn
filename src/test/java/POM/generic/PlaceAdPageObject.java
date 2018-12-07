package POM.generic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

public class PlaceAdPageObject implements PlaceAdPage {

    @AndroidFindBy(id = "action_place_ad")
    @iOSFindBy(accessibility = "Place Ad")
    private MobileElement placeAdTab;

    @AndroidFindBy(id = "action_reset")
    @iOSFindBy(accessibility = "Reset")
    private MobileElement resetBtn;

    @AndroidFindBy(uiAutomator = "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textContains(\"" + "PHOTOS" + "\").instance(0))")
    @iOSFindBy(accessibility = "PHOTOS")
    private MobileElement photosBtn;

    @AndroidFindBy(id = "photo_gallery_add_photos_fab")
    @iOSFindBy(accessibility = "ADD PHOTOS")
    MobileElement addPhotoBtn;

    public PlaceAdPageObject(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public void navigateToPlaceAdTab() {
        placeAdTab.click();
    }

    public void clickPhotos() {
        photosBtn.click();
    }

    public void navigateToPlaceAdPhotosScreen() {
        navigateToPlaceAdTab();
        clickPhotos();
    }
}
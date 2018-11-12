package POM.generic;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageObject implements LoginPage {

    private String emailAddress = "ronan.doyle+dhtest@distilled.ie";
    private String password = "donedeal";
    private AppiumDriver driver;

    @AndroidFindBy(id = "mEmailInputET")
    @iOSFindBy(accessibility = "email")
    MobileElement emailInput;

    @AndroidFindBy(id = "mPasswordInputET")
    @iOSFindBy(accessibility = "password")
    MobileElement passwordInput;

    @AndroidFindBy(id = "login_button")
    @iOSFindBy(accessibility = "LOGIN")
    MobileElement loginBtn;

    @AndroidFindBy(id = "action_my_ads")
    @iOSFindBy(accessibility = "My Ads")
    private MobileElement myAdsTab;


    public LoginPageObject(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void insertEmailAddress() {
        emailInput.sendKeys(emailAddress);
        driver.hideKeyboard();
    }

    public void insertPassword() {
        passwordInput.sendKeys(password);
        driver.hideKeyboard();
    }

    public void clickLoginBtn() {
        loginBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkIsOnMyAdsScreen() {
        assert myAdsTab.isDisplayed();
    }

    public void login() {
        insertPassword(); // Password is needed first due to dismissing the keyboard calling the "Done" btn on iOS.
        insertEmailAddress();
        driver.hideKeyboard();
        clickLoginBtn();
    }
}
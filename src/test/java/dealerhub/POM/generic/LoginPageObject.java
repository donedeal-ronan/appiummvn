package dealerhub.POM.generic;

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
    private MobileElement emailInput;

    @AndroidFindBy(id = "mPasswordInputET")
    @iOSFindBy(accessibility = "password")
    private MobileElement passwordInput;

    @AndroidFindBy(id = "login_button")
    @iOSFindBy(accessibility = "LOGIN")
    private MobileElement loginBtn;

    @AndroidFindBy(id = "action_account")
    @iOSFindBy(accessibility = "My Account")
    private MobileElement accountTab;

    @AndroidFindBy(id = "my_account_logout_button")
    @iOSFindBy(accessibility = "LOGOUT")
    private MobileElement logoutBtn;

    public LoginPageObject(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
    }

    public void login() {
        insertPassword(); // Password is needed first due to dismissing the keyboard calling the "Done" btn on iOS.
        insertEmailAddress();
        driver.hideKeyboard();
        clickLoginBtn();
        logout();
    }

    private boolean isLoggedIn() {
        return !passwordInput.isDisplayed();
    }

    private void logout() {
        accountTab.click();
        logoutBtn.click();
    }

    public void insertPassword() {
        passwordInput.sendKeys(password);
    }

    public void insertEmailAddress() {
        emailInput.sendKeys(emailAddress);
    }

    public void clickLoginBtn() {
        loginBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
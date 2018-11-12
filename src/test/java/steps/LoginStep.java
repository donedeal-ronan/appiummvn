package steps;

import base.BaseTest;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

import java.net.MalformedURLException;

public class LoginStep extends BaseTest {
    @Given("^I want to login$")
    public void IWantToLogIn() {
        System.out.print("IWantToLogin");
        try {
            setup("Android 8.1.0", "emulator-5554", 8201);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @And("^I enter an email address into the email address field$")
    public void enterEmailAddress() throws Throwable {
        loginPage.insertEmailAddress();
    }

    @And("^I enter a password into the password field$")
    public void enterPassword() throws Throwable {
        loginPage.insertPassword();
    }

    @And("^I click the Login button$")
    public void clickButton() throws Throwable {
        loginPage.clickLoginBtn();
    }

    @Then("^I should be logged into my account and shown the MyAds screen$")
    public void IShouldGoToTheScreen() throws Throwable {
        loginPage.checkIsOnMyAdsScreen();
    }
}

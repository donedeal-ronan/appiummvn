import org.junit.Test;

public class GenericTest extends BaseTestClass {

//    @Test
    public void a_login() {
        loginPage.login();
    }

    @Test
    public void navigateToAd() {
        myAdsPage.scrollToAd();
        myAdsPage.clickAd();
    }
}

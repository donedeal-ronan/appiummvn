import org.testng.annotations.Test;

public class MyAdsTest extends BaseTestClass {

    @Test
    public void viewAd() {
        myAdsPage.scrollToAd();
        myAdsPage.clickAd();
    }
}
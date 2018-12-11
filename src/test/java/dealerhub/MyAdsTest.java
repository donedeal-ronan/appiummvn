package dealerhub;

import org.testng.annotations.Test;

public class MyAdsTest extends BaseTest {

    @Test
    public void viewAd() {
        myAdsPage.scrollToAd();
        myAdsPage.clickAd();
    }
}
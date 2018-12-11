package dealerhub;

import org.testng.annotations.Test;

public class ALoginScreenTest extends BaseTest {

    @Test
    public void login() {
        loginPage.login();
    }
}
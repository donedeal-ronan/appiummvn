import org.testng.annotations.Test;

public class ALoginScreenTest extends BaseTestClass {

    @Test
    public void login() {
        loginPage.login();
    }
}
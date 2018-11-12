import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.Test;

@CucumberOptions(features={"src//test//java//features"}
        ,glue={"steps"}
        ,plugin = {"pretty", "html:target/cucumber"}
        ,tags ={"@appium"})

@Test
public class BDDRunner extends AbstractTestNGCucumberTests {
}

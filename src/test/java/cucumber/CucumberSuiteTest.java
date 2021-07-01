package cucumber;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ua.kiev.prog.automation.base.Session;

@CucumberOptions(
        features = "src/test/resources/cucumber",
        glue     = {"ua.kiev.prog.automation.steps"},           //где хранятся степы
        plugin   = {                                           //формат хранения данных
                //"pretty:target/cucumber-pretty.txt",
                "pretty",
                "rerun:target/rerun.txt",
                "json:target/cucumber.json",
                "html:target/cucumber-html.html"
        }
)
public class CucumberSuiteTest extends AbstractTestNGCucumberTests {
        @BeforeMethod
        public void before() {
                WebDriverRunner.setWebDriver(Session.getInstance().driver());
        }

        @AfterMethod
        public void after() {
                Session.getInstance().quit();
        }
}

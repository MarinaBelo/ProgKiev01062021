package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/resources/cucumber",
        glue     = {"ua.kiev.prog.automation.steps"},           //где хранятся степы
        plugin   = {                                           //формат хранения данных
                        "json:target/cucumber.json",
                        "html:target/cucumber-html.html"
        }
)
public class CucumberSuiteTest extends AbstractTestNGCucumberTests {
}

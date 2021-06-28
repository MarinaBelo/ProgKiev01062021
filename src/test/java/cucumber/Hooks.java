package cucumber;

import com.codeborne.selenide.WebDriverRunner;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import ua.kiev.prog.automation.base.Session;

public class Hooks {
    @Before
    public void before(Scenario scenario) {
        WebDriverRunner.setWebDriver(Session.getInstance().driver());
    }

    @After
    public void after(Scenario scenario) {
        Session.getInstance().quit();
    }
}
package ua.kiev.prog.automation.base;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import ua.kiev.prog.automation.tools.API;
import ua.kiev.prog.automation.tools.DBModel;

public class BaseUITest {

    final protected API api = new API();
    final protected DBModel db = new DBModel();
    protected WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        driver = Session.getInstance().driver();
        WebDriverRunner.setWebDriver(driver);                       //driver для Selenide
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        Session.getInstance().quit();
    }
}
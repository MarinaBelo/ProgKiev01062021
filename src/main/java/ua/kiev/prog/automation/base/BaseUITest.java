package ua.kiev.prog.automation.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseUITest {

    protected WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        driver = Session.getInstance().driver();
    }

    @AfterMethod
    public void afterMethod() {
        Session.getInstance().quit();
    }
}
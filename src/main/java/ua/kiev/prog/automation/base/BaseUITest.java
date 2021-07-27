package ua.kiev.prog.automation.base;

import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Attachment;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ua.kiev.prog.automation.tools.API;
import ua.kiev.prog.automation.tools.DBModel;

public class BaseUITest {

    final protected API api = new API();
    final protected DBModel db = new DBModel();
    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setupAllReport() {
        SelenideLogger.addListener("allure", new AllureSelenide()
                .screenshots(true)
                .savePageSource(false)
                .includeSelenideSteps(true)
        );
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeMethod() {
        driver = Session.getInstance().driver();
        WebDriverRunner.setWebDriver(driver);                       //driver для Selenide
    }

    @AfterMethod(alwaysRun = true)
    public void afterMethod(ITestResult result) {
        if(result.getStatus()==ITestResult.FAILURE)
            takeScreenshot();
        Session.getInstance().quit();
    }

    @Attachment(value="Screenshot", type = "image/png")
    private byte[] takeScreenshot(){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
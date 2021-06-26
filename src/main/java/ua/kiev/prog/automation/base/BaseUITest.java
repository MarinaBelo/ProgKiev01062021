package ua.kiev.prog.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseUITest {
    protected WebDriver driver;

    @BeforeMethod
    public void beforeMethod() {
        WebDriverManager.chromedriver().setup();          // Download latest version of Chromedriver.exe
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");        // =driver.manage().window().maximize();
        options.addArguments("--disable-web-security");   //возможность обойти https//отключение веб-безопасности
        options.addArguments("--no-proxy-server");        //Don't use a proxy server

        /*options.addArguments("--headless");               //chrome будет запускаться без UI-части->ускоряет тесты, экономит память
        options.addArguments("--no-sandbox");             //использ.вместе с headless*/

        driver = new ChromeDriver(options);                      // Create driver
        driver.get("http://zvisno.com/");                 // Go to basic URL
        //driver.manage().window().maximize();
    }

    @AfterMethod
    public void afterMethod() {
        if(driver!=null)
            driver.quit();                            // Close driver

    }
}

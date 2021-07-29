package ua.kiev.prog.automation.base.testbed;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ua.kiev.prog.automation.base.Config;

public class TestbedLocal extends Testbed{

    @Override
    final public WebDriver createDriver() {
        WebDriver driver = null;
        if (Config.BROWSER_NAME.value.equals("chrome")) {       // Блок кода перенесенный из BaseUITest
            WebDriverManager.chromedriver().setup();            // Download latest version of Chromedriver.exe
            driver = new ChromeDriver(this.getCommonChromeOptions());                // Create driver
        }
        else if(Config.BROWSER_NAME.value.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver(this.getCommonFirefoxOptions());
        } else
            throw new RuntimeException("Browser " + Config.BROWSER_NAME.value + "is not supported");
        return driver;
    }
}

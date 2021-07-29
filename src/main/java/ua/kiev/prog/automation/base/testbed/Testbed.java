package ua.kiev.prog.automation.base.testbed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ua.kiev.prog.automation.base.Config;

abstract public class Testbed
{
    final protected ChromeOptions getCommonChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");          // =driver.manage().window().maximize();
        options.addArguments("--disable-web-security");     //возможность обойти https//отключение веб-безопасности
        options.addArguments("--no-proxy-server");          //Don't use a proxy server
        if(Config.NO_GUI.isTrue()){
            options.addArguments("--headless");             //chrome будет запускаться без UI-части->ускоряет тесты, экономит память
            options.addArguments("--no-sandbox");           //использ.вместе с headless
        }
        return options;
    }

    final protected FirefoxOptions getCommonFirefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        if(Config.NO_GUI.isTrue()){
            options.setHeadless(true);
        }
        return options;
    }


    abstract public WebDriver createDriver();
}

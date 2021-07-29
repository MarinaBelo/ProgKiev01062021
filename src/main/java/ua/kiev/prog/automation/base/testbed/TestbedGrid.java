package ua.kiev.prog.automation.base.testbed;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import ua.kiev.prog.automation.base.Config;

import java.net.MalformedURLException;
import java.net.URL;

public class TestbedGrid extends Testbed{
    @Override
    final public WebDriver createDriver() {
        DesiredCapabilities caps = new DesiredCapabilities();
        String browserName;
        if (Config.BROWSER_NAME.value.equals("chrome")) {
            browserName = BrowserType.CHROME;
            caps.setCapability(ChromeOptions.CAPABILITY, this.getCommonChromeOptions());
        }
        else if(Config.BROWSER_NAME.value.equals("firefox")) {
            browserName = BrowserType.FIREFOX;
            caps.setCapability(ChromeOptions.CAPABILITY, this.getCommonFirefoxOptions());
        } else
            throw new RuntimeException("Browser " + Config.BROWSER_NAME.value + "is not supported");

        caps.setCapability("browserName", browserName);

        try{
            URL url = new URL("http://"
                    +Config.GRID_HOST.value + ":"
                    + Config.GRID_PORT.value +"/wd/hub");
            return new RemoteWebDriver(url, caps);
        } catch (MalformedURLException e){
            throw  new RuntimeException("URL is not valid", e);
        }
    }
}

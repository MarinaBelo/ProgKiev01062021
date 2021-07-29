package ua.kiev.prog.automation.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ua.kiev.prog.automation.base.testbed.Testbed;
import ua.kiev.prog.automation.base.testbed.TestbedGrid;
import ua.kiev.prog.automation.base.testbed.TestbedLocal;

public class Session {

        // Паттерн синглтон, когда на всю программу есть только один экземпляр класса
        final static private ThreadLocal <Session> _instance = new ThreadLocal<>();     // Приватное, статическое свойство для хранения экземпляра на уровне поля класса

        static public Session getInstance() {                       // Статический метод для получения экземпляра
            if (_instance.get() == null)
                _instance.set(new Session());
            return _instance.get();
        }

        private Session() {
        }                                                           // Приватный конструктор для запрета создания объекта извне

        private MySQLDriver _mysql;

        final public MySQLDriver mysql() {
            if(_mysql==null)
                _mysql= new MySQLDriver();
            return  _mysql;
        }

        // Описание объекта
        private Testbed _testbed;
        private WebDriver _driver;                                  // Приватное свойство для хранения экземпляра веб драйвера

        final public WebDriver driver() {                           // Метод с использованием отложенной инициализацией,
            // драйвер будет создан не при создании объекта, а при вызове метода
            if (_driver == null) {
                if("local".equalsIgnoreCase(Config.TESTBED.value)){
                    _testbed = new TestbedLocal() ;
                } else if ("grid".equalsIgnoreCase(Config.TESTBED.value)){
                    _testbed = new TestbedGrid() ;
                }else
                    throw new RuntimeException("Unknown testbed "+ Config.TESTBED.value);

                _driver = _testbed.createDriver();


               /* if (Config.BROWSER_NAME.value.equals("chrome")) {       // Блок кода перенесенный из BaseUITest
                    WebDriverManager.chromedriver().setup();            // Download latest version of Chromedriver.exe
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--start-maximized");          // =driver.manage().window().maximize();
                    options.addArguments("--disable-web-security");     //возможность обойти https//отключение веб-безопасности
                    options.addArguments("--no-proxy-server");          //Don't use a proxy server
                    if(Config.NO_GUI.isTrue()){
                        options.addArguments("--headless");             //chrome будет запускаться без UI-части->ускоряет тесты, экономит память
                        options.addArguments("--no-sandbox");           //использ.вместе с headless
                        }
                    _driver = new ChromeDriver(options);                // Create driver
                }
                 else if(Config.BROWSER_NAME.value.equals("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    FirefoxOptions options = new FirefoxOptions();
                    if(Config.NO_GUI.isTrue()){
                        options.setHeadless(true);
                    }
                    _driver = new FirefoxDriver();
                }*/
                 this._addShutdownHook();
                _driver.get(Config.SITE_URL.value);                     // Go to basic URL
                //driver.manage().window().maximize();
            }
            return _driver;
        }

        final public void quit (){                                      // Метод закрытия драйвера и обнуления переменной
            if (_driver != null)
                _driver.quit();                                         // Close driver
            _driver = null;
        }

        private void _addShutdownHook (){                                       //метод, когда крашится программа
            Runtime.getRuntime().addShutdownHook(new Thread(Session.this::quit));
        }
}




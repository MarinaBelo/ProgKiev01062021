package ua.kiev.prog.automation.base;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import ua.kiev.prog.automation.tools.Waiters;

import java.lang.reflect.Constructor;
import java.time.Instant;

public abstract class BasePage {
    final protected WebDriver driver = Session.getInstance().driver();  // Получение объекта из синглтона

    public BasePage() {
        //PageFactory.initElements(driver, this);                         // Инициализация selenium page factory
    }

    protected <T extends BasePage> T page(Class<T> pageClass) {         // Generic метод, возвращает объект типа T(который наследуется от BasePage) по классу этого объекта
        try {
            Constructor<T> constructor = pageClass.getConstructor();    // Получаем конструктор который не имеет аргументов (default)
            return constructor.newInstance();                           // Создаём объект класса
        } catch (Exception e) {
            throw new RuntimeException("Could not create instance", e); // Бросаем исключение если что-то пошло не так
        }
    }

    final public void confirmPage(){
        int timeoutSec = 30;
        long endTime = Instant.now().getEpochSecond()+timeoutSec;       //возвращает текущее время в сек.
        boolean found = false;
        while (!found && endTime > Instant.now().getEpochSecond()){
            found=this.readyElement().exists();
            if (!found)
                Waiters.sleep(500);
        }
        if(!found){
            throw new RuntimeException("Ready element is not found for page " + this.getClass().getSimpleName());
        }
    }

    abstract protected SelenideElement readyElement ();

    abstract public String getCurrentUrl ();

}
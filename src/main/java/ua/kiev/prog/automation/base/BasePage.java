package ua.kiev.prog.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.lang.reflect.Constructor;

public class BasePage {
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
}
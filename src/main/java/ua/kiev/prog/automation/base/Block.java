package ua.kiev.prog.automation.base;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import java.lang.reflect.Constructor;

abstract public class Block {                                          //->Instance нельзя создать, только наследовать

   //final protected WebDriver driver = Session.getInstance().driver();
   //final protected WebElement element;

    /*public Block(By locator) {
        try {
            element = driver.findElement(locator);
        }catch (Throwable e){
            throw new RuntimeException("Could not find block element", e);
        }
    }*/
                                                                        //вариант кода для Selenide
    final protected WebDriver driver = Session.getInstance().driver();
    final protected SelenideElement element;

    public Block(SelenideElement elem) {
        this.element = elem;                                            //здесь Exception не будет
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

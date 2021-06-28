package ua.kiev.prog.automation.base;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;


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
}

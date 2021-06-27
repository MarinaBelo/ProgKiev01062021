package ua.kiev.prog.automation.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

abstract public class Block {                                          //->Instance нельзя создать, только наследовать
   final protected WebDriver driver = Session.getInstance().driver();
   final protected WebElement element;

    public Block(By locator) {
        try {
            element = driver.findElement(locator);
        }catch (Throwable e){
            throw new RuntimeException("Could not find block element", e);
        }
    }
}

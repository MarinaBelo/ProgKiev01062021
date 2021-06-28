package ua.kiev.prog.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.tools.Waiters;
import java.util.List;

public class TestClassTest extends BaseUITest {

    //Не актуальный пример тестов/Можно удалить -> применили паттерны
    @Test
    public void testMethod() {
        //WebElement softwareLink = driver.findElement(By.xpath("//nav[@id='menu']//ul/li/a[text()='Software']"));
        WebElement tablets = driver.findElement(By.xpath("//nav[@id='menu']//ul/li[normalize-space()='Tablets']"));
        tablets.click();
        WebElement addToCart = driver.findElement(By.xpath("//div[@id='content']//div[contains(@class, 'product-thumb')]//button[1]"));
        addToCart.click();
        Waiters.sleep(1000);               // Wait 5 sec
        WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class, 'alert-success')]"));
        Assert.assertTrue(successMessage.isDisplayed(), "Success message is not displayed");
        Assert.assertTrue(successMessage.getText().contains("Успешно. Вы добавили"), "Success message text is wrong");
        //List<WebElement> menuItemsList = driver.findElements(By.cssSelector("nav#menu ul>li>a"));

/*        for (WebElement elem : menuItemsList) {
            String text = elem.getAttribute("innerText");
            //System.out.println(text);
            if ("software".equalsIgnoreCase(text)) {
                //if (text.toLowerCase().startsWith("macs")) {
                elem.click();
                // ((JavascriptExecutor)driver).executeScript("arguments[0].click();", elem);
                break;
            }
        }*/
        Waiters.sleep(3000);               // Wait 5 sec
    }
}

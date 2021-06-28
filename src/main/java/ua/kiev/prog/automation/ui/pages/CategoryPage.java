package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import ua.kiev.prog.automation.base.BasePage;

public class CategoryPage extends BasePage {
    public Integer getProductCount(){
        return driver.findElements(By.xpath("//div[contains(@class, 'product-grid')]")).size();
    }

    @Override
    protected SelenideElement readyElement() {
        return Selenide.$x("//button[@id='list-view']");
    }
}

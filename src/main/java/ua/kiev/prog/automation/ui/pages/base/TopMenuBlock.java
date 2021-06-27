package ua.kiev.prog.automation.ui.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ua.kiev.prog.automation.base.Block;

public class TopMenuBlock extends Block {
    public TopMenuBlock() {
        super(By.xpath("//nav[@id='top']"));
    }

    private WebElement _getLinkLocatorStr(String faName){
        return this.element.findElement(By.xpath(".//div[@id='top-links']/ul/li/a/i[contains(@class, '"+ faName+"')]/.."));
    }                                             //ищем относительно элемента из базового класса->xpath через точку

    public WebElement contact(){
        return _getLinkLocatorStr("fa-phone");
    }

    protected WebElement account(){
        return _getLinkLocatorStr("fa-user");
    }

    public WebElement bookmarks(){
        return _getLinkLocatorStr("fa-heart");
    }

    public WebElement cart(){
        return _getLinkLocatorStr("fa-shopping-cart");
    }

    public WebElement checkout(){
        return _getLinkLocatorStr("fa-share");
    }
}

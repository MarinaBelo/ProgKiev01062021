package ua.kiev.prog.automation.ui.pages.base;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.base.Block;
import static com.codeborne.selenide.Selenide.$x;

public class TopMenuBlock extends Block {
    public TopMenuBlock() {
        super($x("//nav[@id='top']"));
    }

    private SelenideElement _getLinkLocatorStr(String faName){
        return this.element.$x(".//div[@id='top-links']/ul/li/a/i[contains(@class, '"+ faName+"')]/..");
    }                          //ищем относительно элемента из базового класса->xpath через точку

    public SelenideElement contact(){
        return _getLinkLocatorStr("fa-phone");
    }

    protected SelenideElement account(){
        return _getLinkLocatorStr("fa-user");
    }

    public SelenideElement bookmarks(){
        return _getLinkLocatorStr("fa-heart");
    }

    public SelenideElement cart(){
        return _getLinkLocatorStr("fa-shopping-cart");
    }

    public SelenideElement checkout(){
        return _getLinkLocatorStr("fa-share");
    }
}

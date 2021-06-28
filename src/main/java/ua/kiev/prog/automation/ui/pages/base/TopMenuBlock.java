package ua.kiev.prog.automation.ui.pages.base;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.base.Block;
import ua.kiev.prog.automation.ui.widgets.Dropdown;

import static com.codeborne.selenide.Selenide.$x;

public class TopMenuBlock extends Block {
                                                       //поиск относительно элемента-родителя
    final public Dropdown currency = new Dropdown(this.element.$x(".//form[@id='form-currency']//button[@data-toggle='dropdown']"));
    final public Dropdown language = new Dropdown(this.element.$x(".//form[@id='form-language']//button[@data-toggle='dropdown']"));

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

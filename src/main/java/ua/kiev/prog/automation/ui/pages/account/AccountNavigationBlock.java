package ua.kiev.prog.automation.ui.pages.account;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.base.Block;

import java.util.List;
import java.util.stream.Collectors;

public class AccountNavigationBlock extends Block {

    public AccountNavigationBlock() {
        super(Selenide.$x("//aside[@id='column-right']"));
    }

    public List<String> getLinksList(){
        return this.element.$$x(".//a")
                .stream().map(e->e.getAttribute("href"))
                .collect(Collectors.toList());
    }

    public String getLinkHref (int index) {
        List<SelenideElement> list = element.$$x(".//a");
        if (index >= list.size()){
            throw new RuntimeException("Index value is out of range. Index: " + index + "Size: " + list.size());
        }
        return list.get(index).getAttribute("href");

    }
}

package ua.kiev.prog.automation.ui.pages.common;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.base.Block;
import static com.codeborne.selenide.Selenide.$x;

public class SearchBlock extends Block {

    final public SelenideElement inputField = this.element.$x(".//input[@name='search']");
    final public SelenideElement button     = this.element.$x(".//button[@type='button']");

    public SearchBlock() {
        super($x("//div[@id='search']"));
    }
}

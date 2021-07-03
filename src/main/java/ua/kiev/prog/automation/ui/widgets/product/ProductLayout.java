package ua.kiev.prog.automation.ui.widgets.product;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.base.Widget;

public class ProductLayout extends Widget {

    final public SelenideElement btnAddToCart = this.element.$x(".//i[contains(@class,'fa-shopping-cart')]/..");
    final public SelenideElement price = this.element.$x(".//p[contains(@class, 'price')]");

    public ProductLayout(SelenideElement elem) {
        super(elem);
    }

    final public String getPrice() {
        String rawValue = price.getAttribute("innerText");
        return rawValue.substring(0, rawValue.indexOf("\n"));
    }
}

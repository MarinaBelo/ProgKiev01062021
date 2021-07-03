package ua.kiev.prog.automation.ui.pages.common;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.base.Block;

public class ProductCartBlock extends Block {

    private SelenideElement      cartTotal   = this.element.$x(".//span[@id='cart-total']");
    private SelenideElement      popUp       = this.element.$x(".//ul[contains(@class, 'dropdown-menu')]");
    private SelenideElement      cleanButton = this.popUp.$x(".//button[contains(@onclick,'cart-remove')]");


    final public SelenideElement button = this.element.$x("./button");

    public ProductCartBlock() {
        super(Selenide.$x("//div[@id='cart']"));
    }

    public Integer getCartItemsNumber() {
        String rawValue = cartTotal.getText().trim();
        String pureValue = rawValue.substring(0, rawValue.indexOf(" "));
        return Integer.parseInt(pureValue);
    }

    public String getCartPrice () {
        String rawValue = cartTotal.getText().trim();
        String pureValue = rawValue.split("-")[1];
        return pureValue.trim();
    }

    public void clean() {
        button.click();
        if(popUp.isDisplayed())
            if (cleanButton.exists())
            cleanButton.click();
    }
}

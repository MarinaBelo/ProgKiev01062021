package ua.kiev.prog.automation.base;

import com.codeborne.selenide.SelenideElement;

public class Widget {

    final protected SelenideElement element;

    public Widget(SelenideElement elem) {
        this.element = elem;
    }
}

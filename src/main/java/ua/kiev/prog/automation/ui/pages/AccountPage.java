package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.ui.pages.base.UserSiteBasePage;

public class AccountPage extends UserSiteBasePage {

    @Override
    protected SelenideElement readyElement() {
        return Selenide.$x("//aside[@id='column-right']//a[contains(@href,'account/logout')]");
    }
}

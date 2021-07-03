package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.ui.pages.account.AccountNavigationBlock;
import ua.kiev.prog.automation.ui.pages.base.UserSiteBasePage;

public class AccountPage extends UserSiteBasePage {

    final public AccountNavigationBlock accountNavigation = new AccountNavigationBlock();

    @Override
    public SelenideElement readyElement() {
        return Selenide.$x("//aside[@id='column-right']//a[contains(@href,'account/logout')]");
    }
}

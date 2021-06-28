package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.ui.pages.base.GuestSiteBasePage;

public class IndexPage extends GuestSiteBasePage {
    public LoginPage goToLoginPage(){
        topMenu.accountAuthorization().click();
        return page(LoginPage.class);
    }

    @Override
    protected SelenideElement readyElement() {
        return topMenu.accountAuthorization();
    }
}

package ua.kiev.prog.automation.ui.pages.common.guest;

import ua.kiev.prog.automation.ui.pages.LoginPage;
import ua.kiev.prog.automation.ui.pages.base.GuestSiteBasePage;

public class IndexPage extends GuestSiteBasePage {
    public LoginPage goToLoginPage(){
        topMenu.accountAuthorization().click();
        return page(LoginPage.class);
    }
}

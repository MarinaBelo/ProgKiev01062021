package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.ui.pages.base.GuestSiteBasePage;

public class IndexPage extends GuestSiteBasePage {
    public LoginPage goToLoginPage(){
        topMenu.goToAuthorization();
        return page(LoginPage.class);
    }

    @Override
    protected SelenideElement readyElement() {
        return Selenide.$x("//nav[@id='top']//ul/li/a[contains(@href, 'account/login')]");
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}

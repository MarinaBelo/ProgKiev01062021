package ua.kiev.prog.automation.ui.pages.common.guest;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.ui.pages.base.TopMenuBlock;

public class GuestTopMenuBlock extends TopMenuBlock {

    private boolean isAccountUIDisplayed(){
        return this.account().$x("./following-sibling::ul").isDisplayed();
    }

    public SelenideElement accountRegistration(){                                                //Registration
        if(!isAccountUIDisplayed())
            this.account().click();
        return this.account().$x("./following-sibling::ul/li[1]/a");
    }

    public SelenideElement accountAuthorization(){                                               //Authorization
        if(!isAccountUIDisplayed())
            this.account().click();
        return this.account().$x("./following-sibling::ul/li[2]/a");
    }
}

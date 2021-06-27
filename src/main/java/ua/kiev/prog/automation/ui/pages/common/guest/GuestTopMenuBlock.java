package ua.kiev.prog.automation.ui.pages.common.guest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ua.kiev.prog.automation.ui.pages.base.TopMenuBlock;

public class GuestTopMenuBlock extends TopMenuBlock {
    private boolean isAccountULDisplayed(){
        return this.account().findElement(By.xpath("./following-sibling::ul")).isDisplayed();
    }

    public WebElement accountRegistration(){                                                //Registration
        if(!isAccountULDisplayed())
            this.account().click();
        return this.account().findElement(By.xpath("./following-sibling::ul/li[1]/a"));
    }

    public WebElement accountAuthorization(){                                               //Authorization
        if(!isAccountULDisplayed())
            this.account().click();
        return this.account().findElement(By.xpath("./following-sibling::ul/li[2]/a"));
    }
}

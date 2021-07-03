package ua.kiev.prog.automation.ui.pages.common.guest;

import ua.kiev.prog.automation.ui.pages.base.TopMenuBlock;

public class GuestTopMenuBlock extends TopMenuBlock {

    public void goToRegistration(){
        this.account().selectValue("Регистрация");//$x("./following-sibling::ul/li[1]/a");
    }

    public void goToAuthorization(){
        this.account().selectValue("Авторизация");//$x("./following-sibling::ul/li[2]/a");
    }
}

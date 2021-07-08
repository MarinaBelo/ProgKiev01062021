package ua.kiev.prog.automation.ui.pages.common.guest;

import ua.kiev.prog.automation.ui.pages.LoginPage;
import ua.kiev.prog.automation.ui.pages.base.TopMenuBlock;

public class GuestTopMenuBlock extends TopMenuBlock {

    public void goToRegistration(){
        this.account().selectValue(0);//Регистрация
    }

    public LoginPage goToAuthorization(){
        this.account().selectValue(1); //Авторизация
        return page(LoginPage.class);
    }
}

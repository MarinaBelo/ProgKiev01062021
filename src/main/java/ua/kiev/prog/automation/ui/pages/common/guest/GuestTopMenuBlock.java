package ua.kiev.prog.automation.ui.pages.common.guest;

import ua.kiev.prog.automation.ui.pages.AccountPage;
import ua.kiev.prog.automation.ui.pages.base.TopMenuBlock;

public class GuestTopMenuBlock extends TopMenuBlock {

    public void goToRegistration(){
        this.account().selectValue(1);//Регистрация
    }

    public void goToAuthorization(){
        this.account().selectValue(1); //Авторизация
    }
}

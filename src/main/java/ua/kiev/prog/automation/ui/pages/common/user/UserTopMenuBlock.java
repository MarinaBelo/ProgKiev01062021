package ua.kiev.prog.automation.ui.pages.common.user;

import ua.kiev.prog.automation.ui.pages.AccountPage;
import ua.kiev.prog.automation.ui.pages.base.TopMenuBlock;

public class UserTopMenuBlock extends TopMenuBlock {

    final public AccountPage goToAccountPage (){
       this.account().selectValue(0); //Личный кабинет
       return this.page(AccountPage.class);
    }

    final public void logout (){
       this.account().selectValue(4); //Выход
    }
}

package ua.kiev.prog.automation.ui.pages.base;

import ua.kiev.prog.automation.base.BasePage;
import ua.kiev.prog.automation.ui.pages.common.ProductCartBlock;
import ua.kiev.prog.automation.ui.pages.common.SearchBlock;
import ua.kiev.prog.automation.ui.pages.common.user.UserTopMenuBlock;

abstract public class UserSiteBasePage extends BasePage {
    final public UserTopMenuBlock topMenu  = new UserTopMenuBlock();
    final public MainMenuBlock    mainMenu = new MainMenuBlock();
    final public SearchBlock      search   = new SearchBlock();
    final public ProductCartBlock cart     = new ProductCartBlock();
}

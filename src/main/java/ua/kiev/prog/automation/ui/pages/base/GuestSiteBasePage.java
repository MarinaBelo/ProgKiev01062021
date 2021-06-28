package ua.kiev.prog.automation.ui.pages.base;

import ua.kiev.prog.automation.base.BasePage;
import ua.kiev.prog.automation.ui.pages.common.guest.GuestTopMenuBlock;

abstract public class GuestSiteBasePage extends BasePage {
    final public GuestTopMenuBlock topMenu  = new GuestTopMenuBlock();
    final public MainMenuBlock     mainMenu = new MainMenuBlock();
}

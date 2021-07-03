package ua.kiev.prog.automation.ui.pages.base;

import ua.kiev.prog.automation.base.Block;
import ua.kiev.prog.automation.ui.pages.CategoryPage;
import static com.codeborne.selenide.Selenide.$x;

public class MainMenuBlock extends Block {
    public MainMenuBlock() {
        super($x("//nav[@id='menu']"));
    }

    public CategoryPage goToMenu(String menu) {
        this.element.$x(".//a[text()='"+menu+"']")
                    .click();
        return new CategoryPage();
    }

    public CategoryPage goToMenu(String menu, String subMenu) {
        goToMenu(menu);
        if(subMenu!=null) {
            this.element.$x(".//a[text()='" + menu + "']/..//ul/li/a[contains(.,'" + subMenu + "')]")
                    .click();
        }
        return new CategoryPage();
    }
}

package ua.kiev.prog.automation.ui.pages.base;

import org.openqa.selenium.By;
import ua.kiev.prog.automation.base.Block;
import ua.kiev.prog.automation.ui.pages.CategoryPage;

public class MainMenuBlock extends Block {
    public MainMenuBlock() {
        super(By.xpath("//nav[@id='menu']"));
    }

    public CategoryPage goToMenu(String menu) {
        this.element.findElement(By.xpath(".//a[text()='"+menu+"']"))
                    .click();
        return new CategoryPage();
    }

    public CategoryPage goToMenu(String menu, String subMenu) {
        goToMenu(menu);
        if(subMenu!=null) {
            this.element.findElement(By.xpath(".//a[text()='" + menu + "']/..//ul/li/a[contains(.,'" + subMenu + "')]"))
                    .click();
        }
        return new CategoryPage();
    }
}

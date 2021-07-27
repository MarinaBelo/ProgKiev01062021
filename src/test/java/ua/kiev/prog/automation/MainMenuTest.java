package ua.kiev.prog.automation;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.tools.db.Category;
import ua.kiev.prog.automation.ui.pages.CategoryPage;
import ua.kiev.prog.automation.ui.pages.IndexPage;

import java.util.List;

public class MainMenuTest extends BaseUITest {

    @DataProvider(name="mainMenuItems")
    public Object[][] mainMenuItems() {
        return new Object[][] {
                {"Desktops",            "PC",           0},
                {"Desktops",            "Mac",          1},
                {"Laptops & Notebooks", "Macs",         0},
                {"Laptops & Notebooks", "Windows",      0},
                {"Tablets",             null,           1},
                {"Software",            null,           0},
                {"Phones & PDAs",       null,           3},
                {"Cameras",             null,           2}
        };
    }

    @Test(dataProvider = "mainMenuItems", groups = {"regression"})
    public void mainMenuTest(String menu, String subMenu, Integer expectedElementCount){
        IndexPage indexPage = new IndexPage();
        if(subMenu==null)
            indexPage.mainMenu.goToMenu(menu);
        else
            indexPage.mainMenu.goToMenu(menu, subMenu);
        CategoryPage categoryPage = indexPage.mainMenu.goToMenu(menu, subMenu);             //после клика->newPage
        Assert.assertEquals(categoryPage.getProductCount(), expectedElementCount,
                            "Count of products is not correct");
    }

    @Test(groups = {"regression"})
    public void checkMenuMap() {
        IndexPage indexPage = new IndexPage();

        List<Category.TreeItem> tree = db.category.getCategoriesTree();
        for (Category.TreeItem treeItem : tree) {
            System.out.println(treeItem.getItem().getName() + " - " + treeItem.getChilds().size());
            if(treeItem.getChilds().size()==0)
                indexPage.mainMenu.goToMenu(treeItem.getItem().getName());
            for (Category.TreeItem childTreeItem : treeItem.getChilds()) {
                System.out.println("\t" + childTreeItem.getItem().getName() + " - " + childTreeItem.getChilds().size());
                /*for (Category.TreeItem subChildTreeItem : childTreeItem.getChilds()) {
                    System.out.println("\t\t" + subChildTreeItem.getItem().getName() + " - " + subChildTreeItem.getChilds().size());
                }*/
                indexPage.mainMenu.goToMenu(treeItem.getItem().getName(), childTreeItem.getItem().getName());
            }
        }
    }
}

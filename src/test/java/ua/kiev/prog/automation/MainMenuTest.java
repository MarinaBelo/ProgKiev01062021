package ua.kiev.prog.automation;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.tools.db.Category;
import ua.kiev.prog.automation.ui.pages.CategoryPage;
import ua.kiev.prog.automation.ui.pages.IndexPage;

import java.util.Map;

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

    @Test(dataProvider = "mainMenuItems")
    public void mainMenuTest(String menu, String subMenu, Integer expectedElementCount){
        IndexPage indexPage = new IndexPage();
        CategoryPage categoryPage = indexPage.mainMenu.goToMenu(menu, subMenu);             //после клика->newPage
        //Integer actualElementCount = categoryPage.getProductCount();                      //на новой СategoryPage считаем
        Assert.assertEquals(categoryPage.getProductCount(), expectedElementCount,
                            "Expected product count");
    }

    @Test
    public void checkMenuMap () {
        Map<Integer, Category.Item> categoryMap = db.category.getCategories();
        for (Map.Entry<Integer, Category.Item> pair: categoryMap.entrySet()){
            Category.Item item = pair.getValue();
            System.out.println(pair.getKey() + " -[" + item.getCategoryId() +
                    " ," +item.getParentId() +" ," + item.getName() + " -]"
                    );
        }
    }
}

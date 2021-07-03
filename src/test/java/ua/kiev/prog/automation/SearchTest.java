package ua.kiev.prog.automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.base.Config;
import ua.kiev.prog.automation.tools.Waiters;
import ua.kiev.prog.automation.ui.pages.SearchPage;
import ua.kiev.prog.automation.ui.pages.SearchResultPage;

public class SearchTest extends BaseUITest {

    static private final String SEARCH_ELEMENT        = "Apple Cinema 30\"";
    static private final String SEARCH_PAGE_URL       = Config.SITE_URL.value + "/index.php?route=product/search";
    int expectedQuantityOfElementsResult              = 1;

    @Test
    public void searchPageTest(){
        SearchPage searchPage = new SearchPage();
        //Поиск продукта на сайте
        SearchResultPage searchResultPage = searchPage.navigateToSearchResultPage(SEARCH_ELEMENT);
        Waiters.sleep(1000);
        //1.Проверяем, что есть переход на страницу результат поиска
        Assert.assertTrue(searchResultPage.getCurrentUrl().startsWith(SEARCH_PAGE_URL));
        //2.Проверяем, что отображается искомый элемент
        Assert.assertTrue(searchResultPage.getSearchElement().isDisplayed(), "Searched element not found");
        //3.Проверяем количество искомых элементов
        Assert.assertEquals(searchResultPage.countOfSearchResultElement(), expectedQuantityOfElementsResult,
                "The number of search items is greater than expected");
    }
}

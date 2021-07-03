package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import ua.kiev.prog.automation.base.BasePage;
import ua.kiev.prog.automation.ui.pages.base.GuestSiteBasePage;
import ua.kiev.prog.automation.ui.widgets.product.ProductLayout;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import java.util.ArrayList;
import java.util.List;

public class SearchResultPage extends GuestSiteBasePage {

    public List<SelenideElement> searchResultElements = $$x("//div[@class='product-thumb']");
    public SelenideElement searchElement              = $x("//div[@class='product-thumb']//a[contains(text(),\"Apple Cinema 30\")]");

    public String getCurrentUrl (){
        return driver.getCurrentUrl();
    }

    public int countOfSearchResultElement(){
        return searchResultElements.size();
    }

    public WebElement getSearchElement() {
        return searchElement;
    }

    @Override
    protected SelenideElement readyElement() {
        return Selenide.$x("//label[@for='input-search']");
    }

    final public List<ProductLayout> getProducts () {
        List<ProductLayout> result = new ArrayList<>();
        List<SelenideElement> elemList = Selenide.$$x("//div[@id='content']//div[contains(@class, 'product-layout')]");
        for (SelenideElement elem: elemList){
            result.add(new ProductLayout(elem));
        }
        return result;
    }
}

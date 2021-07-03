package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.base.BasePage;
import ua.kiev.prog.automation.ui.widgets.product.ProductLayout;
import java.util.ArrayList;
import java.util.List;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage extends BasePage {

    public SelenideElement searchField  = $x("//div[@id='search']//input[@name='search']");
    public SelenideElement searchButton = $x("//div[@id='search']//button[@type='button']");

    public SearchResultPage navigateToSearchResultPage(String searchElement){
        searchField.sendKeys(searchElement);
        searchButton.click();
        return new SearchResultPage();
    }

    @Override
    protected SelenideElement readyElement() {
        return Selenide.$x("//div[@id='search']//button[@type='button']");
    }
}

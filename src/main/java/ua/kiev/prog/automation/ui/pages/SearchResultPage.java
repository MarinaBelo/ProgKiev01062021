package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import ua.kiev.prog.automation.base.BasePage;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

import java.util.List;

public class SearchResultPage extends BasePage {

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
}

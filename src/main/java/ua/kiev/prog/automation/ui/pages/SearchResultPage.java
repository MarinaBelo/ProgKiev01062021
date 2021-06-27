package ua.kiev.prog.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.kiev.prog.automation.base.BasePage;

import java.util.List;

public class SearchResultPage extends BasePage {

    @FindBy(xpath = "//div[@class='product-thumb']")
    public List<WebElement> searchResultElements;
    @FindBy(xpath = "//div[@class='product-thumb']//a[contains(text(),\"Apple Cinema 30\")]")
    public WebElement searchElement;

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

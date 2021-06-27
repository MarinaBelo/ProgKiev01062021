package ua.kiev.prog.automation.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ua.kiev.prog.automation.base.BasePage;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='search']//input[@name='search']")
    public WebElement searchField;
    @FindBy(xpath = "//div[@id='search']//button[@type='button']")
    public WebElement searchButton;

    public SearchResultPage navigateToSearchResultPage(String searchElement){
        searchField.sendKeys(searchElement);
        searchButton.click();
        return new SearchResultPage(driver);
    }

}

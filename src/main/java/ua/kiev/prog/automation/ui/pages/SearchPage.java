package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.base.BasePage;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage extends BasePage {
    /*@FindBy(xpath = "//div[@id='search']//button[@type='button']")
    @FindBy(xpath = "//div[@id='search']//input[@name='search']")*/
    public SelenideElement searchField  = $x("//div[@id='search']//input[@name='search']");
    public SelenideElement searchButton = $x("//div[@id='search']//button[@type='button']");

    public SearchResultPage navigateToSearchResultPage(String searchElement){
        searchField.sendKeys(searchElement);
        searchButton.click();
        return new SearchResultPage();
    }

    @Override
    protected SelenideElement readyElement() {
        return null;
    }
}

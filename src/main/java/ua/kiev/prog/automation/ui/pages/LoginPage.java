package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.ui.pages.base.GuestSiteBasePage;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import java.util.List;

public class LoginPage extends GuestSiteBasePage {
/*    @FindBy(xpath="//div[@id='content']//input[@id='input-email']")
    @FindBy(xpath="//div[@id='content']//input[@id='input-password']")
    @FindBy(xpath="//div[@id='content']//input[@type='submit']")
    @FindBys(@FindBy(xpath="//div[@id='account-login']//div[contains(@class, 'alert-danger')]"))*/
    public SelenideElement emailInput           = $x("//div[@id='content']//input[@id='input-email']");
    public SelenideElement passwordInput        = $x("//div[@id='content']//input[@id='input-password']");
    public SelenideElement submitBtn            = $x("//div[@id='content']//input[@type='submit']");
    public List<SelenideElement> errorMessages  = $$x("//div[@id='account-login']//div[contains(@class, 'alert-danger')]");

    public AccountPage login(String email, String password){       //Authorization
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        submitBtn.click();
        return page(AccountPage.class);
        //return login(email,password, true);
    }

    //Пример переопределенного метода
    /*public AccountPage login(String email, String password, boolean clickSubmit){
        emailInput.sendKeys(email);
        passwordInput.sendKeys(password);
        if(clickSubmit)
            submitBtn.click();
        return page(AccountPage.class);
    }*/
}

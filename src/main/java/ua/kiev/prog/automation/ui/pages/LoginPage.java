package ua.kiev.prog.automation.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import ua.kiev.prog.automation.ui.pages.base.GuestSiteBasePage;

import java.util.List;

public class LoginPage extends GuestSiteBasePage {
    @FindBy(xpath="//div[@id='content']//input[@id='input-email']" )
    public WebElement emailInput;
    @FindBy(xpath="//div[@id='content']//input[@id='input-password']")
    public WebElement passwordInput;
    @FindBy(xpath="//div[@id='content']//input[@type='submit']")
    public WebElement submitBtn;
    @FindBys(@FindBy(xpath="//div[@id='account-login']//div[contains(@class, 'alert-danger')]"))
    public List<WebElement> errorMessages;

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

package ua.kiev.prog.automation.ui.pages;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.ui.pages.base.GuestSiteBasePage;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import java.util.List;

public class LoginPage extends GuestSiteBasePage {

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

    @Override
    protected SelenideElement readyElement() {
        return emailInput;
    }

    @Override
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
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

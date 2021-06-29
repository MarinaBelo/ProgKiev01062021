package ua.kiev.prog.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ua.kiev.prog.automation.base.Session;
import ua.kiev.prog.automation.tools.Util;
import ua.kiev.prog.automation.ui.pages.AccountPage;
import ua.kiev.prog.automation.ui.pages.LoginPage;

public class LoginTestSteps {

    LoginPage loginPage     = new LoginPage();
    AccountPage accountPage = new AccountPage();

    //Scenario #1
    @Given("^I am navigating to login page$")
    public void iAmNavigatingToLoginPage(){
        Session.getInstance().driver().get("http://zvisno.com/index.php?route=account/login");
    }

    @When("^I enter valid email \"(.+?)\" to email field$")
    public void iEnterValidEmailToEmailField(String value) {
        loginPage.emailInput.sendKeys(value);
    }

    @And("^I enter valid password \"(.+?)\" to password field$")
    public void iEnterValidPasswordToPasswordField(String value) {
        loginPage.passwordInput.sendKeys(value);
    }

    @And("^I click login button$")
    public void iClickLoginButton() {
        loginPage.submitBtn.click();
    }
    @Then("^account page is displayed$")
    public void AccountPageIsDisplayed() {
        accountPage.confirmPage();
    }

    //Scenario #2
    @When("^I enter invalid email \"(.+?)\" to email field$")
    public void iEnterInvalidEmailToEmailField(String value) {
        loginPage.emailInput.sendKeys(value = Util.randomString(10)+"@domain.com");
    }

    @Then("^account page is not displayed$")
    public void AccountPageIsNotDisplayed() {
        Assert.assertFalse(accountPage.readyElement().isDisplayed(), "Account Page Is Displayed");
    }

    //Scenario #3
    @And("^I enter invalid password \"(.+?)\" to password field$")
    public void iEnterInvalidPasswordToPasswordField(String value) {
        loginPage.passwordInput.sendKeys(value);
    }
}

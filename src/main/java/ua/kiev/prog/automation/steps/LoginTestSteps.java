package ua.kiev.prog.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import ua.kiev.prog.automation.base.Session;
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

    @When("^I enter \"(.+?)\" to email field$")
    public void iEnterToEmailField(String value) {
        loginPage.emailInput.sendKeys(value);
    }
    @And("^I enter \"(.+?)\" to password field$")
    public void iEnterToPasswordField(String value) {
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

    //Scenario #3
}

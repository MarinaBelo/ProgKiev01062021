package ua.kiev.prog.automation.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import ua.kiev.prog.automation.base.Config;
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
        Session.getInstance().driver().get(Config.SITE_URL.value + "/index.php?route=account/login");
    }

    @When("^I enter \"(.+?)\" to email field$")
    public void iEnterEmailToEmailField(String value) {
        if ("$_INVALID_EMAIL_%".equals(value)){
            value = Util.randomString(10)+"@domain.com";
        }
        loginPage.emailInput.sendKeys(value);
    }

    @And("^I enter \"(.+?)\" to password field$")
    public void iEnterPasswordToPasswordField(String value) {
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

    @Then("^Login result must be \"(.+?)\" and error message \"(.+?)\" is displayed$")
    public void loginResultMustBeResultAndErrorMessageIsDisplayed(String result, String errorMessage){
        if ("success".equalsIgnoreCase(result)){
            accountPage.confirmPage();
        }else if("fail".equalsIgnoreCase(result)){
            loginPage.confirmPage();
            Assert.assertTrue(loginPage.isErrorMessageDisplayed(errorMessage),
                    "Error message is not displayed: " + errorMessage);
        }else
            throw new RuntimeException("Unknown result: " + result);
    }
}



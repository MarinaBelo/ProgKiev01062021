package ua.kiev.prog.automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.tools.Util;
import ua.kiev.prog.automation.tools.Waiters;
import ua.kiev.prog.automation.ui.pages.AccountPage;
import ua.kiev.prog.automation.ui.pages.LoginPage;
import ua.kiev.prog.automation.ui.pages.common.guest.IndexPage;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class LoginTest extends BaseUITest {

    /*static private final String LOGIN_URL         = "http://zvisno.com/index.php?route=account/login";
    static private final By ACCOUNT_LOCATOR       = By.xpath("//div[@id='top-links']/ul/li/a/i[contains(@class, 'fa-user')]/..");
    static private final By AUTH_LOCATOR          = By.xpath("./following-sibling::ul/li[2]/a");*/
    static private final String EMAIL_VALID       = "yurii.voronenko@gmail.com";
    static private final String EMAIL_INVALID     = Util.randomString(10)+"@domain.com"; //генерация рандомных невалидных данных
    //static private final String EMAIL_INVALID     = UUID.randomUUID().toString();           //генерация рандомных невалидных данных, но не самый красивый вариант для имейла
    //static private final String EMAIL_INVALID     = "invalidLogin@gmail123";
    static private final String PASSWD_VALID      = "12345678";
    static private final String PASSWD_INVALID    = "123456";

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
            {EMAIL_VALID,               PASSWD_VALID, null},
            {EMAIL_VALID.toUpperCase(), PASSWD_VALID, null},
            {EMAIL_INVALID,             PASSWD_VALID,   "Предупреждение: Не совпадает адрес электронной почты и/или пароль."},
            {EMAIL_VALID,               PASSWD_INVALID, "Предупреждение: Не совпадает адрес электронной почты и/или пароль."},    
        };
    }

    @Test(dataProvider = "loginData")
    public void loginTest(String email, String password, String errorMessage) {
        System.out.println(email);
        IndexPage landingPage = new IndexPage();
        LoginPage loginPage = landingPage.goToLoginPage();
        AccountPage accountPage = loginPage.login(email, password);
        Waiters.sleep(1000);

        List<WebElement> errorMessages = loginPage.errorMessages;
        if (errorMessage == null) {
            Assert.assertEquals(errorMessages.size(), 0, "Error messages are shown" +
                    //errorMessageList.stream().map(e->e.getText()).collect(Collectors.toList())); //преобразовываем из листа WebElements в лист Strings
                    errorMessages.stream()
                            .map(WebElement::getText)
                            .collect(Collectors.toList()));
        } else {
            Assert.assertTrue(errorMessages.size() > 0, "There is no any error message");
            Assert.assertEquals(errorMessages.get(0).getText(), errorMessage, "Error message is not as expected");
        }
    }
}

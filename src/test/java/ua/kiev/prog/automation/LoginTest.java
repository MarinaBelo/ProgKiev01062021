package ua.kiev.prog.automation;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.base.Config;
import ua.kiev.prog.automation.tools.Util;
import ua.kiev.prog.automation.tools.Waiters;
import ua.kiev.prog.automation.ui.pages.LoginPage;
import ua.kiev.prog.automation.ui.pages.IndexPage;
import ua.kiev.prog.automation.ui.pages.UserIndexPage;

import java.util.List;
import java.util.stream.Collectors;

public class LoginTest extends BaseUITest {

    static private final String EMAIL_INVALID     = Util.randomString(10)+"@domain.com"; //генерация рандомных невалидных данных
    //static private final String EMAIL_INVALID     = UUID.randomUUID().toString();           //генерация рандомных невалидных данных, но не самый красивый вариант для имейла
    //static private final String EMAIL_INVALID     = "invalidLogin@gmail123";
    static private final String PASSWD_INVALID    = "123456";

    @DataProvider(name = "loginData")
    public Object[][] loginData() {
        return new Object[][]{
            {Config.SITE_USERNAME.value,               Config.SITE_PASSWORD.value, null},
            {Config.SITE_USERNAME.value.toUpperCase(), Config.SITE_PASSWORD.value, null},
            {EMAIL_INVALID,                            Config.SITE_PASSWORD.value, "Предупреждение: Не совпадает адрес электронной почты и/или пароль."},
            {Config.SITE_USERNAME.value,               PASSWD_INVALID,             "Предупреждение: Не совпадает адрес электронной почты и/или пароль."},
        };
    }

    public void loginTest(String email, String password, String errorMessage) {
        System.out.println(email);
        IndexPage landingPage = new IndexPage();
        LoginPage loginPage = landingPage.goToLoginPage();
        UserIndexPage accountPage = loginPage.login(email, password);
        Waiters.sleep(1000);

        List<SelenideElement> errorMessages = loginPage.errorMessages;
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

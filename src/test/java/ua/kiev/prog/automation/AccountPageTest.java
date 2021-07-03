package ua.kiev.prog.automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.ui.pages.AccountPage;
import ua.kiev.prog.automation.ui.pages.UserIndexPage;
import java.util.Arrays;
import java.util.List;

public class AccountPageTest extends BaseUITest {

    UserIndexPage userIndexPage = new UserIndexPage();
    List<String> expectedLinks = Arrays.asList(
            "http://zvisno.com/index.php?route=account/account",
            "http://zvisno.com/index.php?route=account/edit",
            "http://zvisno.com/index.php?route=account/password",
            "http://zvisno.com/index.php?route=account/address",
            "http://zvisno.com/index.php?route=account/wishlist",
            "http://zvisno.com/index.php?route=account/order",
            "http://zvisno.com/index.php?route=account/download",
            "http://zvisno.com/index.php?route=account/recurring",
            "http://zvisno.com/index.php?route=account/reward",
            "http://zvisno.com/index.php?route=account/return",
            "http://zvisno.com/index.php?route=account/transaction",
            "http://zvisno.com/index.php?route=account/newsletter",
            "http://zvisno.com/index.php?route=account/logout"
    );

    @Test
    public void testRightMenu (){
        api.loginIntoWebsite();
        userIndexPage.confirmPage();
        AccountPage accountPage = userIndexPage.topMenu.goToAccountPage();
        accountPage.confirmPage();

        List<String> actualLinks = accountPage.accountNavigation.getLinksList();
        Assert.assertEquals(actualLinks.size(), expectedLinks.size(),
                "List size is not match. \n" +
                        "Expected: " + expectedLinks + "\n" +
                        "Actual:   " + actualLinks
        );

        SoftAssert softAssert = new SoftAssert();
        for (int i=0;  i< expectedLinks.size(); i++){
            softAssert.assertEquals(accountPage.accountNavigation.getLinkHref(i), expectedLinks.get(i));
        }
        softAssert.assertAll();
    }

}

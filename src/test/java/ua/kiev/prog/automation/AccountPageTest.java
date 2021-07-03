package ua.kiev.prog.automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.base.Config;
import ua.kiev.prog.automation.ui.pages.AccountPage;
import ua.kiev.prog.automation.ui.pages.UserIndexPage;
import java.util.Arrays;
import java.util.List;

public class AccountPageTest extends BaseUITest {

    UserIndexPage userIndexPage = new UserIndexPage();
    List<String> expectedLinks = Arrays.asList(
            Config.SITE_URL.value + "/index.php?route=account/account",
            Config.SITE_URL.value + "/index.php?route=account/edit",
            Config.SITE_URL.value + "/index.php?route=account/password",
            Config.SITE_URL.value + "/index.php?route=account/address",
            Config.SITE_URL.value + "/index.php?route=account/wishlist",
            Config.SITE_URL.value + "/index.php?route=account/order",
            Config.SITE_URL.value + "/index.php?route=account/download",
            Config.SITE_URL.value + "/index.php?route=account/recurring",
            Config.SITE_URL.value + "/index.php?route=account/reward",
            Config.SITE_URL.value + "/index.php?route=account/return",
            Config.SITE_URL.value + "/index.php?route=account/transaction",
            Config.SITE_URL.value + "/index.php?route=account/newsletter",
            Config.SITE_URL.value + "/index.php?route=account/logout"
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

package ua.kiev.prog.automation;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.base.Config;
import ua.kiev.prog.automation.ui.pages.IndexPage;
import ua.kiev.prog.automation.ui.pages.LoginPage;
import ua.kiev.prog.automation.ui.pages.SearchResultPage;
import ua.kiev.prog.automation.ui.pages.UserIndexPage;
import ua.kiev.prog.automation.ui.widgets.product.ProductLayout;

public class AddProductToCartTest extends BaseUITest {

    @BeforeMethod
    public void preconscious(){
        api.loginIntoWebsite();
        UserIndexPage userIndexPage = new UserIndexPage();
        userIndexPage.cart.clean();
        userIndexPage.topMenu.logout();
    }

    @Test
    public void addProductAndLogin() {
        IndexPage indexPage = new IndexPage();
        indexPage.search.inputField.sendKeys("iMac");
        indexPage.search.button.click();
        SearchResultPage searchResult = new SearchResultPage();
        Assert.assertTrue(searchResult.getProducts().size() >0, "Products not found");
        ProductLayout product = searchResult.getProducts().get(0);
        String productPrice = product.getPrice();
        System.out.println("Price: " +productPrice);
        product.btnAddToCart.click();

        searchResult.topMenu.goToAuthorization();
        LoginPage loginPage = new LoginPage();
        loginPage.emailInput.sendKeys(Config.SITE_USERNAME.value);
        loginPage.passwordInput.sendKeys(Config.SITE_PASSWORD.value);
        loginPage.submitBtn.click();

        UserIndexPage userIndexPage = new UserIndexPage();
        Assert.assertEquals(userIndexPage.cart.getCartItemsNumber(), (Integer) 1, "Product count in cart is wrong");
        Assert.assertEquals(userIndexPage.cart.getCartPrice(), productPrice, "Price in cart is wrong");


    }
}

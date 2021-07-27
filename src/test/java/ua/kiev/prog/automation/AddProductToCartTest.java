package ua.kiev.prog.automation;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.base.Config;
import ua.kiev.prog.automation.tools.Waiters;
import ua.kiev.prog.automation.ui.pages.IndexPage;

import ua.kiev.prog.automation.ui.pages.LoginPage;
import ua.kiev.prog.automation.ui.pages.SearchResultPage;
import ua.kiev.prog.automation.ui.pages.UserIndexPage;
import ua.kiev.prog.automation.ui.widgets.product.ProductLayout;

public class AddProductToCartTest extends BaseUITest {

    @BeforeMethod
    @Description("Clear user cart")
    public void preconscious(){
        api.loginIntoWebsite();
        UserIndexPage userIndexPage = new UserIndexPage();
        userIndexPage.cart.clean();
        Waiters.sleep(1000);
        userIndexPage.topMenu.logout();
    }

    @Test(testName = "Add product and login", groups = {"smoke","regression"})
    @Description("Search product, add to cart, login and check products")
    public void addProductAndLogin() {
        IndexPage indexPage = new IndexPage();
        SearchResultPage searchResult = indexPage.search.search("iMac");
        Assert.assertTrue(searchResult.getProducts().size() >0, "Products not found");

        ProductLayout product = searchResult.getProducts().get(0);
        String productPrice = product.getPrice();
        System.out.println("Price: " +productPrice);
        product.btnAddToCart.click();

        //при логине через АПИ, сервер ничего не знает о том что было добавлено в корзину,
        // логиниться нужно или через GUI или при запросе АПИ передавать текущие куки браузера

        LoginPage loginPage = searchResult.topMenu.goToAuthorization();
        UserIndexPage userIndexPage = loginPage.login(Config.SITE_USERNAME.value,Config.SITE_PASSWORD.value);
        Waiters.sleep(5000);
        Assert.assertEquals(userIndexPage.cart.getCartItemsNumber(), (Integer) 1, "Product count in cart is wrong");
        Assert.assertEquals(userIndexPage.cart.getCartPrice(), productPrice, "Price in cart is wrong");
    }
}

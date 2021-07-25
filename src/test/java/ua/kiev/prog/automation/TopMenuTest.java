package ua.kiev.prog.automation;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import ua.kiev.prog.automation.base.BaseUITest;
import ua.kiev.prog.automation.tools.Waiters;
import ua.kiev.prog.automation.ui.pages.IndexPage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TopMenuTest extends BaseUITest {

    String [] expectedCurrency = new String[]{
            "€ Euro",
            "$ US Dollar",
            "£ Pound Sterling",
            //"gfgfggf"
    };

    String [] expectedLanguage = new String[]{
            "Українська",
            "English",
            "Русский"
            //"Spanish"
    };

    @Test(groups = {"regression"})
    public void topMenuTest (){
        IndexPage indexPage = new IndexPage();

        List<String> expectedCurrencyList = Arrays.asList(expectedCurrency);
        List<String> actualCurrencyList = indexPage.topMenu.currency.getValues();
        List<String> expectedLanguageList = Arrays.asList(expectedLanguage);
        List<String> actualLanguageList = indexPage.topMenu.language.getValues();

        Collections.sort(expectedCurrencyList);
        Collections.sort(actualCurrencyList);
        Collections.sort(expectedLanguageList);
        Collections.sort(actualLanguageList);

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(expectedCurrencyList, actualCurrencyList, "Currency lists are not equal." +
                "\nExpected: " + expectedCurrencyList +
                "\nActual:   " + actualCurrencyList +
                "\n");
        softAssert.assertEquals(expectedLanguageList, actualLanguageList, "Language lists are not equal." +
                "\nExpected: " + expectedLanguageList +
                "\nActual:   " + actualLanguageList +
                "\n");

        softAssert.assertAll();                               //если была ошибка выше ->здесь она выпадет

        List<String> actualCurrency = indexPage.topMenu.currency.getValues();
        for(String curr: expectedCurrency){
            Assert.assertTrue(actualCurrency.contains(curr), "Currency \"" +curr+"\" is not found");
            Assert.assertTrue(indexPage.topMenu.currency.hasValue(curr), "Currency \"" +curr+"\" is not found");
        }
        List<String> actualLanguage = indexPage.topMenu.language.getValues();
        for(String lang: expectedLanguage){
            Assert.assertTrue(actualLanguage.contains(lang), "Language \"" +lang+"\"is not found");
            Assert.assertTrue(indexPage.topMenu.language.hasValue(lang), "Language \"" +lang+"\"is not found");
        }

        indexPage.topMenu.currency.selectValue("$ US Dollar");
        indexPage.topMenu.language.selectValue("Українська");
        Waiters.sleep(3000);
    }
}

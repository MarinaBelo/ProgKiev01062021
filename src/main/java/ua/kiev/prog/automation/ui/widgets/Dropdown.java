package ua.kiev.prog.automation.ui.widgets;

import com.codeborne.selenide.SelenideElement;
import ua.kiev.prog.automation.base.Widget;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dropdown extends Widget {

    final private SelenideElement _menuList;

    public Dropdown(SelenideElement elem) {
        super(elem);
        _menuList = this.element.$x("../ul[contains(@class, 'dropdown-menu')]");
    }

    public void clickOnDropdown(){
        this.element.click();
    }

    public List<String> getValues(){
        /*List<String> result = new ArrayList<>();
        List <SelenideElement> liList = _menuList.$$x("./li");
        for(SelenideElement item: liList){
            result.add(item.getAttribute("innerText").trim());
        }
        return result;*/
        return _menuList.$$x("./li")
                .stream()
                .map(e->e.getAttribute("innerText").trim())
                .collect(Collectors.toList());
    }

    public void selectValue(String value){
        value = value.trim();
        if (!_menuList.isDisplayed()) {
            this.element.click();
        }
        SelenideElement valToSelect = _menuList.$x("./li[normalize-space()='"+value+"']");
        if (valToSelect.exists()){
            valToSelect.click();
        } else {
            throw  new RuntimeException("Value \"" + value +"\" is not found for dropdown");
        }
    }

    public boolean hasValue (String value) {
        return _menuList.$x("./li[normalize-space()='"+value+"']").exists();
    }
}

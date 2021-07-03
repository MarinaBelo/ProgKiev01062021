package ua.kiev.prog.automation.tools;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.Cookie;
import ua.kiev.prog.automation.base.Config;
import ua.kiev.prog.automation.base.Session;

import java.util.Map;

public class API {
    //вместо перехода на loginPage и нажимать Submit -> логин с помощью api-метода и подставлять
    //нужные куки, которые придут от сервера и обновление страницы

    public  void loginIntoWebsite(){

        Response response = RestAssured.given()
                .param("email", Config.SITE_USERNAME.value)
                .param("password", Config.SITE_PASSWORD.value)
                .post(Config.SITE_URL.value + "/index.php?route=account/login")
                .then().extract().response();
        for (Map.Entry<String, String> cookie: response.getCookies().entrySet()){ //берем ключ-значение из каждого куки
            String param = cookie.getKey();
            String value = cookie.getValue();
            Session.getInstance().driver().manage().addCookie(new Cookie(param, value)); //передать все куки в браузер
        }

        //обновляем страницу в браузере->вебрайвер по умолчанию не знает, что у нас добавлены куки, нам нужно переотправить запрос серверу
        Session.getInstance().driver().navigate().refresh();


    }
}

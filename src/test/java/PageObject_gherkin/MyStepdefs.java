package PageObject_gherkin;

import PageObject_gherkin.app.Application;
import io.cucumber.java8.En;
import io.cucumber.junit.Cucumber;
import org.testng.Assert;

public class MyStepdefs implements En {
    private static Application app;


    public MyStepdefs() {
        Before("",() ->{
            app = new Application();
        });

        When("Добавим {} единицы товара в корзину", (Integer a) -> {
            app.add_to_basket(a);
        });
        And("Удалим все товары из коризины", () -> {
            app.remove_from_basket();
        });
        And("Перeйдем на главную страницу магазина", () -> {
            app.go_to_main_page();
        });
        And("Проверим является ли последняя отрытая страница главной", () -> {
            app.check_main_page();
            app.quit();
        });

    }
}

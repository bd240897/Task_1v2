package PageObject_gherkin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Page_basket extends page
{
    public Page_basket(WebDriver driver) {
        super(driver);
    }

    // открыть корзину
    public void open_basket_page()
    {
        driver.navigate().to("https://litecart.stqa.ru/en/");
        WebElement check_out = driver.findElement(By.cssSelector("div#cart-wrapper a.link"));
        String href = check_out.getAttribute("href");
        driver.navigate().to(href);
    }

    // удалить все элементы из корзины
    public void delet_all_product(){
        // длина карусели для удаления
        List<WebElement> shortcuts = driver.findElements(By.cssSelector("ul.shortcuts li a"));

        // пробежимя по товарам и удалим их
        for (int i = 0; i < shortcuts.size(); i++) {
            // находим таблицу товаров
            WebElement table = wait.until(presenceOfElementLocated(By.cssSelector("table.dataTable.rounded-corners")));


            List<WebElement> shortcuts_current = driver.findElements(By.cssSelector("li.shortcut"));
            // кликнем на маленькую иконку корусели
            if (shortcuts_current.size() > 0) {
                shortcuts_current.get(0).click();
            }

            // нажмем на кнопку удалить
            WebElement remove = driver.findElement(By.cssSelector("button[name=remove_cart_item]"));
            remove.click();

            // дождемся удаления
            wait.until(stalenessOf(table));
        }
    }
}

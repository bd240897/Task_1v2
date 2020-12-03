package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;



public class Application {
    private WebDriverWait wait;
    private WebDriver driver;
    private Page_main page_main;
    private Page_basket page_basket;

    public Application() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait  = new WebDriverWait(driver, 30);

        page_main = new Page_main(driver);
        page_basket = new Page_basket((driver));
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    public void add_to_basket() {
        for (int i = 0; i <= 5; i++) {

            // передйем на главную страницу
            page_main.open_main_page();

            // получим ссылку на счетчик
            WebElement counter = page_main.numb_item();

//          // откроем первый продукт
            page_main.open_first_product();

            // кликнем по первому продукту
            page_main.clik_on_first_product();

            // дождемся обновление счетчика
            page_main.waiting_for_update_counter(counter, i);
        }
    }

    public void remove_from_basket()
    {
        // передйем в корзину
        page_basket.open_basket_page();

        // удалим все товра из корзины
        page_basket.delet_all_product();

    }

        public void go_to_main_page()
        {
            // передйем на главную страницу
            driver.get("https://litecart.stqa.ru/en/");
        }
}


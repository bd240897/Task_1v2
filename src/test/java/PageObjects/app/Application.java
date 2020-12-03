package PageObjects.app;

import PageObjects.pages.Page_basket;
import PageObjects.pages.Page_main;
import PageObjects.pages.Page_product;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;


public class Application {
    private WebDriverWait wait;
    private WebDriver driver;
    private Page_main page_main;
    private Page_basket page_basket;
    private Page_product page_product;

    public Application() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait  = new WebDriverWait(driver, 30);

        page_main = new Page_main(driver);
        page_basket = new Page_basket(driver);
        page_product = new Page_product(driver);
    }

    public void quit() {
        driver.quit();
        driver = null;
    }

    public void add_to_basket(int count_of_product) {
        for (int i = 0; i < count_of_product; i++) {

            // передйем на главную страницу
            page_main.open_main_page();

            // получим ссылку на счетчик
            WebElement counter = page_main.numb_item();

//          // откроем первый продукт
            page_main.open_first_product();

            // кликнем по первому продукту
            page_product.clik_on_first_product();

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
        page_main.open_main_page();
    }
}


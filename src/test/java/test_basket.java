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
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;


public class test_basket {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

        driver = new ChromeDriver();
//        driver = new InternetExplorerDriver();

//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
//        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


//        FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe"));
//        WebDriver driver = new FirefoxDriver(bin, new FirefoxProfile());

        wait  = new WebDriverWait(driver, 30);
    }

    @Test
    public void test_test_basket_1() {

        //откроем главную страницу

        // переменный
//        WebElement numb_item;
//        WebElement first_product;
//        WebElement add_to_cart;
//        WebElement yellow_duck;
//        Select select;


        for (int i = 0; i <=2; i++){

            // передйем на сайт магазина
            driver.navigate().to("https://litecart.stqa.ru/en/");

            // получим ссылку на счетчик
            WebElement numb_item = driver.findElement(By.cssSelector("span.quantity"));

            // откроем первый продукт
            WebElement first_product = driver.findElement(By.cssSelector("div#box-most-popular ul.listing-wrapper.products li:nth-child(1)"));
            first_product.click();

            // смотрим названеи продукта
            WebElement w_title = driver.findElement(By.cssSelector("h1"));
            String title = w_title.getAttribute("outerText");

            // если он желтая утка
            WebElement add_to_cart;
            if (title.equals("Yellow Duck")){
                WebElement yellow_duck = driver.findElement(By.cssSelector("select"));
                Select select = new Select(yellow_duck);
                select.selectByValue("Small");
                // кликнем на кропку добавить
                add_to_cart = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
                add_to_cart.click();
            }
            else{
                // кликнем на кропку добавить
                add_to_cart = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
                add_to_cart.click();
            }


            // дождемся обновление счетчика
            wait.until(stalenessOf(numb_item));
            wait.until(ExpectedConditions.attributeToBe(By.cssSelector("span.quantity"), "innerText", String.valueOf(i+1)));

            //System.out.println(i);
        }

        // передйем в корзину
        WebElement check_out = driver.findElement(By.cssSelector("div#cart-wrapper a.link"));
        String href = check_out.getAttribute("href");
        driver.navigate().to(href);


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

        // передйем на главную страницу
        driver.get("https://litecart.stqa.ru/en/");
    }



    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

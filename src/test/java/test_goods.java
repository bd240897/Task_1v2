import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;


public class test_goods {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
//        driver = new ChromeDriver();
//        driver = new InternetExplorerDriver();

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


//        FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe"));
//        WebDriver driver = new FirefoxDriver(bin, new FirefoxProfile());

        wait  = new WebDriverWait(driver, 10);
    }

    @Test
    public void test_goods_1() {
        driver.navigate().to("http://localhost/litecart/en/");

        //НАХОДИМСЯ НА ГЛАВНОЙ СТРАНИЦЕ
        System.out.println("НАХОДИМСЯ НА ГЛАВНОЙ СТРАНИЦЕ");

        // откроем первый элемент
        WebElement duck_1 = driver.findElement(By.cssSelector("div#box-campaigns div.content ul.listing-wrapper.products"));

        // названеи товара
        WebElement w_name = duck_1.findElement(By.cssSelector("div.name"));
        String name = w_name.getAttribute("innerText");
//        System.out.println("Название: " + name);

        // cылка на элемент - ГС
        WebElement w_ref = duck_1.findElement(By.cssSelector("a.link"));
        String ref = w_ref.getAttribute("href");
//        System.out.println("Ссылка:" + ref);

        // цена обычная
        WebElement w_price = duck_1.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String price = w_price.getAttribute("innerText");
//        System.out.println("Цена обычная:" + price);

        // цена аукционная
        WebElement w_price_sale = duck_1.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String price_sale = w_price_sale.getAttribute("innerText");
//        System.out.println("Цена_аукционная:" + price_sale);

        // обычная цена - зачеркнутая и сера
        WebElement w_price_style = duck_1.findElement(By.cssSelector("div.price-wrapper s.regular-price"));
        String price_style_colour = w_price_style.getCssValue("color");
        String price_style_line= w_price_style.getCssValue("text-decoration-line");
//        System.out.println("Цена обычная: цвет " + price_style_colour + " cтиль " + price_style_line);
        String price_style_size = w_price_style.getCssValue("font-size");
//        System.out.println("Размер цена:" + price_style_size);

        // аукционная цена жирная и красная
        WebElement w_price_sale_style = duck_1.findElement(By.cssSelector("div.price-wrapper strong.campaign-price"));
        String price_sale_style_colour = w_price_sale_style.getCssValue("color");
        String price_sale_style_line = w_price_sale.getCssValue("font-weight");
//        System.out.println("Цена аукц: цвет " + price_sale_style_colour + " cтиль " + price_sale_style_line);
        String price_sale_style_size = w_price_sale_style.getCssValue("font-size");
//        System.out.println("Размер цены аукц: " + price_sale_style_size);

        // ПРОВЕРКИ
        // проверка цвета
        String channel_1, channel_2, channel_3;
          if (driver instanceof ChromeDriver){
             channel_1 = price_style_colour.substring(5, 8);
             channel_2 = price_style_colour.substring(10, 13);
             channel_3 = price_style_colour.substring(15, 18);
          }
          else if (driver instanceof FirefoxDriver){
             channel_1 = price_style_colour.substring(4, 7);
             channel_2 = price_style_colour.substring(9, 12);
             channel_3 = price_style_colour.substring(14, 17);
          }
          else {
              channel_1 = "";
              channel_2 = "";
              channel_3 = "";
          }

        if (channel_1.equals(channel_2)  && channel_2.equals(channel_3) )
            System.out.println("в1) Цвет обычной цены на главной странице серый!");
        else
            System.out.println("в1) Цвет обычной цены на главной странице НЕ серый");

        // проверка стиля
        System.out.println("в2) Обычная цена на главной странице зачеркнутая? - " + price_style_line.equals("line-through"));

        // аукционная цена жирная и красная
        // проверка цвета
        if (driver instanceof ChromeDriver){
            channel_1 = price_style_colour.substring(5, 8);
            channel_2 = price_style_colour.substring(10, 11);
            channel_3 = price_style_colour.substring(12, 13);
        }
        else if (driver instanceof FirefoxDriver){
            channel_1 = price_sale_style_colour.substring(4, 7);
            channel_2 = price_sale_style_colour.substring(9, 10);
            channel_3 = price_sale_style_colour.substring(12, 13);
        }
        else {
            channel_1 = "";
            channel_2 = "";
            channel_3 = "";
        }

        if (Integer.parseInt(channel_1)>0  && channel_2.equals("0") && channel_3.equals("0"))
            System.out.println("г1) Цвет аукц цены на главной странице касный!");
        else
            System.out.println("г1) Цвето аукц цены на главной странице НЕ касный");

        // проверка стиля
        if (driver instanceof ChromeDriver) {
            System.out.println("г2) Аукц цена на главной стринице жирная? - " + price_sale_style_line.equals("700"));
        }
        else if (driver instanceof FirefoxDriver){
            System.out.println("г2) Аукц цена на главной стринице жирная? - " + price_sale_style_line.equals("900"));
        }
        // Размер на странице
        int one = Integer.parseInt(price_sale_style_size.substring(0, 2));
        int two = Integer.parseInt(price_style_size.substring(0, 2));
        System.out.println("д) Аукционная цена на главной странице больше обычной:? - " + (one > two));

                                        //ПЕРЕЙДЕМ НА СТРАНИЦЕ ТОВАРА
        System.out.println("НАХОДИМСЯ НА СТРАНИЦЕ ТОВАРА");
        driver.navigate().to(ref);

        // названеи товара
        String  page_name = driver.findElement(By.cssSelector("h1")).getAttribute("innerText");;
//        System.out.println("На странице Название: " + page_name);

        // цена обычная
        String  page_price = driver.findElement(By.cssSelector("s")).getAttribute("innerText");
//        System.out.println("На странице Цена обычная:" + page_price);

        // цена аукционная
        String  page_price_sale = driver.findElement(By.cssSelector("strong.campaign-price")).getAttribute("innerText");
//        System.out.println("На странице Цена аукц: " + page_price_sale);

        // обычная цена - зачеркнутая и сера
        WebElement w_page_price_style = driver.findElement(By.cssSelector("s"));
        String page_price_style_colour = w_page_price_style.getCssValue("color");
        String page_price_style_line= w_page_price_style.getCssValue("text-decoration-line");
//        System.out.println("На странице Цена обычная: цвет " + page_price_style_colour + " cтиль " + page_price_style_line);
        String page_price_style_size = w_page_price_style.getCssValue("font-size");
//        System.out.println("На странице Размер цена: " + page_price_style_size);

        // аукционная цена жирная и красная
        WebElement w_page_price_sale_style = driver.findElement(By.cssSelector("strong.campaign-price"));
        String page_price_sale_style_colour = w_page_price_sale_style.getCssValue("color");
        String page_price_sale_style_line = w_page_price_sale_style.getCssValue("font-weight");
//        System.out.println("На странице Цена аукц: цвет " + page_price_sale_style_colour + " cтиль " + page_price_sale_style_line);
        String page_price_sale_style_size = w_page_price_sale_style.getCssValue("font-size");
//        System.out.println("На странице Размер цена аукц: " + page_price_sale_style_size);


        // СРАВНЕНИЯ
        // названеи товара
        System.out.println("а) Название товара совпадает на обеих страницах? - " + name.equals(page_name));
        // цена обычная
        System.out.println("б1) Цена обычная товара совпадает на обеих страницах? - " + page_price.equals(price));
        // цена аукционная
        System.out.println("б2) Цена аукц товара совпадает на обеих страницах? - " + page_price_sale.equals(price_sale));


// обычная цена - зачеркнутая и сера
        // проверка цвета
        if (driver instanceof ChromeDriver){
            channel_1 = price_style_colour.substring(5, 8);
            channel_2 = price_style_colour.substring(10, 13);
            channel_3 = price_style_colour.substring(15, 18);
        }
        else if (driver instanceof FirefoxDriver){
            channel_1 = price_style_colour.substring(4, 7);
            channel_2 = price_style_colour.substring(9, 12);
            channel_3 = price_style_colour.substring(14, 17);
        }
        else {
            channel_1 = "";
            channel_2 = "";
            channel_3 = "";
        }
        if (channel_1.equals(channel_2)  && channel_2.equals(channel_3) )
            System.out.println("в1) Цвет обычной цены на открытой странице серый!");
        else
            System.out.println("в1) Цвет обычной цены на открытой странице НЕ серый");

        // проверка стиля
        System.out.println("в2) Обычная цена на открытой странице зачеркнутая? - " + page_price_style_line.equals("line-through"));

        // аукционная цена жирная и красная
        // проверка цвета
        if (driver instanceof ChromeDriver){
            channel_1 = price_style_colour.substring(5, 8);
            channel_2 = price_style_colour.substring(10, 11);
            channel_3 = price_style_colour.substring(12, 13);
        }
        else if (driver instanceof FirefoxDriver){
            channel_1 = price_sale_style_colour.substring(4, 7);
            channel_2 = price_sale_style_colour.substring(9, 10);
            channel_3 = price_sale_style_colour.substring(12, 13);
        }
        else {
            channel_1 = "";
            channel_2 = "";
            channel_3 = "";
        }
        if (Integer.parseInt(channel_1)>0  && channel_2.equals("0") && channel_3.equals("0"))
            System.out.println("г1) Цвет аукц цены на открытой странице касный!");
        else
            System.out.println("г1) Цвето аукц цены на открытой странице НЕ касный");

        // проверка стиля
        System.out.println("г2) Аукц цена жирная? - " + page_price_sale_style_line.equals("700"));

        // Размер на странице
        one = Integer.parseInt(page_price_sale_style_size.substring(0, 2));
        two = Integer.parseInt(price_sale_style_size.substring(0, 2));
        System.out.println("д2) Аукционная цена больше обычной:? - " + (one > two));
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

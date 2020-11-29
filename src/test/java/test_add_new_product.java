import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;


public class test_add_new_product {
    private WebDriver driver;
    private WebDriverWait wait;

    public String method_absolute_file_Path(String filePath) throws IOException {
        String absolute_file_Path = new File(filePath).getCanonicalPath();
        return absolute_file_Path;
    }


    @Before
    public void start(){
        driver = new ChromeDriver();
//        driver = new InternetExplorerDriver();

//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
//        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


//        FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe"));
//        WebDriver driver = new FirefoxDriver(bin, new FirefoxProfile());

        wait  = new WebDriverWait(driver, 10);
    }

    @Test
    public void test_add_new_product_1() throws InterruptedException, IOException {
        // путь к картинке - относительный!!!!
        String filePath = "src\\test\\resources\\duck_photo.jpg";

        //залогинимся в админку
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Thread.sleep(1000);

        // страница регистрации
        driver.navigate().to("http://localhost/litecart/admin/?category_id=0&app=catalog&doc=edit_product");

        //GENERAL

        //Status
        WebElement status_enabled = driver.findElement(By.cssSelector("div#tab-general tbody tr:nth-child(1) td label:nth-child(3) input[type=radio]"));
        status_enabled.click();

        //Name
        WebElement name = driver.findElement(By.cssSelector("div#tab-general table tbody tr:nth-child(2) td input[type=text]"));
        name.sendKeys("Duck");

        //code
        WebElement code = driver.findElement(By.cssSelector("div#tab-general table tbody tr:nth-child(3) td input[type=text]"));
        code.sendKeys("911");

        //Product Groups
        WebElement group_male = driver.findElement(By.cssSelector("div#tab-general table tbody > tr:nth-child(7) tr:nth-child(3) input"));
        group_male.click();

        //Quantity
        WebElement quantity = driver.findElement(By.cssSelector("div#tab-general table tbody > tr:nth-child(8) input[name=quantity]"));
        quantity.sendKeys("4");

        //Categories
        WebElement Categories_Rubber_Ducks = driver.findElement(By.cssSelector("div#tab-general tbody tr:nth-child(4) td div.input-wrapper tbody tr:nth-child(2) input[type=checkbox]"));
        Thread.sleep(1000);
        Categories_Rubber_Ducks.click();

        // Date Valid From
        WebElement date_from = driver.findElement(By.cssSelector("div#tab-general table tbody > tr:nth-child(10) input"));
        date_from.click();
        date_from.clear();
        date_from.sendKeys("01112020");

        // Date Valid To
        WebElement date_to = driver.findElement(By.cssSelector("div#tab-general table tbody > tr:nth-child(11) input"));
        date_to.click();
        date_to.clear();
        date_to.sendKeys("29112020");

        // Upload Images
        String absolute_file_Path = method_absolute_file_Path(filePath);
        WebElement images = driver.findElement(By.cssSelector("div#tab-general table tbody > tr:nth-child(9) input[type=file]"));
        images.sendKeys(absolute_file_Path);


        //INFORMATION

        // переход по ссылку
        Thread.sleep(2100);
        WebElement w_href_information_page2 = driver.findElement(By.cssSelector("div.tabs ul.index li:nth-child(2) a"));
        Thread.sleep(1000);
        w_href_information_page2.click();

//        Manufacturer
        WebElement manufacturer = driver.findElement(By.cssSelector("div.content div#tab-information tbody tr:nth-child(1) select"));
        manufacturer.click();
        Select select = new Select(manufacturer);
        select.selectByValue("1");

        //Keywords
        WebElement keywords = driver.findElement(By.cssSelector("div.content div#tab-information tbody tr:nth-child(3) input"));
        keywords.sendKeys("duck");

        //Short Description
        WebElement short_description = driver.findElement(By.cssSelector("div.content div#tab-information tbody tr:nth-child(4) input"));
        short_description.sendKeys("duck");

        //Description
        WebElement description = driver.findElement(By.cssSelector("div.content div#tab-information tbody tr:nth-child(5) div.trumbowyg-editor"));
        description.sendKeys("Temp duck");

        //Head Title
        WebElement head_title = driver.findElement(By.cssSelector("div.content div#tab-information tbody tr:nth-child(6) input"));
        head_title.sendKeys("Black duck");

        //Meta Description
        WebElement meta_description = driver.findElement(By.cssSelector("div.content div#tab-information tbody tr:nth-child(7) input"));
        meta_description.sendKeys("Meta Black duck");


        //PRICES
        Thread.sleep(2100);
        WebElement w_href_information_page3 = driver.findElement(By.cssSelector("div.tabs ul.index li:nth-child(4) a"));
        Thread.sleep(1000);
        w_href_information_page3.click();

        //Purchase Price
        WebElement purchase_price_num = driver.findElement(By.cssSelector("div#tab-prices table:nth-child(2) input"));
        purchase_price_num.clear();
        purchase_price_num.sendKeys("150");

        WebElement purchase_price_select = driver.findElement(By.cssSelector("div#tab-prices table:nth-child(2) select"));
        Select select_2 = new Select(purchase_price_select);
        select_2.selectByValue("USD");

        //Price
        WebElement Price_11 = driver.findElement(By.cssSelector("div#tab-prices table:nth-child(4) tr:nth-child(2) td:nth-child(1) input"));
        Price_11.sendKeys("150");
//        WebElement Price12 = driver.findElement(By.cssSelector("div#tab-prices table:nth-child(4) tr:nth-child(2) td:nth-child(2) input"));

        WebElement Price21 = driver.findElement(By.cssSelector("div#tab-prices table:nth-child(4) tr:nth-child(3) td:nth-child(1) input"));
        Price21.sendKeys("150");

//        WebElement Price22 = driver.findElement(By.cssSelector("div#tab-prices table:nth-child(4) tr:nth-child(3) td:nth-child(2) input"));

        //Price
        WebElement Price = driver.findElement(By.cssSelector("div.content div#tab-information tbody tr:nth-child(7) input"));


        //сохранить изменения
        WebElement botton_save = driver.findElement(By.cssSelector("button[name=save]"));
        botton_save.click();
        Thread.sleep(3000);

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

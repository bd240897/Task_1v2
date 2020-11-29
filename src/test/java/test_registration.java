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
import java.util.concurrent.TimeUnit;


public class test_registration {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
//        driver = new ChromeDriver();
//        driver = new InternetExplorerDriver();

        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


//        FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe"));
//        WebDriver driver = new FirefoxDriver(bin, new FirefoxProfile());

        wait  = new WebDriverWait(driver, 10);
    }

    @Test
    public void test_registration_1() throws InterruptedException {
        // случайный эмейл и постоянный пароль
        int random_num = (int) (Math.random() * 100);
        String my_login = Integer.toString(random_num) + "@mail.ru";
        String my_password = "1";

        // страница регистрации
        driver.navigate().to("http://localhost/litecart/en/create_account");


        // найдем все элементы регистарции
        WebElement tax_id = driver.findElement(By.cssSelector("form table tbody tr:nth-child(1) td:nth-child(1) input[name=tax_id]"));
        WebElement firstname = driver.findElement(By.cssSelector("form table tbody tr td input[name=firstname]"));
        WebElement lastname = driver.findElement(By.cssSelector("form table tbody tr td input[name=lastname]"));
        WebElement address1 = driver.findElement(By.cssSelector("form table tbody tr td input[name=address1]"));
        WebElement postcode = driver.findElement(By.cssSelector("form table tbody tr td input[name=postcode]"));
        WebElement city = driver.findElement(By.cssSelector("form table tbody tr td input[name=city]"));
        WebElement email = driver.findElement(By.cssSelector("form table tbody tr td input[name=email]"));
        WebElement phone = driver.findElement(By.cssSelector("form table tbody tr td input[name=phone]"));
        WebElement password = driver.findElement(By.cssSelector("form table tbody tr td input[name=password]"));
        WebElement confirmed_password = driver.findElement(By.cssSelector("form table tbody tr td input[name=confirmed_password]"));

        // заполним поля
        new Actions(driver)
                .moveToElement(firstname).click()
                .sendKeys("Dima")
                .moveToElement(lastname).click()
                .sendKeys("Borisov")
                .moveToElement(address1).click()
                .sendKeys("Moskva")
                .moveToElement(postcode).click()
                .sendKeys("24800")
                .moveToElement(city).click()
                .sendKeys("Kaluga")
                .moveToElement(email).click()
                .sendKeys(my_login)
                .moveToElement(phone).click()
                .sendKeys("+78005553535")
                .perform();
        password.sendKeys(my_password);
        confirmed_password.sendKeys(my_password);

        // выбор страны
        WebElement US_locator = driver.findElement(By.cssSelector("form table tbody tr td span.select2-selection.select2-selection--single"));
        new Actions(driver).moveToElement(US_locator).click().sendKeys("United States" + Keys.ENTER).perform();
        // первое нажате кнопки регистрации - без выбора штата
        WebElement button = driver.findElement(By.cssSelector("form table tbody tr td button[name=create_account]"));
        button.click();

        // еще раз введем пароль
        password = driver.findElement(By.cssSelector("form table tbody tr td input[name=password]"));
        confirmed_password = driver.findElement(By.cssSelector("form table tbody tr td input[name=confirmed_password]"));
        password.clear();
        confirmed_password.clear();
        Thread.sleep(1000);
        password.sendKeys(my_password);
        confirmed_password.sendKeys(my_password);

        // второе нажатие кнопки регистрации
        button = driver.findElement(By.cssSelector("form table tbody tr td button[name=create_account]"));
        button.click();
        Thread.sleep(1000);

        // выход из профился
        driver.navigate().to("http://localhost/litecart/en/logout");
        Thread.sleep(1000);

        // вход в профиль
        driver.findElement(By.cssSelector("input[name=email]")).sendKeys(my_login);
        driver.findElement(By.cssSelector("input[name=password]")).sendKeys(my_password);
        driver.findElement(By.cssSelector("button[name=login]")).click();
        Thread.sleep(1000);
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

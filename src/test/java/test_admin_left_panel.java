import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;

import java.io.File;
import java.sql.Time;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class test_admin_left_panel {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);
        wait  = new WebDriverWait(driver, 10);
    }

    @Test
    public void mySecondTest() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // узнаем размер списка внешнего
        List<WebElement> element_list_1  = driver.findElements(By.cssSelector("ul#box-apps-menu > li#app-"));
        int size_list_1 = element_list_1.size();

        // проходимся по списку внешнего
        for (int i = 0; i < size_list_1 ; i++) {
            element_list_1  = driver.findElements(By.cssSelector("ul#box-apps-menu > li#app-"));
            element_list_1.get(i).click();

            // проверка наличия заголовка для внутренних списков
            int count = driver.findElements(By.cssSelector("h1")).size();
            if (count < 1)
                System.out.println("Cтраница НЕ имеет заголовок");
            else
                System.out.println("Cтраница имеет " + count + " заголовок");

            // узнаем размер внутренного списка
            List<WebElement> element_list_2  = driver.findElements(By.cssSelector("ul#box-apps-menu > li#app- [class=docs] span"));
            int size_list_2 = element_list_2.size();

            // проходимся по внутренним спискам
            for (int j = 0; j < size_list_2 ; j++) {
                element_list_2  = driver.findElements(By.cssSelector("ul#box-apps-menu > li#app- [class=docs] span"));
                element_list_2.get(j).click();

                // проверка наличия заголовка для внутренних списков
                int count_2 = driver.findElements(By.cssSelector("h1")).size();
                if (count_2 < 1)
                    System.out.println("Cтраница НЕ имеет заголовок");
                else
                    System.out.println("Cтраница имеет " + count_2 + " заголовок");

            }
        }

    }





    @After
    public void stop() {
        driver.quit();
        driver = null;
    }


}

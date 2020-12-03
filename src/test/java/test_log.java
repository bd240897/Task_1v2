import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class test_log {
    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait  = new WebDriverWait(driver, 30);
    }

    @Test
    public void test_log_1() {
        // логин в админку
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // переход на страницу товаров
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        // получаем список товаров
        driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> duck_list = driver.findElements(By.cssSelector("table.dataTable tbody tr td:nth-child(3) a:nth-child(2)"));

        // пробежимся по товварам и откроем каждый
        for (int i = 1; i < duck_list.size(); i++) {
            driver.navigate().to("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            duck_list = driver.findElements(By.cssSelector("table.dataTable tbody tr td:nth-child(3) a:nth-child(2)"));
            duck_list.get(i).click();

            // получим список логов
            List<LogEntry> logs= driver.manage().logs().get("browser").getAll();
            // если список не пустой выведем логи
            if ( logs.size() > 0) {
                for (LogEntry l : logs) {
                    System.out.println(l);
                }
            }
        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

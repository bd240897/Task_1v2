import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

import org.junit.After;



public class test_new_country {
    private WebDriver driver;
    private WebDriverWait wait;


    // функция находит id нового окна
    public ExpectedCondition<String> anyWindowOtherThan(final Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles=driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size()>0 ? handles.iterator().next():null;
            }
        };
    }


    @Before
    public void start(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PAGE_LOAD_STRATEGY, "eager");

        driver = new ChromeDriver();
//        driver = new InternetExplorerDriver();

//        FirefoxOptions options = new FirefoxOptions();
//        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
//        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


//        FirefoxBinary bin = new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe"));
//        WebDriver driver = new FirefoxDriver(bin, new FirefoxProfile());

        wait  = new WebDriverWait(driver, 30);
    }

    @Test
    public void test_new_country_1() {
        // логин в админку
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        // переход на страницу стран
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        // переход на страницу создания страны
        WebElement botton_new_country = driver.findElement(By.cssSelector("a.button[href]"));
        botton_new_country.click();

        // получаем список ссылок
        List<WebElement> list_link = driver.findElements(By.cssSelector("form tbody a:not([id=address-format-hint])"));

        for (int i = 0; i < list_link.size(); i++) {
            //заново получим список ссылок - т.к. они обновились
            list_link = driver.findElements(By.cssSelector("form tbody a:not([id=address-format-hint])"));

            // главное окно
            String mainWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();

            // открываем новое окно
            WebElement w_first_link = list_link.get(i);
            w_first_link.click();

            // найдем id нового окна
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));

            // переключимся на него
            driver.switchTo().window(newWindow);

            // закроем новое окно
            driver.close();

            // переключимся на исходное окно
            driver.switchTo().window(mainWindow);

        }
    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}

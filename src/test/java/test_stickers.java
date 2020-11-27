import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;


public class test_stickers {
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
        driver.navigate().to("http://localhost/litecart/en/");

        // получим список товаров
        List<WebElement> element_list_2 = driver.findElements(By.cssSelector("ul.listing-wrapper.products li"));
        int size_list_1 = element_list_2.size();

        // пробежимся по каждому элементу и относительно него поищем наличае стикера
        // если стикера нет - выведет ошибку
        for (int i = 0; i < size_list_1 ; i++) {
            WebElement element = element_list_2.get(i);
            int count = element.findElements(By.cssSelector("div.sticker")).size();

            // проверка количества стикеров
            if (count > 1) {
                System.out.println("У" + i +  "-го элемента больше одного стикера");
            }
            else if (count < 1) {
                System.out.println("У" + i +  "-го элемента меньше одного стикера");
            }
            else{
                System.out.println("У" + i +  "-го элемента 1 cтикер");
            }
        }

    }


    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}


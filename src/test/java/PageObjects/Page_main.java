package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Page_main extends page
{
    // конструкторв
    public Page_main(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    // открыть главную страницы
    public void open_main_page() { driver.navigate().to("https://litecart.stqa.ru/en/"); }

    // счетчик у левом углу
    public WebElement numb_item() {
        return driver.findElement(By.cssSelector("span.quantity"));
    }

    // открыть первы продукт
    public void open_first_product() {
        driver.findElement(By.cssSelector("div#box-most-popular ul.listing-wrapper.products li:nth-child(1)")).click();
    }

    // дождемся обновления счетчика у углу
    public void waiting_for_update_counter(WebElement counter, int i){
        wait.until(stalenessOf(counter));
        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("span.quantity"), "innerText", String.valueOf(i + 1)));

    }




}

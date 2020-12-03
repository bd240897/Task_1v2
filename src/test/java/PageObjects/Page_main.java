package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class Page_main extends page
{
    public Page_main(WebDriver driver) {
        super(driver);
    }

    public void open_main_page() { driver.navigate().to("https://litecart.stqa.ru/en/"); }

    public WebElement numb_item() { return driver.findElement(By.cssSelector("span.quantity")); }

    public void open_first_product() { driver.findElement(By.cssSelector("div#box-most-popular ul.listing-wrapper.products li:nth-child(1)")).click(); }

    public void clik_on_first_product()
    {
        WebElement w_title = driver.findElement(By.cssSelector("h1"));
        String title = w_title.getAttribute("outerText");

        // если он желтая утка
        WebElement add_to_cart;
        if (title.equals("Yellow Duck")) {
            WebElement yellow_duck = driver.findElement(By.cssSelector("select"));
            Select select = new Select(yellow_duck);
            select.selectByValue("Small");
            // кликнем на кропку добавить
            add_to_cart = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
            add_to_cart.click();
        } else {
            // кликнем на кропку добавить
            add_to_cart = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
            add_to_cart.click();
        }
    }

    public void waiting_for_update_counter(WebElement counter, int i){
        wait.until(stalenessOf(counter));
        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("span.quantity"), "innerText", String.valueOf(i + 1)));

    }




}

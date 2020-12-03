package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class Page_product extends page {
    public Page_product(WebDriver driver) {
        super(driver);
    }

    public void clik_on_first_product() {
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
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name=add_cart_product]")));
            add_to_cart = driver.findElement(By.cssSelector("button[name=add_cart_product]"));
            add_to_cart.click();
        }
    }
}
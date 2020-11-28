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
import sun.security.util.ArrayUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class test_country {
    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start(){
        FirefoxOptions options = new FirefoxOptions();
        options.setBinary(new FirefoxBinary(new File("c:\\Program Files\\Firefox Nightly\\firefox.exe")));
        driver = new FirefoxDriver(options);
        wait  = new WebDriverWait(driver, 10);

//        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
    }

    @Test // задание 1 - а
    public void country_test_1_for_alphabet() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        // получим список tr блоко где находятсья тсраны
        List<WebElement> element_list_1 = driver.findElements(By.cssSelector("tr.row :nth-child(5) a"));
        int size_list_1 = element_list_1.size();

        // список для хранения названия стран
        ArrayList<String> name_country = new ArrayList<String>();

//      пробежимся по каждому блоку и дсотаним имена страны
        for (int i = 0; i < size_list_1 ; i++) {
            WebElement element = element_list_1.get(i);
//            System.out.print(element.getAttribute("innerText") + ' ');
            name_country.add(element.getAttribute("innerText"));
        }

//      создадим новый список и отсортируем в нем элементы стран
        List<String> name_country_ordered = new ArrayList<String>(name_country);
        Collections.sort(name_country_ordered);
//      сравним отсортированный список стран с исходным
        boolean inOrder = name_country_ordered.equals(name_country);
        System.out.println();
        System.out.println("Исходный список стран отсортирован по алфавиту? - " + inOrder);

    }
    @Test // задание 1 - б
    public void country_test_2_multiple_zones() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=countries&doc=countries");

        // получим tr блоки где лежит количество зон
        List<WebElement> element_list_2 = driver.findElements(By.cssSelector("tr.row :nth-child(6)"));
        int size_list_1 = element_list_2.size();

//      cписок для хранения номеров стран с количеством зон >0
        List<Integer> number_country = new ArrayList<Integer>();

//      проверим кол-во зон у каждой страны и оставим только те страны где количеством зон >0
        for (int i = 0; i < size_list_1 ; i++) {
            WebElement element = element_list_2.get(i);

            int convert_element = Integer.parseInt(element.getAttribute("innerText"));
            if (convert_element != 0)
            {
            number_country.add(i);
            }
        }


//      список для хранения tr блоко где находятся ссыдки
        List<WebElement> element_list_3 = driver.findElements(By.cssSelector("tr.row :nth-child(5) a"));
//      список для хранения самих ссылок
        List<String> ref = new ArrayList<String>();

//      вытащим только те ссылки в странах которых зон > 0
        for (int i = 0; i < size_list_1 ; i++) {
            WebElement element = element_list_3.get(i);
            if (number_country.contains(i)) {
                //System.out.print(element.getAttribute("href") + ' ');
                ref.add(element.getAttribute("href"));
            }
        }

//      зайдем в каждую ссылку
        for (int i = 0; i < ref.size() ; i++) {
            driver.navigate().to(ref.get(i));

//          получим список tr блоко где находятсья тсраны
            List<WebElement> country_inside = driver.findElements(By.cssSelector("table.dataTable tr td:nth-child(3)"));
//          список для хранения названия стран
            ArrayList<String> name_country_2 = new ArrayList<String>();

//          вытащим из каждой страны ее название
            for (int j = 0; j < country_inside.size()-1; j++) {
                WebElement element = country_inside.get(j);
                name_country_2.add(element.getAttribute("innerText"));
            }

//          создадим новый список и отсортируем в нем элементы стран
            List<String> name_country_ordered_2 = new ArrayList<String>(name_country_2);
            Collections.sort(name_country_ordered_2);

//          сравним отсортированный список стран с исходным
            //System.out.println();
            //System.out.println(name_country_2);
            //System.out.println(name_country_ordered_2);

            boolean inOrder = name_country_ordered_2.equals(name_country_2);

            int number_of_current_country = number_country.get(i) + 1;
            System.out.println("Для страны под номером №" + number_of_current_country + " названия зон отсортированы по алфавиту? - " + inOrder);
        }

    }

    @Test // задание 2
    public void country_test_3_edit_geo_zone() {
        driver.navigate().to("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.navigate().to("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

//      получим td блоки где лежат ссылка
        List<WebElement> td_ref = driver.findElements(By.cssSelector("tbody tr.row td:nth-child(3) a"));
//      список для хранения самих ссылок
        List<String> ref = new ArrayList<String>();

//      получим список ссылок
        for (int j = 0; j < td_ref.size(); j++) {
            WebElement element = td_ref.get(j);
            ref.add(element.getAttribute("href"));
        }
        //System.out.println(ref);

//      перейдем по ссылкам
        for (int i = 0; i < ref.size() ; i++) {
            driver.navigate().to(ref.get(i));

//          10 блоков options
            List<WebElement> options = driver.findElements(By.cssSelector("form table tbody tr td:nth-child(3) select option[selected]"));
//          лист для хранения имен стран
            List<String> name_country_inside = new ArrayList<String>();

            for (int j = 0; j < options.size(); j++) {
                WebElement element = options.get(j);
                name_country_inside.add(element.getAttribute("innerText"));
            }

//            System.out.println(name_country_inside);
//          создадим новый список и отсортируем в нем элементы стран
            List<String> name_country_inside_ordered = new ArrayList<String>(name_country_inside);
            Collections.sort(name_country_inside_ordered);

//          сравним отсортированный список стран с исходным
//            System.out.println();
//            System.out.println(name_country_inside);
//            System.out.println(name_country_inside_ordered);

            boolean inOrder = name_country_inside_ordered.equals(name_country_inside);

            System.out.println();
            int number_of_current_country = i + 1;
            System.out.println("Для страны под номером №" + number_of_current_country + " названия зон отсортированы по алфавиту? - " + inOrder);
        }
}

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }

}


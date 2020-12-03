package PageObjects.tests;

import PageObjects.app.Application;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;

public class TestBase {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public Application app;

    @Before
    public void start() {
        app = new Application();
    }

    @After
    public void stop() {
        app.quit();
    }
}

package PageObjects.tests;

import org.junit.Test;

public class test_basket_main  extends TestBase{
    @Test
    public void test_test_basket_1()
    {
        app.add_to_basket(3);
        app.remove_from_basket();
        app.go_to_main_page();
    }
}

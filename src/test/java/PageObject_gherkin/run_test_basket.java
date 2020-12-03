package PageObject_gherkin;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = {"src/test/java"}, glue = {"PageObject_gherkin"})
public class run_test_basket {
}

package code;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
    features = "src/test/resources/featureFiles/filter_tasks.feature",
    glue = {"code", "code.stepdefinitions", "code.hooks"},
    plugin = {"pretty", "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"},
    monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests {
    // No browser setup or TestNG lifecycle methods here!
}

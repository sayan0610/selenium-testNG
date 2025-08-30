package code.hooks;

import io.cucumber.java.AfterStep;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class AllureStepHooks {
    @AfterStep
    public void afterStep(io.cucumber.java.Scenario scenario) {
        WebDriver driver = BrowserHooks.driver.get();
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Allure.addAttachment("Screenshot after step", "image/png", new java.io.ByteArrayInputStream(screenshot), ".png");
        }
        Allure.addAttachment("Step name", "text/plain", scenario.getName());
    }
}

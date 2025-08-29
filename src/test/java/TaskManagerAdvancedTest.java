
package test.java;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.Factory;

public class TaskManagerAdvancedTest {
    private final String browser;
    private final boolean headless;

    public TaskManagerAdvancedTest(String browser, boolean headless) {
        this.browser = browser;
        this.headless = headless;
    }

    @org.testng.annotations.BeforeClass
    public void setUp() {
        BrowserManager.initDriver(browser, headless);
    }

    @org.testng.annotations.AfterClass
    public void tearDown() {
        BrowserManager.quitDriver();
    }

    @org.testng.annotations.Test
    public void shouldAddNewTaskAndDisplayInList() {
        WebDriver driver = BrowserManager.getDriver();
        driver.get("http://localhost:3000");
        WebElement input = driver.findElement(By.id("task-input"));
        WebElement button = driver.findElement(By.cssSelector("#task-form button[type='submit']"));
        String newTask = "Write Selenium test";
        input.sendKeys(newTask);
        button.click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        WebElement taskList = driver.findElement(By.id("task-list"));
        assertThat(taskList.getText()).contains(newTask);
    }

    @Factory
    public static Object[] browserFactory() {
        String os = System.getProperty("os.name").toLowerCase();
        java.util.List<Object> tests = new java.util.ArrayList<>();
        tests.add(new TaskManagerAdvancedTest("chrome", true));
        tests.add(new TaskManagerAdvancedTest("firefox", true));
        if (!os.contains("mac")) {
            tests.add(new TaskManagerAdvancedTest("edge", true));
        }
        tests.add(new TaskManagerAdvancedTest("safari", false));
        return tests.toArray();
    }
}

package code;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;
import code.hooks.BrowserHooks;
import java.util.List;

public class StepDefinitions {
    @io.cucumber.java.en.Then("the page title should be \"Task Manager\"")
    public void pageTitleShouldBeTaskManager() {
        WebDriver driver = BrowserHooks.driver.get();
        String title = driver.getTitle();
        assertThat(title).isEqualTo("Task Manager");
    }

    @io.cucumber.java.en.And("the header should be visible")
    public void headerShouldBeVisible() {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement header = driver.findElement(By.tagName("h1"));
        assertThat(header.isDisplayed()).isTrue();
    }

    @Given("the user is on the Task Manager homepage")
    public void userOnHomePage() {
        WebDriver driver = BrowserHooks.driver.get();
        driver.get("http://localhost:3000");
    }

    @When("the user adds a new task {string}")
    public void userAddsTask(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement input = driver.findElement(By.id("task-input"));
        WebElement button = driver.findElement(By.cssSelector("#task-form button[type='submit']"));
        input.sendKeys(task);
        button.click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    @Then("the task list should contain {string}")
    public void taskListShouldContain(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(By.id("task-list"));
        assertThat(taskList.getText()).contains(task);
    }

    @When("the user deletes the task {string}")
    public void userDeletesTask(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(By.id("task-list"));
        for (WebElement item : taskList.findElements(By.tagName("li"))) {
            if (item.getText().contains(task)) {
                // Find the correct "Delete" button (usually the last button in the item)
                List<WebElement> buttons = item.findElements(By.tagName("button"));
                WebElement deleteBtn = buttons.get(buttons.size() - 1);
                deleteBtn.click();
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                break;
            }
        }
    }

    @Then("the task list should not contain {string}")
    public void taskListShouldNotContain(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(By.id("task-list"));
        assertThat(taskList.getText()).doesNotContain(task);
        // No need to quit here, handled by hooks
    }
}

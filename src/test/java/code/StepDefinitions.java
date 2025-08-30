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
    @When("the user edits the task {string} to {string}")
    public void userEditsTask(String oldName, String newName) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(By.id("task-list"));
        System.out.println("Task List Items:" + taskList.findElements(By.tagName("li")).size());
        for (WebElement item : taskList.findElements(By.tagName("li"))) {
            if (item.getText().contains(oldName)) {
                // Find the Edit button (assume third button)
                WebElement editButton = item.findElement(By.xpath(".//span[contains(text(), '" + oldName + "')]/following-sibling::button[contains(text(), 'Edit')]"));
                editButton.click();
                // Find the input field and set new value
                WebElement input = item.findElement(By.xpath(".//span[contains(text(), '" + oldName + "')]/following-sibling::span//input[@type='text']"));
                input.clear();
                input.sendKeys(newName);
                // Find the Save button and click
                WebElement saveButton = item.findElement(By.xpath("//button[contains(text(), 'Save')]"));
                saveButton.click();
            }
            try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        }
    }

    @Given("local data is cleared before launch")
    public void clearLocalDataBeforeLaunch() {
        WebDriver driver = BrowserHooks.driver.get();
        driver.get("http://localhost:3000");
        // Clear localStorage and sessionStorage
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("window.localStorage.clear(); window.sessionStorage.clear();");
        driver.navigate().refresh();
    }
    @When("the user marks the task {string} as completed")
    public void the_user_marks_the_task_as_completed(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(By.id("task-list"));
        for (WebElement item : taskList.findElements(By.tagName("li"))) {
            if (item.getText().contains(task)) {
                // Find the complete button (assume first button is complete)
                List<WebElement> buttons = item.findElements(By.tagName("button"));
                if (!buttons.isEmpty()) {
                    WebElement completeBtn = buttons.get(0);
                    completeBtn.click();
                    try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                }
                break;
            }
        }
    }

    @Then("the task {string} should be shown as completed")
    public void the_task_should_be_shown_as_completed(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(By.id("task-list"));
        boolean found = false;
        for (WebElement item : taskList.findElements(By.tagName("li"))) {
            if (item.getText().contains(task)) {
                // Check for completed class or style
                String classAttr = item.getAttribute("class");
                if (classAttr != null && classAttr.contains("completed")) {
                    found = true;
                    break;
                }
                // Alternatively, check for strikethrough style
                String style = item.getAttribute("style");
                if (style != null && style.contains("line-through")) {
                    found = true;
                    break;
                }
                // Try to get style from child elements if not found on <li>
                List<WebElement> children = item.findElements(By.xpath(".//*"));
                for (WebElement child : children) {
                    String childStyle = child.getAttribute("style");
                    if (childStyle != null && childStyle.contains("line-through")) {
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
        }
        assertThat(found).isTrue();
    }
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

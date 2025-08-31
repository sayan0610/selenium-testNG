package code;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;
import code.hooks.BrowserHooks;

public class StepDefinitions {
    @When("the user adds a new task {string} with details {string}")
    public void userAddsTaskWithDetails(String task, String details) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement input = driver.findElement(By.id("task-input"));
        WebElement detailsInput = driver.findElement(By.id("task-detail-input"));
        WebElement button = driver.findElement(By.cssSelector("#task-form button[type='submit']"));
        input.clear();
        input.sendKeys(task);
        detailsInput.clear();
        detailsInput.sendKeys(details);
        button.click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    @When("the user views the details of the task {string}")
    public void userViewsTaskDetails(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement titleSpan = driver.findElement(By.xpath(".//span[contains(text(), '" + task + "')]"));
        titleSpan.click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    @Then("the task details for {string} should be displayed with {string}")
    public void taskDetailsShouldBeDisplayed(String taskName, String details) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement detailDiv = driver.findElement(By.xpath(".//span[contains(text(), '" + taskName + "')]//parent::td//parent::tr//following-sibling::tr//td[@data-test='detail-row']"));
        Thread.sleep(10000);
        assertThat(detailDiv.isDisplayed()).isTrue();
        assertThat(detailDiv.getText()).contains(details);
    }
    @When("the user has completed the task {string}")
    public void userHasCompletedTask(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement input = driver.findElement(By.id("task-input"));
        WebElement button = driver.findElement(By.cssSelector("#task-form button[type='submit']"));
        input.clear();
        input.sendKeys(task);
        button.click();
        Thread.sleep(500);
        // Mark as completed
        WebElement completeButton = driver.findElement(By.xpath(".//span[contains(text(), '" + task + "')]//parent::td//following-sibling::td//button[@data-test-id='status']"));
        completeButton.click();
        Thread.sleep(500);
    }

    @When("the user has an incomplete task {string}")
    public void userHasIncompleteTask(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement input = driver.findElement(By.id("task-input"));
        WebElement button = driver.findElement(By.cssSelector("#task-form button[type='submit']"));
        input.clear();
        input.sendKeys(task);
        button.click();
        Thread.sleep(500);
        // Do not mark as completed
    }

    @When("the user filters to show only completed tasks")
    public void userFiltersToShowOnlyCompletedTasks() throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement filterDropdown = driver.findElement(By.id("filter-dropdown"));
        filterDropdown.click();
        filterDropdown.findElement(By.xpath(".//option[@value='completed']")).click();
        Thread.sleep(1000);
    }
    @When("the user adds tasks {string}, {string}, and {string}")
    public void userAddsMultipleTasks(String t1, String t2, String t3) {
        userAddsTask(t1);
        userAddsTask(t2);
        userAddsTask(t3);
    }

    @When("the user selects and deletes tasks {string} and {string}")
    public void userBulkDeletesTasks(String t1, String t2) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(By.id("task-list"));
        // Select checkboxes for t1 and t2
        for (WebElement item : taskList.findElements(By.tagName("li"))) {
            if (item.getText().contains(t1) || item.getText().contains(t2)) {
                WebElement checkbox = item.findElement(By.cssSelector("input[type='checkbox'].bulk-checkbox"));
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }
        // Click Bulk Delete button
        WebElement bulkDeleteBtn = driver.findElement(By.xpath("//button[contains(text(), 'Bulk Delete')]"));
        bulkDeleteBtn.click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }
    @When("the user edits the task {string} to {string}")
    public void userEditsTask(String oldName, String newName) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        // Find the Edit button (assume third button)
        Thread.sleep(10000);
        WebElement editButton = driver.findElement(By.xpath(".//span[contains(text(), '" + oldName + "')]//parent::td//following-sibling::td//button[@data-test-id=\"edit-task\"]"));
        editButton.click();
        // Find the input field and set new value        
        WebElement input = driver.findElement(By.xpath(".//span[contains(text(), '" + oldName + "')]/following-sibling::span//input[@type='text']"));
        input.clear();
        input.sendKeys(newName);
        // Find the Save button and click
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(), 'Save')]"));
        saveButton.click();
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
    public void the_user_marks_the_task_as_completed(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement completeButton = driver.findElement(By.xpath(".//span[contains(text(), '" + task + "')]//parent::td//following-sibling::td//button[@data-test-id=\"status\"]"));
        completeButton.click();
    }

    @Then("the task {string} should be shown as completed")
    public void the_task_should_be_shown_as_completed(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskTable = driver.findElement(By.id("task-list"));
        boolean found = false;
        for (WebElement row : taskTable.findElements(By.tagName("tr"))) {
            if (row.getText().contains(task)) {
            // Check for completed status in the status column
            WebElement statusBtn = row.findElement(By.xpath(".//button[@data-test-id='status']"));
            String statusText = statusBtn.getText().toLowerCase();
            if (statusText.contains("completed") || statusBtn.getAttribute("class").contains("completed")) {
                found = true;
                break;
            }
            // Optionally, check for strikethrough style in the task name cell
            WebElement nameCell = row.findElement(By.xpath(".//span[contains(text(), '" + task + "')]"));
            String style = nameCell.getAttribute("style");
            if (style != null && style.contains("line-through")) {
                found = true;
                break;
            }
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

    @When("the user deletes the task {string} using the delete icon")
    public void userDeletesTaskUsingIcon(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        // Find the delete button by data-test-id
        WebElement deleteButton = driver.findElement(By.xpath(".//span[contains(text(), '" + task + "')]//parent::td//following-sibling::td//button[@data-test-id=\"delete-task\"]"));
        deleteButton.click();
    }

    @Then("the task list should not contain {string}")
    public void taskListShouldNotContain(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(By.id("task-list"));
        assertThat(taskList.getText()).doesNotContain(task);
        // No need to quit here, handled by hooks
    }
}

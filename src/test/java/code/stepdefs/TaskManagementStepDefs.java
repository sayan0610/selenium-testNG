package code.stepdefs;

import io.cucumber.java.en.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;
import code.hooks.BrowserHooks;
import code.locators.Locators;

public class TaskManagementStepDefs {
    @Given("the user is on the Task Manager homepage")
    public void userOnHomePage() {
        WebDriver driver = BrowserHooks.driver.get();
        driver.get("http://localhost:3000");
    }

    @When("the user adds a new task {string}")
    public void userAddsTask(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement input = driver.findElement(Locators.TASK_INPUT);
        WebElement button = driver.findElement(Locators.ADD_TASK_BUTTON);
        input.sendKeys(task);
        button.click();
        Thread.sleep(500);
    }
    
        @Given("the user adds tasks {string}, {string}, and {string}")
        public void the_user_adds_tasks_and(String task1, String task2, String task3) throws InterruptedException {
            WebDriver driver = BrowserHooks.driver.get();
            WebElement input = driver.findElement(Locators.TASK_INPUT);
            WebElement button = driver.findElement(Locators.ADD_TASK_BUTTON);
            String[] tasks = {task1, task2, task3};
            for (String task : tasks) {
                input.clear();
                input.sendKeys(task);
                button.click();
                Thread.sleep(500);
            }
        }
    
        @When("the user selects tasks {string} and {string}")
        public void the_user_selects_and_deletes_tasks_and(String task1, String task2) throws InterruptedException {
            WebDriver driver = BrowserHooks.driver.get();
            String[] tasks = {task1, task2};
            // Select checkboxes for the tasks
            for (String task : tasks) {
                WebElement checkbox = driver.findElement(Locators.getTaskCheckbox(task));
                if (!checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }

    @When("the user deletes the selected tasks")
    public void userDeletesSelectedTasks() throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement bulkDeleteButton = driver.findElement(Locators.BULK_DELETE_BUTTON);
        bulkDeleteButton.click();
        Thread.sleep(500);
    }

    @When("the user deletes the task {string} using the delete icon")
    public void userDeletesTaskUsingIcon(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement deleteButton = driver.findElement(Locators.getDeleteButton(task));
        deleteButton.click();
        Thread.sleep(500);
    }

    @Then("the task list should contain {string}")
    public void taskListShouldContain(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(Locators.TASK_LIST);
        assertThat(taskList.getText()).contains(task);
    }

    @Then("the task list should not contain {string}")
    public void taskListShouldNotContain(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(Locators.TASK_LIST);
        assertThat(taskList.getText()).doesNotContain(task);
    }

    @When("the user selects all tasks")
    public void the_user_selects_all_tasks() throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement selectAllCheckbox = driver.findElement(Locators.SELECT_ALL_CHECKBOX);
        if (!selectAllCheckbox.isSelected()) {
            selectAllCheckbox.click();
        }
        Thread.sleep(500);
    }

    @When("the user changes the status of selected tasks to {string}")
    public void the_user_changes_the_status_of_selected_tasks_to(String status) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement statusBtn = driver.findElement(Locators.getBulkStatusButton(status));
        statusBtn.click();
        Thread.sleep(500);
    }

    @Then("the status of {string} should be {string}")
    public void the_status_of_should_be(String task, String expectedStatus) {
        WebDriver driver = BrowserHooks.driver.get();
        org.openqa.selenium.WebElement row = driver.findElement(org.openqa.selenium.By.xpath("//tr[td/span[text()='" + task + "']]"));
        org.openqa.selenium.WebElement statusBadge = row.findElement(org.openqa.selenium.By.cssSelector(".status-badge"));
        assertThat(statusBadge.getText()).isEqualToIgnoringCase(expectedStatus);
    }
}

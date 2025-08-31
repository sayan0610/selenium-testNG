package code.stepdefs;

import io.cucumber.java.en.*;
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
    public void taskListShouldNotContain(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskList = driver.findElement(Locators.TASK_LIST);
        assertThat(taskList.getText()).doesNotContain(task);
    }
}

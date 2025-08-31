package code.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;
import code.hooks.BrowserHooks;
import code.locators.Locators;

public class ViewTaskDetailsStepDefs {
    @When("the user adds a new task {string} with details {string}")
    public void userAddsTaskWithDetails(String task, String details) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement input = driver.findElement(Locators.TASK_INPUT);
        WebElement detailsInput = driver.findElement(Locators.TASK_DETAIL_INPUT);
        WebElement button = driver.findElement(Locators.ADD_TASK_BUTTON);
        input.clear();
        input.sendKeys(task);
        detailsInput.clear();
        detailsInput.sendKeys(details);
        button.click();
        Thread.sleep(500);
    }

    @When("the user views the details of the task {string}")
    public void userViewsTaskDetails(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement titleSpan = driver.findElement(Locators.getTaskTitleSpan(task));
        titleSpan.click();
        Thread.sleep(500);
    }

    @Then("the task details for {string} should be displayed")
    public void taskDetailsShouldBeDisplayed(String taskName) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement detailDiv = driver.findElement(Locators.getTaskDetailRow(taskName));
        Thread.sleep(1000);
        assertThat(detailDiv.isDisplayed()).isTrue();
        assertThat(detailDiv.getText()).contains("Task Details");
    }
}

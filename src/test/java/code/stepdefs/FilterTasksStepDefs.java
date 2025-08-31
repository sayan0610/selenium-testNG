package code.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;
import code.hooks.BrowserHooks;
import code.locators.Locators;

public class FilterTasksStepDefs {
    @When("the user has completed the task {string}")
    public void userHasCompletedTask(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement input = driver.findElement(Locators.TASK_INPUT);
        WebElement button = driver.findElement(Locators.ADD_TASK_BUTTON);
        input.clear();
        input.sendKeys(task);
        button.click();
        Thread.sleep(500);
        WebElement completeButton = driver.findElement(Locators.getStatusButton(task));
        completeButton.click();
        Thread.sleep(500);
    }

    @When("the user has an incomplete task {string}")
    public void userHasIncompleteTask(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement input = driver.findElement(Locators.TASK_INPUT);
        WebElement button = driver.findElement(Locators.ADD_TASK_BUTTON);
        input.clear();
        input.sendKeys(task);
        button.click();
        Thread.sleep(500);
    }

    @When("the user filters to show only completed tasks")
    public void userFiltersToShowOnlyCompletedTasks() throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement filterDropdown = driver.findElement(Locators.FILTER_DROPDOWN);
        filterDropdown.click();
        filterDropdown.findElement(Locators.FILTER_COMPLETED_OPTION).click();
        Thread.sleep(1000);
    }

}

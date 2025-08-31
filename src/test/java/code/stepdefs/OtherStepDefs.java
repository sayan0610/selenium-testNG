package code.stepdefs;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static org.assertj.core.api.Assertions.assertThat;
import code.hooks.BrowserHooks;

public class OtherStepDefs {
    @When("the user edits the task {string} to {string}")
    public void the_user_edits_the_task_to(String oldName, String newName) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
        Thread.sleep(1000);
    WebElement editButton = driver.findElement(code.locators.Locators.getEditButton(oldName));
        editButton.click();
    WebElement input = driver.findElement(code.locators.Locators.getEditInput(oldName));
        input.clear();
        input.sendKeys(newName);
    WebElement saveButton = driver.findElement(code.locators.Locators.SAVE_BUTTON);
        saveButton.click();
    }

    @When("the user marks the task {string} as completed")
    public void the_user_marks_the_task_as_completed(String task) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
    WebElement completeButton = driver.findElement(code.locators.Locators.getStatusButton(task));
        completeButton.click();
    }

    @Then("the task {string} should be shown as completed")
    public void the_task_should_be_shown_as_completed(String task) {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement taskTable = driver.findElement(By.id("task-list"));
        boolean found = false;
        for (WebElement row : taskTable.findElements(By.tagName("tr"))) {
            if (row.getText().contains(task)) {
                WebElement statusBtn = row.findElement(code.locators.Locators.STATUS_BUTTON);
                String statusText = statusBtn.getText().toLowerCase();
                if (statusText.contains("completed") || statusBtn.getAttribute("class").contains("completed")) {
                    found = true;
                    break;
                }
                WebElement nameCell = row.findElement(code.locators.Locators.getTaskTitleSpan(task));
                String style = nameCell.getAttribute("style");
                if (style != null && style.contains("line-through")) {
                    found = true;
                    break;
                }
            }
        }
        assertThat(found).isTrue();
    }

    @Then("the page title should be {string}")
    public void the_page_title_should_be(String title) {
        WebDriver driver = BrowserHooks.driver.get();
        assertThat(driver.getTitle()).isEqualTo(title);
    }

    @Then("the header should be visible")
    public void the_header_should_be_visible() {
        WebDriver driver = BrowserHooks.driver.get();
        WebElement header = driver.findElement(By.tagName("h1"));
        assertThat(header.isDisplayed()).isTrue();
    }

    @Then("the task details for {string} should be displayed with {string}")
    public void the_task_details_for_should_be_displayed_with(String taskName, String details) throws InterruptedException {
        WebDriver driver = BrowserHooks.driver.get();
    WebElement detailDiv = driver.findElement(code.locators.Locators.getTaskDetailRow(taskName));
        Thread.sleep(1000);
        assertThat(detailDiv.isDisplayed()).isTrue();
        assertThat(detailDiv.getText()).contains(details);
    }
}

package test.java;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    private WebDriver driver;

    @Given("the user is on the Task Manager homepage")
    public void userOnHomePage() {
        driver = new ChromeDriver();
        driver.get("http://localhost:3000");
    }

    @When("the user adds a new task {string}")
    public void userAddsTask(String task) {
        WebElement input = driver.findElement(By.id("task-input"));
        WebElement button = driver.findElement(By.cssSelector("#task-form button[type='submit']"));
        input.sendKeys(task);
        button.click();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
    }

    @Then("the task list should contain {string}")
    public void taskListShouldContain(String task) {
        WebElement taskList = driver.findElement(By.id("task-list"));
        assertThat(taskList.getText()).contains(task);
    }

    @When("the user deletes the task {string}")
    public void userDeletesTask(String task) {
        WebElement taskList = driver.findElement(By.id("task-list"));
        for (WebElement item : taskList.findElements(By.tagName("li"))) {
            if (item.getText().contains(task)) {
                WebElement deleteBtn = item.findElement(By.tagName("button"));
                deleteBtn.click();
                try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
                break;
            }
        }
    }

    @Then("the task list should not contain {string}")
    public void taskListShouldNotContain(String task) {
        WebElement taskList = driver.findElement(By.id("task-list"));
        assertThat(taskList.getText()).doesNotContain(task);
        driver.quit();
    }
}

package code.locators;

import org.openqa.selenium.By;

public class Locators {
    public static final By SAVE_BUTTON = By.xpath("//button[contains(text(), 'Save')]");
    public static final By STATUS_BUTTON = By.xpath(".//button[@data-test-id='status']");
    public static By getEditButton(String task) {
        return By.xpath(".//span[contains(text(), '" + task + "')]//parent::td//following-sibling::td//button[@data-test-id='edit-task']");
    }
    public static By getEditInput(String task) {
        return By.xpath(".//span[contains(text(), '" + task + "')]/following-sibling::span//input[@type='text']");
    }
    public static final By TASK_INPUT = By.id("task-input");
    public static final By TASK_DETAIL_INPUT = By.id("task-detail-input");
    public static final By ADD_TASK_BUTTON = By.cssSelector("#task-form button[type='submit']");
    public static final By TASK_LIST = By.id("task-list");
    public static final By FILTER_DROPDOWN = By.id("filter-dropdown");
    public static final By FILTER_COMPLETED_OPTION = By.xpath(".//option[@value='completed']");
    public static final By BULK_DELETE_BUTTON = By.id("bulk-delete-btn");
    public static final By SELECT_ALL_CHECKBOX = By.xpath("//input[@id='select-all-checkbox']//following-sibling::span[@class='checkmark']");

    public static By getDeleteButton(String task) {
        return By.xpath(".//span[contains(text(), '" + task + "')]//parent::td//following-sibling::td//button[@data-test-id='delete-task']");
    }
    public static By getTaskTitleSpan(String task) {
        return By.xpath(".//span[contains(text(), '" + task + "')]");
    }
    public static By getTaskDetailRow(String task) {
        return By.xpath(".//span[contains(text(), '" + task + "')]//parent::td//parent::tr//following-sibling::tr//td[@data-test='detail-row']");
    }
    public static By getStatusButton(String task) {
        return By.xpath(".//span[contains(text(), '" + task + "')]//parent::td//following-sibling::td//button[@data-test-id='status']");
    }
    public static By getTaskCheckbox(String task) {
        // Assumes task title is unique and you have access to its id via JS or mapping
        // If you only have the title, use XPath to find the checkbox in the same row as the title
        return By.xpath(".//tr[td//span[text()='" + task + "']]//td//input[@class='bulk-checkbox']//following-sibling::span");
    }
    public static By getBulkStatusButton(String status) {
        return By.xpath("//button[@id='bulk-" + status.toLowerCase() + "-btn']");
    }
}

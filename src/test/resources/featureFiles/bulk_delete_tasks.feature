Feature: Bulk Delete Tasks
  As a user
  I want to delete multiple tasks at once
  So that I can quickly clean up my task list

  Scenario: Delete multiple tasks
    Given the user is on the Task Manager homepage
    And the user adds tasks "Task 1", "Task 2", and "Task 3"
    When the user selects and deletes tasks "Task 1" and "Task 2"
    Then the task list should not contain "Task 1"
    And the task list should not contain "Task 2"
    And the task list should contain "Task 3"

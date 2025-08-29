Feature: Task Management
  As a user
  I want to add and delete tasks
  So that I can organize my work

  Scenario: Add a new task
    Given the user is on the Task Manager homepage
    When the user adds a new task "Write Selenium test"
    Then the task list should contain "Write Selenium test"

  Scenario: Delete a task
    Given the user is on the Task Manager homepage
    And the user adds a new task "Delete Me"
    When the user deletes the task "Delete Me"
    Then the task list should not contain "Delete Me"

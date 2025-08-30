Feature: Edit Task
  As a user
  I want to edit an existing task
  So that I can update task details

  Scenario: Edit a task name
    Given the user is on the Task Manager homepage
    And the user adds a new task "Initial Name"
    When the user edits the task "Initial Name" to "Updated Name"
    Then the task list should contain "Updated Name"
    And the task list should not contain "Initial Name"

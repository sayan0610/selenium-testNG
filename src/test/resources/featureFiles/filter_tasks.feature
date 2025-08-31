Feature: Filter Tasks
  As a user
  I want to filter tasks by status
  So that I can focus on specific tasks

  Scenario: Show only completed tasks
    Given the user is on the Task Manager homepage
    And the user has completed the task "Done Task"
    And the user has an incomplete task "Pending Task"
    When the user filters to show only completed tasks
    Then the task list should contain "Done Task"
    And the task list should not contain "Pending Task"



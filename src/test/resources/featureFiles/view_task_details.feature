Feature: View Task Details
  As a user
  I want to view details of a task
  So that I can see more information

  Scenario: View details of a task
    Given the user is on the Task Manager homepage
    And the user adds a new task "Review PR"
    When the user views the details of the task "Review PR"
    Then the task details should be displayed
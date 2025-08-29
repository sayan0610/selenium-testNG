Feature: Task Manager Home Page
  As a user
  I want to view the Task Manager home page
  So that I can manage my tasks

  Scenario: Open Task Manager home page
    Given the user is on the Task Manager homepage
    Then the page title should be "Task Manager"
    And the header should be visible

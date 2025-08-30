Feature: Complete Task
  As a user
  I want to mark a task as completed
  So that I can track finished work

  Scenario: Mark a task as completed
    Given the user is on the Task Manager homepage
    And the user adds a new task "Finish report"
    When the user marks the task "Finish report" as completed
    Then the task "Finish report" should be shown as completed

Feature: Bulk Delete Tasks
  As a user
  I want to delete multiple tasks at once
  So that I can quickly clean up my task list

  Scenario: Delete multiple tasks
    Given the user is on the Task Manager homepage
    And the user adds tasks "TASK A", "TASK B", and "TASK C"
    When the user selects tasks "TASK A" and "TASK B"
    And the user deletes the selected tasks
    Then the task list should not contain "TASK A"
    And the task list should not contain "TASK B"
    And the task list should contain "TASK C"

  Scenario: Bulk change status of all tasks
    Given the user is on the Task Manager homepage
    And the user adds tasks "TASK X", "TASK Y", and "TASK Z"
    When the user selects all tasks
    And the user changes the status of selected tasks to "Complete"
    Then the status of "TASK X" should be "Completed"
    And the status of "TASK Y" should be "Completed"
    And the status of "TASK Z" should be "Completed"

  Scenario: Bulk change status of all tasks to Incomplete
    Given the user is on the Task Manager homepage
    And the user adds tasks "TASK X", "TASK Y", and "TASK Z"
    When the user selects all tasks
    And the user changes the status of selected tasks to "Complete"
    And the user selects all tasks
    And the user changes the status of selected tasks to "Incomplete"
    Then the status of "TASK X" should be "In-Progress"
    And the status of "TASK Y" should be "In-Progress"
    And the status of "TASK Z" should be "In-Progress"

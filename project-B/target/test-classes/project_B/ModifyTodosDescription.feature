@ModifyTodosDescription
Feature: Modify a todo's description field
    As a user, I want to be able to modify the description of an existing todo, to correct the details of a task.

Background:
    Given the API server is running
    And the following todos with description field exist
        | title | doneStatus | description |
        | todo1 | false      | first       |
        | todo2 | true       | second      |
        | todo3 | false      | third       |

# Normal and Alternate Flow
Scenario: Modify a todo's description field successfully
    When a user attempts to modify the description of todos
        | title | doneStatus | description |
        # Normal Flow
        | todo1 | false      | deleted     |
        | todo2 | true       | updated     |
        # Alternate Flow
        | todo3 | false      |             |
    Then the todo's description field should be updated
        | title | doneStatus | description |
        # Normal Flow
        | todo1 | false      | deleted     |
        | todo2 | true       | updated     |
        # Alternate Flow
        | todo3 | false      |             |

# Error Flow
Scenario: Modify a todo's description field unsuccessfully
    When a user sends an incorrect url /todos/1descriptions to modify the description of todos
    Then the todo's description field should not be updated
        | title | doneStatus | description |
        # Normal Flow
        | todo1 | false      | first       |
        | todo2 | true       | second      |
        # Alternate Flow
        | todo3 | false      | third       |
    Then the user should receive an error message No such todo entity instance with GUID or ID 1descriptions found
        
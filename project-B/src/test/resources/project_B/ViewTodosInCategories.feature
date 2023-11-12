@ViewTodosInCategories
Feature: Views all todos in a category
    As a user, I want to be able to view all existing tasks related to a specific category, to keep track of my tasks within a category.

    Background:
    Given the API server is running
    And the following todos are in the system
        | title | doneStatus | description | category |
        | todo1 | false      |             | cat1     |
        | todo2 | true       |             | cat1     |
        | todo3 | false      |             | cat2     |
    And the following categories exist in the system
        | title | description |
        | cat1  |             |
        | cat2  |             |
        | cat3  |             |

    # Normal and Alternate Flow
    Scenario Outline:
        When a user requests to view all todos in a category
        Then the system will return all todos in that category

        Examples:
        | title | doneStatus | description | categories |
        # Normal Flow
        | todo1 | false      |             | cat1       |
        | todo2 | true       |             | cat1       |
        # Alternate Flow
        |       |            |             | cat3       |

    # Error Flow
    Scenario Outline:
        When a user inputs an incorrect endpoint /categories/tod to request to view all todos in a category
        Then the system will return an error "Could not find an instance with categories/tod" and a 404 Not Found status code

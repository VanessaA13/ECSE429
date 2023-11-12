@DeleteTodoAndCategory
Feature: Delete a relationship between a todo and a category
    As a user, I want to delete a relationship between a todo and a category, to remove the todo that does not belong to a category

    Background: 
        Given the API server is running
        And the following todos, categories and relationships in these categories exists:
            | title | doneStatus | category  |
            | todo1 | false      | category1 |
            | todo2 | false      | category1 |
            | todo3 | true       | category2 |


# Normal and Alternate flow
    Scenario: Delete a relationship between a todo and a category sucessfully
        When a user deletes a relationship between a todo and a category
            | title | doneStatus | category  |
            # Normal Flow
            | todo1 | false      | category1 |
            | todo2 | false      | category1 |
            # Alternate Flow (remove a finished todo)
            | todo3 | true       | category2 |
        Then the relationship between the todo and the category no longer exists
            | title | doneStatus | category  |
            # Normal Flow
            | todo1 | false      | category1 |
            | todo2 | false      | category1 |
            # Alternate Flow
            | todo3 | true       | category2 |
        

# Error Flow
    Scenario: Delete a relationship between a todo and a category that does not exist
        When a user attempts to delete a relationship between a todo and a category when the relationship does not exist
            | title | doneStatus | category  |
            | todo1 | false      | category2 |
        And the error message Could not find any instances with todos/3/categories/5 is returned

        
        
        
        
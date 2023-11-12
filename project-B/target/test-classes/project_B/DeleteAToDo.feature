@DeleteATodo
Feature: Delete a todo
        As a user, I want to be able to delete an existing todo, once I have finished a task.

    Background:
        Given the API server is running
        And the following todo instances exist in the system
            | id | title               | doneStatus | description         |
            | 4  | print paperwork     | false      | documents           |
            | 5  | organize paperwork  | false      | in numerical order  |
            | 6  | vacuum floor        | false      |                     |
          

    # Normal and Alternate flow
    Scenario: Deleting a todo successfully 
        When a user deletes a todo with a specfic id
            | id | title               | doneStatus | description         |
            # Normal flow
            | 4  | print paperwork     | false      | documents           |
            | 5  | organize paperwork  | false      | in numerical order  |
            # Alternate flow (delete a initial todo)
            | 1  | scan paperwork      | false      |                     |
        Then the following todos would not exist in the system
            | id | title               | doneStatus | description         |
            # Normal flow
            | 4  | print paperwork     | false      | documents           |
            | 5  | organize paperwork  | false      | in numerical order  |
            # Alternate flow (delete a initial todo)
            | 1  | scan paperwork      | false      |                     |         
   
    # Error flow
    Scenario Outline: Delete a todo that does not exist unsuccessfully
        When a user delete a todo with a id that does not exist
            | id | error                                        |
            | 10 | Could not find any instances with todos/10   |
            | 25 | Could not find any instances with todos/25   |
        Then the following todos may not exist in the system
            | id | error                                        |
            | 10 | Could not find any instances with todos/10   |
            | 25 | Could not find any instances with todos/25   |
        Then the server shall raise an error
            | id | error                                        |
            | 10 | Could not find any instances with todos/10   |
            | 25 | Could not find any instances with todos/25   |
            
            
            
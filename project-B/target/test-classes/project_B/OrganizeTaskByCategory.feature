@OrganizeTaskByCategory
Feature:

        As a user, I want to be able to organize some tasks by category, to better order my tasks

  Background:
    Given the API server is running
      And the following todos registered in the system
      | title                  | doneStatus | description              |
      | clean living room      | false      | hoover living room       |
      And the following categories are registered in the system:
      | title          | description          | 
      | House Chores   | test                 | 

    
    #Normal Flow
    Scenario: Set a task under a category
        Given <todoTitle> is the title of a todo registered on the system
        And there is an existing category with title <categoryTitle>
        When a POST request is sent with the following info:
         | todoTitle         | title          | 
         | clean living room | House Chores   | 
        Then a relationship should be created between task <todoTitle> and category <categoryTitle>
        Examples:
         | todoTitle         | categoryTitle  | 
         | clean living room | House Chores   | 

    #Alternate Flow
    Scenario: Set a non-existent task under category
        Given there is no existing task with title <todoTitle>
        And there is an existing category with title <categoryTitle>
        When a POST request is sent with the following info:
         | todoTile         | title          | 
         | doesn't exist    | House Chores   |
        Then the API server should respond with an error message <errorMessage>
        Examples:
         | todoTitle      | categoryTitle  | errorMessage                                 |
         | doesn't exist  | House Chores   | Could not find parent thing for relationship |

    #Error flow
    Scenario: Set a task under a non-existent category
        Given <todoTitle> is the title of a todo registered on the system
        And there is no existing category with title <categoryTitle>
        When a POST request is sent with the following info:
         | todoTile            | title          | 
         | clean living room   | non-existent   |
        Then a relationship should be created between task <todoTitle> and category <categoryTitle>
        #no relationship should be created, this is a bug
        Examples:
         | todoTitle         | categoryTitle  | 
         | clean living room | non-existent   | 


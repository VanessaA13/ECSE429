@CreatingProjectWithFields
Feature: Creating Project with specific fields

    As a user, I want to create a project with specific fields, so I can prepare the project for task organization

    #Normal flow
    Scenario Outline: Creating project with specific fields and no relationship to task
        Given the todo exists
        | id    | title             | doneStatus | description  |
        | 1     | scan paperwork    | false      |              |
        When the user creates project without tasks <project_title1> <project_completed1> <project_active1> <project_description1>
        Then the new project with specific fields <project_title1> <project_completed1> <project_active1> <project_description1> should exist under Projects 
        Examples:
            | project_title1| project_completed1 | project_active1 | project_description1 | project_tasks1  |
            | ECSE429       | false              | false           | description          | []              |

    #Alternate flow 
    Scenario Outline: Creating project with specific fields and with relationship to task 
        Given the todo exists 
        | id | title          | doneStatus | description | 
        | 1  | scan paperwork | false      |             | 
        When the user creates project with task <project_title2> <project_completed2> <project_active2> <project_description2> <project_tasks2> 
        Then the new project with specific tasks and fields <project_title2> <project_completed2> <project_active2> <project_description2> <project_tasks2> should exist under Projects 
        Examples: 
            | project_title2 | project_completed2 | project_active2 | project_description2 | project_tasks2 | 
            | ECSE429        | false              | false           | description          | [{"id":"1"}]   |
    
    #Error flow
    Scenario Outline: Creating project with specific fields and with relationship to a non-existing task
        Given the following todo with todo_id 3 does not exist
        | id    | title             | doneStatus | description  |
        | 3     | scan paperwork    | false      |              |
        When the user creates project with non-existing task <project_title3> <project_completed3> <project_active3> <project_description3> <project_tasks3>
        Then the new project <project_title3> with specific fields should not exist under Projects and error should be displayed
        Examples:
            | project_title3 | project_completed3 | project_active3 | project_description3 | project_tasks3  |
            | ECSE429        | false              | false           | description          | [{"id":"3"}]     |
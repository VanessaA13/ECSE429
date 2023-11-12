@RemovingProject
Feature: Deleting a specific project

    As a user, I want to delete a specific project, so I can remove a project from the system when it is no longer needed

    #Normal flow
    Scenario Outline: Deleting a specific project without tasks
        Given the project with <project_title1> <project_completed1> <project_active1> <project_description1> exists
        When the user deletes the project with title <project_title1>
        Then this project <project_title1> should no longer exist under Projects
        Examples:
            | project_title1 | project_completed1 | project_active1 | project_description1 | project_tasks1  |
            | ECSE429        | false              | false           | description          | []              |

    #Alternate flow
    Scenario Outline: Deleting a specific project with tasks
        Given the task-associated project with <project_title2> <project_completed2> <project_active2> <project_description2> <project_tasks2> exists
        When the user deletes the task-associated project with title <project_title2>
        Then this project <project_title2> should no longer exist under Projects
        And this project <project_id> should be removed from task <project_tasks2_id>
        Examples:
            | project_title2 | project_completed2 | project_active2 | project_description2 | project_tasks2  | project_tasks2_id | project_id |
            | ECSE429        | false              | false           | description          | [{"id":"1"}]    | 1                 | 3          |
    
    #Error flow
    Scenario Outline: Deleting a non-existing project
        Given the project with title <project_title3> does not exist 
        When the user tries to delete this project <project_title3>
        Then an error message should be generated for deleting <project_id>
        Examples:
            | project_title3 | project_completed3 | project_active3 | project_description3 | project_tasks3  | project_id |
            | ECSE429        | false              | false           | description          | []              | 3          |
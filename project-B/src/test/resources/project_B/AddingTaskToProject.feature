@AddingTaskToProject
Feature: Adding a specific task to a specific project

    As a user, I want to add a specific task to a specific project, so I can organize the tasks into the project

    #Normal flow
    Scenario Outline: Adding a specific task to a specific project which is unrelated to the task
        Given the todo with <project_task1> and project with <project_title1> exists
        When the user add the todo <project_task1> to the project <project_title1>
        Then the todo <project_task1> should exist in the project's <project_title1> tasks field
        Examples:
            | project_title1 | project_completed1 | project_active1 | project_description1 | project_task1  |
            | ECSE429        | false              | false           |                      | 1              |

    #Alternate flow
    Scenario Outline: Adding a specific task to a specific project which is already related to the task
        Given the project with <project_title2> exists and associated with <project_task21> and <project_task22>
        When the user add the todo <project_task21> to the associated project <project_title2>
        Then the project <project_title2> remain associated with <project_task21> and <project_task22>
        Examples:
            | project_title2 | project_completed2 | project_active2 | project_description2 | project_task21  | project_task22  |
            | Office Work    | false              | false           |                      | 1               | 2               |
    
    #Error flow
    Scenario Outline: Creating Project with specific fields and with relationship to a non-existing task
        Given the project with <project_title3> exists but <project_task3> does not exist
        When the user add the non-existing todo <project_task3> to the project <project_title3>
        Then the todo <project_task3> should not exist in the project's <project_title3> project_tasks field
        Examples:
            | project_title3 | project_completed3 | project_active3 | project_description3 | project_task3  |
            | ECSE429        | false              | false           | description          | 3              |
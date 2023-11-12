@RemovingTaskFromProject
Feature: Removing a specific task from a specific project

    As a user, I want to remove a specific task from a specific project, so I can remove a task from a project when they are no longer related

    #Normal flow
    Scenario Outline: Removing a specific task from a specific project which is only related to that task
        Given the project with <project_title1> exists and is associated with <project_task1>
        When the user removes <project_task1> from the project <project_title1>
        Then the todo <project_task1> should not exist under the project's <project_title1> project_tasks field
        Examples:
            | project_title1 | project_completed1 | project_active1 | project_description1 | project_task1 |
            | ECSE429        | false              | false           |                      | 1             |

    #Alternate flow
    Scenario Outline: Removing a specific task from a specific project which is related to several tasks
        Given the project with <project_title2> exists and is associated with <project_task21> and <project_task22>
        When the user removes <project_task21> from project <project_title2> associated to another task
        Then only the todo <project_task22> should exist under the project's <project_title2> project_tasks field
        Examples:
            | project_title2 | project_completed2 | project_active2 | project_description2 | project_task21  | project_task22  |
            | Office Work    | false              | false           |                      | 1                | 2                |
    
    #Error flow
    Scenario Outline: Removing a specific task from a specific project which is not related to the task
        Given the project with <project_title3> exists and unassociated with <project_task3>
        When the user removes <project_task3> from the unassociated project <project_title3>
        Then the todo <project_task3> should not exist under the project's <project_title3> project_tasks field and error should be displayed
        Examples:
            | project_title3 | project_completed3 | project_active3 | project_description3 | project_task3  |
            | ECSE429        | false              | false           |                      | 3              |
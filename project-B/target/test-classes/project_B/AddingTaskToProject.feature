Feature: Adding a specific task to a specific project

    As a user, I want to add a specific task to a specific project, so I can organize the tasks into the project


    #Normal flow
    Scenario Outline: Adding a specific task to a specific project which is unrelated to the task
        Given the todo with todo_id: 1, todo_title: "scan paperwork", todo_doneStatus: false, todo_description: "", exists
        And project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [], exists
        When the user add the todo to the project
        Then the todo should exist in the project's project_tasks field
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | ECSE429       | false             | false          |                     |[{id:1}]        |

    #Alternate flow
    Scenario Outline: Adding a specific task to a specific project which is already related to the task
        Given the todo with todo_id: 1, todo_title: "scan paperwork", todo_doneStatus: false, todo_description: "", exists
        And project with project_title: "Office Work", project_completed: false, project_active: false, project_description: "", project_tasks: [{id:1},{id:2}], exists
        When the user add the todo to the project
        Then nothing will change
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | Office Work   | false             | false          |                     |[{id:1},{id:2}] |
    
    #Error flow
    Scenario Outline: Creating Project with specific fields and with relationship to a non-existing task
        Given the todo with todo_id: 1, todo_title: "scan paperwork", todo_doneStatus: false, todo_description: "", does not exist
        And project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [], exists
        When the user add the todo to the project
        Then the todo should not exist in the project's project_tasks field
        And an error message should be displayed
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | ECSE429       | false             | false          |                     |[]              |
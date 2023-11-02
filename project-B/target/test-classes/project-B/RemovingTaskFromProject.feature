Feature: Removing a specific task from a specific project

    As a user, I want to remove a specific task from a specific project, so I can remove a task from a project when they are no longer related

    #Normal flow
    Scenario Outline: Removing a specific task from a specific project which is only related to that task
        Given the todo with todo_id: 1, todo_title: "scan paperwork", todo_doneStatus: false, todo_description: "", exists
        And project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [{id:1}], exists
        When the user tries to remove the todo from the project
        Then the todo should not exist in the project's project_tasks field
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | ECSE429       | false             | false          |                     |[]              |

    #Alternate flow
    Scenario Outline: Removing a specific task from a specific project which is related to several tasks
        Given the todo with todo_id: 1, todo_title: "scan paperwork", todo_doneStatus: false, todo_description: "", exists
        And project with project_title: "Office Work", project_completed: false, project_active: false, project_description: "", project_tasks: [{id:1},{id:2}], exists
        When the user tries to remove the todo from the project
        Then the todo should not exist in the project's project_tasks field
        And other todos related to the project will not be affected
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | Office Work   | false             | false          |                     |[{id:2}]        |
    
    #Error flow
    Scenario Outline: Removing a specific task from a specific project which is not related to the task
        Given the todo with todo_id: 1, todo_title: "scan paperwork", todo_doneStatus: false, todo_description: "", exists
        And project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [], exists
        When the user tries to remove the todo from the project
        Then the todo should not exist in the project's project_tasks field
        And an error message should be displayed
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | ECSE429       | false             | false          |                     |[]              |
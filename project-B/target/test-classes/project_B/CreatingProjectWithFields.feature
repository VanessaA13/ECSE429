Feature: Creating Project with specific fields

    As a user, I want to create a project with specific fields, so I can prepare the project for task organization

    #Normal flow
    Scenario Outline: Creating project with specific fields and no relationship to task
        Given the todo with todo_id: 1, todo_title: "scan paperwork", todo_doneStatus: false, todo_description: "", exists
        When the user creates project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: []
        Then the new project with specific fields should exist under Projects
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | ECSE429       | false             | false          |                     |[]              |

    #Alternate flow
    Scenario Outline: Creating project with specific fields and with relationship to task
        Given the todo with todo_id: 1, todo_title: "scan paperwork", todo_doneStatus: false, todo_description: "", exists
        When the user creates project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [{id:1}]
        Then the new project with specific fields should exist under Projects
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | ECSE429       | false             | false          |                     |[{id:1}]        |
    
    #Error flow
    Scenario Outline: Creating project with specific fields and with relationship to a non-existing task
        Given the todo with todo_id: 3, does not exist
        When the user creates project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [{id:3}]
        Then the new project with specific fields should not exist under Projects
        And an error message should be displayed
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            |               |                   |                |                     |                |
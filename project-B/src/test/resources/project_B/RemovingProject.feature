Feature: Deleting a specific project

    As a user, I want to delete a specific project, so I can remove a project from the system when it is no longer needed

    #Normal flow
    Scenario Outline: Deleting a specific project without tasks
        Given the project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [], exists
        When the user tries to delete this project
        Then this project should no longer exist under Projects
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            |               |                   |                |                     |                |

    #Alternate flow
    Scenario Outline: Deleting a specific project with tasks
        Given the project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [{id:1}], exists
        When the user tries to delete this project
        Then this project should no longer exist under Projects
        And this project should be removed from the todo_tasksof field of todo: {id:1}
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            |               |                   |                |                     |                |
    
    #Error flow
    Scenario Outline: Deleting a non-existing project
        Given the project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [], does not exist
        When the user tries to delete this project
        Then the project should not exist under Projects
        And an error message should be displayed
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            |               |                   |                |                     |                |
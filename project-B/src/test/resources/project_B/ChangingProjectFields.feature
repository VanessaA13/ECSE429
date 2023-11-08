Feature: Changing the non-tasks fields of a specific project

    As a user, I want to change the non-tasks fields of a specific project, so I can modify the information related to the project


    #Normal flow
    Scenario Outline: Setting the non-tasks fields of the project to different values
        Given the project with project_title: "Office Work", project_completed: false, project_active: false, project_description: "", project_tasks: [{id:1},{id:2}], exists
        When the user set the project with project_title: "Project1", project_completed: true, project_active: true, project_description: "description1"
        Then the project's non-tasks fields should be updated
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | Project1      | true              | true           | description1        |[{id:1},{id:2}] |

    #Alternate flow
    Scenario Outline: Setting the non-tasks fields of the project to the original values
        Given the project with project_title: "Office Work", project_completed: false, project_active: false, project_description: "", project_tasks: [{id:1},{id:2}], exists
        When the user set the project with project_title: "Office Work", project_completed: false, project_active: false, project_description: ""
        Then the project's non-tasks fields should remain the original values
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            | Office Work   | false             | false          |                     |[{id:1},{id:2}] |
    
    #Error flow
    Scenario Outline: Setting the non-tasks fields of a non-existing project
        Given the project with project_title: "ECSE429", project_completed: false, project_active: false, project_description: "", project_tasks: [], does not exist
        When the user set the project with project_title: "ECSE428", project_completed: true, project_active: true, project_description: "description2"
        Then the project with modified non-tasks fields should not exist under Projects
        And an error message should be displayed
        Examples:
            | project_title | project_completed | project_active | project_description | project_tasks  |
            |               |                   |                |                     |                |
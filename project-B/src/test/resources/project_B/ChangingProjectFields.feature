@ChangingProjectFields
Feature: Changing the non-tasks fields of a specific project

    As a user, I want to change the non-tasks fields of a specific project, so I can modify the information related to the project


    #Normal flow
    Scenario Outline: Setting the non-tasks fields of the project to different values
        Given the following project exists
        | title   | completed | active | description |
        | ECSE429 | false     | false  | description |
        When the user set the project <old_title1> with <project_title1> <project_completed1> <project_active1> <project_description1>
        Then the project's non-tasks fields should be updated to <project_title1> <project_completed1> <project_active1> <project_description1>
        Examples:
            | project_title1 | project_completed1 | project_active1 | project_description1  | old_title1 |
            | ECSE428        | true               | true            | description1          | ECSE429    |

    #Alternate flow
    Scenario Outline: Setting the non-tasks fields of the project to the original values
        Given the following project exists
        | title   | completed | active | description |
        | ECSE429 | false     | false  | description |
        When the user set this project <old_title2> with <project_title2> <project_completed2> <project_active2> <project_description2>
        Then the project's non-tasks fields should remain as <project_title2> <project_completed2> <project_active2> <project_description2>
        Examples:
            | project_title2 | project_completed2 | project_active2 | project_description2  | old_title2 |
            | ECSE429        | false              | false           | description           | ECSE429    |
    
    #Error flow
    Scenario Outline: Setting the non-tasks fields of a non-existing project
        Given the following project does not exist
        | title   | completed | active | description |
        | ECSE429 | false     | false  | description |
        When the user tries to set the non-existing project <id> with <project_title3> <project_completed3> <project_active3> <project_description3>
        Then the project <id> with modified non-tasks fields should not exist under Projects
        Examples:
            | project_title3 | project_completed3 | project_active3 | project_description3  | id     |
            | ECSE428        | true               | true            | description1          | 2      |
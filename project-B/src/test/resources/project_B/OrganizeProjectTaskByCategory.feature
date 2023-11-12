@OrganizeProjectTaskByCategory
Feature:

       As a user, I want to be able to add a project reference to a category to link together related tasks

  Background:
    Given the API server is running
      And the following categories are registered in the system:
        |categoryID    | categoryTitle          | categoryDescription  | projects   |
        |2             | Home                   | [blank]              |  [blank]      |
        |1              | Office                | [blank]              |  [blank]      |
      And the following projects are registered in the system:
        |projectID    | projectTile          | projectCompleted | projectActive | projectDescription | 
        |1            | Office Work          | false            | false         | [blank]     | 
    
    #Normal Flow
    Scenario: Add a project that exists to a category that exists
        Given <categoryTitle> is the title of a category 
        And <categoryDescription> is the description of a category
        And <categoryID> is the id of a category
        And <projectID> is the id of a project        
        And <projectTitle> is the title of a project
        And <projectCompleted> is the completed value of a project
        And <projectActive> is the active value of a project
        And <projectDescription> is the description of a project
        When a POST request is sent with the following info for the category and project:
        |categoryID    | categoryTitle   | categoryDescription  | projectID    | projectTitle          | projectCompleted | projectActive | projectDescription |
        |2             | Home            | [blank]              |1            | Office Work           | false            | false         | [blank]              | 
        
        
        
        Then a relationship is formed between a project with <projectID> and <projectTitle> and a category with <categoryTitle>
        Examples:
        |categoryID    | categoryTitle         | categoryDescription  | projectID   | projectTitle |
        |2             | Home                  | [blank]              |  1          | Office Work |
        

    #Alternate Flow
    Scenario: Add a project with an invalid id to a category that exists
        Given <categoryTitle> is the title of a category 
        And <categoryDescription> is the description of a category
        And <categoryID> is the id of a category
        And <projectID> is the id of a project        
        And <projectTitle> is the id of a project
        And <projectCompleted> is the completed value of a project
        And <projectActive> is the active value of a project
        And <projectDescription> is the description of a project
        When a POST request is sent with the following info for the category and project:
        |categoryID    | categoryTitle   | categoryDescription  | projectID    | projectTitle          | projectCompleted | projectActive | projectDescription | 
        |2             | Home            | [blank]              | 3            | Office Work           | false           | false          | [blank]                   | 
        
        
        
        Then the server should respond with an error message <errorMessage>
        Examples:
        |categoryID    | categoryTitle  | categoryDescription  | errorMessage   |
        | [blank]      | [blank]        | [blank]              | Could not find thing matching value for id |

    #Error flow
    Scenario: Add a project that exists to a category id that's invalid
        Given <categoryTitle> is the title of a category 
        And <categoryDescription> is the description of a category
        And <categoryID> is the id of a category
        And <projectID> is the id of a project        
        And <projectTitle> is the id of a project
        And <projectCompleted> is the completed value of a project
        And <projectActive> is the active value of a project
        And <projectDescription> is the description of a project
        When a POST request is sent with the following info for the category and project:
        |categoryID    | categoryTitle   | categoryDescription  | projectID    | projectTitle          | projectCompleted | projectActive | projectDescription |
        |10             | Home            | [blank]              | 1            | Office Work           | false            | false         | [blank]            | 
        
        
        
        Then the server should respond with an error message <errorMessage>
        Examples:
        |categoryID    | categoryTitle   | categoryDescription    | errorMessage  |
        | [blank]      | [blank]         |[blank]                 |Could not find parent thing for relationship categories/10/projects|


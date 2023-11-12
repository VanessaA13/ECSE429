@DeleteProjectUnderCategory
Feature:

       As a user, I want to be able to delete a project reference from a category once done with the project

  Background:
    Given the API server is running
      And the following categories are registered in the system:
        |id    | title          | description  | projectID   |
        |2     | Home           | [blank]      |  1           |
        |1     | Office         | [blank]      |  [blank]      |
      And the following projects are registered in the system:
        |id    | title          | completed | active | description | 
        |1     | Office Work    | false     | false  | [blank]            | 
    
    #Normal Flow
    Scenario: Delete a project that exists under a category that exists
        Given <categoryTitle> is the title of a category 
        And <categoryDescription> is the description of a category
        And <categoryID> is the id of a category
        And <projectID> is the id of a project        
        And <projectTitle> is the id of a project
        And <projectCompleted> is the completed value of a project
        And <projectActive> is the active value of a project
        And <projectDescription> is the description of a project
        And <projectRelationship> is the project that's under the category
        When a DELETE request is sent with the following info for the category:
         
        |categoryID    | categoryTitle   | categoryDescription  | projectRelationship | projectID    | projectTitle          | projectCompleted | projectActive | projectDescription |
        |2             |   [blank]       |  [blank]             |  1                  | 1           | Office Work           | false            | false         | [blank]            |
        
        
        Then the relationship is deleted between the with <projectID> and the category with <categoryTitle>
        Examples:
        |categoryID    | categoryTitle   | categoryDescription  | projectRelationship   |
        |2     | Home           | [blank]                       |  [blank]      |
        |1     | Office         | [blank]                       |  [blank]      |
        

    #Alternate Flow
    Scenario: Delete a project with an invalid id under a category that exists
        Given <categoryTitle> is the title of a category 
        And <categoryDescription> is the description of a category
        And <categoryID> is the id of a category
        And <projectID> is the id of a project        
        And <projectTitle> is the id of a project
        And <projectCompleted> is the completed value of a project
        And <projectActive> is the active value of a project
        And <projectDescription> is the description of a project
        And <projectRelationship> is the project that's under the category
        When a DELETE request is sent with the following info for the category:
        |categoryID    | categoryTitle   | categoryDescription | projectRelationship | projectID    | projectTitle          | projectCompleted | projectActive | projectDescription |
        |2             | Home            | [blank]             |  [blank]               |3             | Office Work           | false            | false         |  [blank]           |
        
        
        Then the server should respond with an error message <errorMessage>
        Examples:
        |categoryID    | categoryTitle   | categoryDescription | errorMessage |
        | [blank]      |  [blank]        |   [blank]           | Could not find any instances with categories/2/projects/3 |

    #Error flow
    Scenario: Delete a project that exists under a category id that's invalid
        Given <categoryTitle> is the title of a category 
        And <categoryDescription> is the description of a category
        And <categoryID> is the id of a category
        And <projectID> is the id of a project        
        And <projectTitle> is the id of a project
        And <projectCompleted> is the completed value of a project
        And <projectActive> is the active value of a project
        And <projectDescription> is the description of a project
        And <projectRelationship> is the project that's under the category
        When a DELETE request is sent with the following info for the category:
        |categoryID    | categoryTitle   | categoryDescription | projectRelationship | projectID    | projectTitle          | projectCompleted | projectActive | projectDescription |
        |4             | Home            | [blank]             |  [blank]               |1             | Office Work           | false            | false         | [blank]     |
        
        
        Then the server should respond with an error message <errorMessage>
        Examples:
        |categoryID    | categoryTitle   | categoryDescription | errorMessage |
        | [blank]    | [blank]        | [blank]               | Could not find any instances with categories/4/projects/1 |


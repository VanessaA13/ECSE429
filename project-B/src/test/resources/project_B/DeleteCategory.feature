@DeleteCategory
Feature:

        As a user, I want to be able to delete a category once I’m done with it.

  Background:
    Given the API server is running
      And the following categories are registered in the system:
        |categoryID    | categoryTitle          | categoryDescription      | 
        |2             | Home                   |  [blank]                 | 
        |1             | Office                 |  [blank]                | 

    
    #Normal Flow
    Scenario: Delete a category with all fields filled
        Given <categoryID> is the id of a category with all fields filled
        And <categoryTitle> is the title of a category with all fields filled
        And <categoryDescription> is the description of a category with all fields filled
        When a DELETE request is sent with all the fields filled:
        |categoryID    | categoryTitle    | categoryDescription     | 
        |3             | mandatory        | nonMandatory            | 
        Then the category should be deleted and <categoryTitle> and <categoryDescription> should not be there
        Examples:
        |categoryID    | categoryTitle  | categoryDescription   | 
        |2     | Home                   | [blank]               | 
        |1     | Office                 | [blank]               | 
        |3     | mandatory              | nonMandatory          |

    #Alternate Flow
    Scenario: Delete a category with only mandatory fields filled
        Given <categoryID> is the id of a category with only mandatory fields filled
        When a DELETE request is sent with the following info:
        |categoryID    | categoryTitle          | categoryDescription   | 
        |1             | Office                 | [blank]               | 
        Then the category should be deleted and <categoryTitle> and <categoryDescription> should not be there
        Examples:
        |categoryID    | categoryTitle          | categoryDescription      | 
        |2             | Home                   | [blank]                    | 

    #Error flow
    Scenario: Delete a category with an id that doesn't exist
        When a DELETE request is sent with the following info:
        |categoryID    | categoryTitle          | categoryDescription   | 
        |10             | title               | [blank]       |
        Then the server should respond with an error message <errorMessage>
        Examples:
        |categoryID   | categoryTitle    | categoryDescription   | errorMessage |
        | [blank]           |  [blank]         |  [blank]        | Could not find any instances with categories/10 |


@ModifyCategory
Feature:

       As a user, I want to be able to modify the properties of a category to be able to update it

  Background:
    Given the API server is running
      And the following categories are registered in the system:
        |categoryID    | categoryTitle          | categoryDescription     | 
        |2             | Home                   | [blank]                 | 
        |1             | Office                 |  [blank]                |

    
    #Normal Flow
    Scenario: Modify all fields in a category
        Given <categoryTitle> is the title of a category 
        And <categoryDescription> is the description of a category
        And <categoryID> is the id of a category
        When a POST request to categories is sent with the category id and the following info:         
        |categoryID    | categoryTitle          | categoryDescription      | 
        |2             | newTitle               | newDescription           | 
        Then a category should be modified and have the new <categoryTitle>, <categoryDescription> and <categoryID>
        Examples:
        |categoryID    | categoryTitle          | categoryDescription         | 
        |2             | newTitle               | newDescription              | 
        

    #Alternate Flow
    Scenario: Modify a category to have only required fields
        Given <categoryTitle> is the title of a category 
        And <categoryDescription> is the description of a category
        And <categoryID> is the id of a category
        When a POST request to categories is sent with the category id and the following info:         
        |categoryID    | categoryTitle          | categoryDescription         | 
        |3             | newTitle               |   [blank]                     | 
        Then a category with the <categoryID> will have empty fields apart from the <categoryTitle> should be created
        Examples:
        |categoryID    | categoryTitle          | categoryDescription           | 
        |2             | Home                    | [blank]               | 
        |1             | Office                 | [blank]               | 
        |3             | newTitle               | [blank]               |

    #Error flow
    Scenario: Modify a category that doesn't exist
        Given <categoryID> is the id of a category 
        When a POST request to categories is sent with the category id and the following info:         
        |categoryID    | categoryTitle          | categoryDescription           | 
        |10             | Home                   | [blank]                      | 
        Then the server should respond with an error message <errorMessage>
        Examples:
        |categoryID           | categoryTitle          | categoryDescription           | errorMessage |
        |[blank]              |[blank]                 | [blank]               | No such category entity instance with GUID or ID 4 found |


@AddCategory
Feature:

        As a user, I want to be able to create a new category, to better organize my daily tasks.

  Background:
    Given the API server is running
      And the following categories are registered in the system:
        | categoryTitle  | categoryDescription      | 
        | Home           | [blank                   | 
        | Office         | [blank]                  | 

    
    #Normal Flow
    Scenario: Create a category
        Given <categoryTitle> is the title of a category
        And <categoryDescription> is the description of a category
        When a POST request to categories is sent with the following info:
        | categoryTitle         | categoryDescription |
        | newTitle              | newDescription      |
        Then a category should be created with the title <categoryTitle> and description <categoryDescription>
        Examples:
        | categoryTitle  | categoryDescription   |
        | newTitle       | newDescription        |

    #Alternate Flow
    Scenario: Create a category with only mandatory fields filled
        Given <categoryTitle> is the required field of a category
        And <categoryDescription> is not a required field of a category
        When a POST request to categories is sent with the following info:
         | categoryTitle         | categoryDescription  |
         | newTitle2             | [blank]              |
        Then a category should be created with the title <categoryTitle> and no <categoryDescription>
        Examples:
        | categoryTitle  | categoryDescription  |
        | newTitle2      | [blank]              |

    #Error flow
    Scenario: Create a category with the mandatory fields not filled
        When a POST request to categories is sent with the following info:
         | categoryTitle | categoryDescription  |
         | [blank]       | [blank]              |

        Then the server should respond with an error message <errorMessage>
        Examples:
       | categoryTitle | categoryDescription  | errorMessage |
       | [blank]       | [blank]              | Failed Validation: title : can not be empty|
        


@AddATodo
Feature: Create a new instance of todo
    As a user, I want to be able to create a new todo task, to add to my daily tasks.

    Background:
        Given the API server is running

    # Normal Flow 
    Scenario: Creating a todo successfully
        When a user create a todo with a title, a doneStatus a description:
            | title               | doneStatus | description         |
            | print paperwork     | false      | documents           |
            | organize paperwork  | false      | in numerical order  |
        Then the following todos shall exist in the system:
            | title               | doneStatus | description         |
            | print paperwork     | false      | documents           |
            | organize paperwork  | false      | in numerical order  |

    # Alternate flow
    Scenario: Creating a todo successfully with no description
        When a user create a todo with a title, a doneStatus an empty description 
            | title               | doneStatus | description         |   
            | vacuum floor        | false      |                     |
            | clean dishes        | false      |                     |
            | make bed            | false      |                     |
        Then the following todos shall still exist in the system
            | title               | doneStatus | description         |
            | vacuum floor        | false      |                     |
            | clean dishes        | false      |                     |
            | make bed            | false      |                     |

            

    # Error flow
    Scenario: Creating todo with an empty title unsuccessfully
        When a user create a todo with an empty title
        | title     | doneStatus | description | error                                         |
        |           | false      |             | Failed Validation: title : can not be empty   |
        Then the following todos with the title shall not exist
        | title     | doneStatus | description | error                                         |
        |           | false      |             | Failed Validation: title : can not be empty   |
        Then the server will raise an error
        | title     | doneStatus | description | error                                         |
        |           | false      |             | Failed Validation: title : can not be empty   |
 

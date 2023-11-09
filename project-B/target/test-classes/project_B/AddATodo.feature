Feature: Create a new instance of todo
    As a user, I want to be able to create a new todo task, to add to my daily tasks.

    # Background:
        # Given the following todos exist:
            # | id | title           | doneStatus | description | tasksof   | categories |
            # | 1  | scan paperwork  | false      |             | {id = 1}  | {id = 1}   |
            # | 2  | file paperwork  | false      |             | {id = 1}  |            |
        # Given the following projects exist:
            # | id | title       | completed | active | description | tasks               | categories |
            # | 1  | Office Work | false     | false  |             | {id = 1, id = 2}  |            |
        # Given the following categories exist:
            # | id | title  | description | tasks | categories | 
            # | 1  | Office |             |       |            |
            # | 2  | Home   |             |       |            |

    # Normal and Alternate flow
    Scenario Outline: Creating a todo in relation to Office Work project successfully 
        When a user create a todo with title "<title>", doneStatus "<doneStatus>", description "<description>",tasksof "<tasksof>" and categories "<categories>"
        Then the tasks with title "<title>", doneStatus "<doneStatus>", description "<description>", tasksof "<tasksof>" and categories "<categories>" shall exist
        
        Examples:
            | title               | doneStatus | description         | tasksof   | categories | statusCode |
            # Normal flow
            | print paperwork     | false      | documents           | {id = 1}  | {id = 1}   ||
            | organize paperwork  | false      | in numerical order  | {id = 1}  | {id = 1}   ||
            # Alternate flow
            | vacuum floor        |            |                     |           | {id = 2}   ||
            | clean dishes        |            |                     |           | {id = 2}   ||
            | make bed            |            |                     |           | {id = 2}   | |         
            

    # Error flow
    Scenario Outline: Creating todo with a taskof to a non-existing project or category
        When a user create a todo with title "<title>" and a doneStatus "<doneStatus>", a description "<description>", tasksof "<tasksof>" and categories "<categories>"
        Then the tasks with title "<title>", doneStatus "<doneStatus>", description "<description>", tasksof "<tasksof>" and categories "<categories>" shall not exist
        Then the app will raise an error "<error>"

        Examples:
            | title               | doneStatus | description | tasksof | categories | error | statusCode |
            | print paperwork     | false      |             | id = 3  | {id = 1}   |       |            |
            | organize paperwork  | false      |             | id = 3  | {id = 1}   |       |            |
            | make bed            | false      | sheets      |         | {id = 5}   |       |            |

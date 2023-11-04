Feature:
As a student,
I mark a task as done on my project to do list,
so I can track my accomplishments.

  Background:
    Given the API server is running
      And the following todos registered in the system
      | title                  | doneStatus | description                                          |
      | example1               | false      | test                                                 |
      | example2               | true       | test                                                 |
      | x                      | true       | This todo also exists to try different titles        |

  Scenario Outline: Mark non completed todo as completed (Normal Flow)
    Given <selectedTitle> is the title of a todo registered on the system
      And the todo with title <selectedTitle> is not marked as done
     When the user chooses to mark the task named <selectedTitle> as done
     Then the todo with title <selectedTitle> will be marked as done on the system
      And the updated todo will be returned to the user and marked as done
    Examples:
      | selectedTitle        |
      | example1             |

  Scenario Outline: Mark already completed todo as completed (Alternate Flow)
    Given <selectedTitle> is the title of a todo registered on the system
      And the todo with title <selectedTitle> is marked as done
     When the user chooses to mark the task named <selectedTitle> as done
     Then no todo on the system will be modified
      And the todo will be returned to the user
    Examples:
      | selectedTitle          |
      | example2               |
      | x                      |

  Scenario Outline: Mark non-existent todo as completed (Error flow)
    Given <selectedTitle> is not a title of a todo registered on the system
     When the user chooses to mark the task named <selectedTitle> as done
     Then no todo on the system will be modified
      And the user will receive an error message that the specified todo does not exist
    Examples:
      | selectedTitle |
      | fake title    |
      | null          |

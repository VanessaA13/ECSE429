@ViewCategories
Feature: View categories

  As a user, I want to be able to query all available categories, to know what to  mark my tasks under

Background: 
    Given the API server is running

#Normal flow
Scenario: Querying all categories
    Given the following categories are registered in the system:
      | title      | description          | 
      | homework   | homework stuff       | 
      | assignment | assignment stuff     | 
    When a GET request is sent to /categories
    Then the response body should contain the following categories:
      | title      | description          | 
      | homework   | homework stuff       | 
      | assignment | assignment stuff     | 

# #Alternate flow
Scenario: Querying categories with no categories registered
  Given the API server is running
  When a GET request is sent to /categories
  Then the response body should only contain the original categories:
      | title      | id   | 
      | Home       | 2    | 
      | Office     | 1    | 

#Error flow
Scenario: Querying categories with an invalid endpoint
  Given the API server is running
  When a GET request is sent to /categoriez
  Then the API server should respond with a 404 Not Found status code


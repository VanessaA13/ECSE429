@ViewProjects
Feature: View projects

  As a user, I want to be able to query all available projects, to keep track of what projects I have going on at the moment

Background: 
    Given the API server is running

#Normal flow
Scenario: Querying all projects
    Given the following projects are registered in the system:
      | title                  | description          | 
      | Renovate Living Room   | test                 | 
      | Renovate Bathroom      | bathroom stuff       | 
    When a GET request is sent to /projects
    Then the response body should contain the following categories:
      | title                  | description          | 
      | Renovate Living Room   | test                 | 
      | Renovate Bathroom      | bathroom stuff       | 

# #Alternate flow
Scenario: Querying projects with no projects registered
  Given the API server is running
  When a GET request is sent to /projects
  Then the response body should only contain the original projects:
      | title         | id   | 
      | Office Work   | 1    | 


#Error flow
Scenario: Querying projects with an invalid endpoint
  Given the API server is running
  When a GET request is sent to /projectz
  Then the request with endpoint /projectz should respond with a 404 Not Found status code


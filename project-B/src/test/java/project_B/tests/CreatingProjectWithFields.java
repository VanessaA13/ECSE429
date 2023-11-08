package project_B.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CreatingProjectWithFields {
    @Given("the todo with todo_id: {int}, todo_title: {string}, todo_doneStatus: false, todo_description: {string}, exists")
    public void the_todo_with_todo_id_todo_title_todo_done_status_false_todo_description_exists(Integer int1, String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @When("the user creates project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}, project_tasks: []")
    public void the_user_creates_project_with_project_title_project_completed_false_project_active_false_project_description_project_tasks(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("the new project with specific fields should exist under Projects")
    public void the_new_project_with_specific_fields_should_exist_under_projects() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @When("the user creates project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}, project_tasks: [\\{id:{int}}]")
    public void the_user_creates_project_with_project_title_project_completed_false_project_active_false_project_description_project_tasks(String string, String string2, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Given("the todo with todo_id: {int}, does not exist")
    public void the_todo_with_todo_id_does_not_exist(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("the new project with specific fields should not exist under Projects")
    public void the_new_project_with_specific_fields_should_not_exist_under_projects() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}

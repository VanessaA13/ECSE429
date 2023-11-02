package project_B;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RemovingProject {
    @Given("the project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}, project_tasks: [], exists")
    public void the_project_with_project_title_project_completed_false_project_active_false_project_description_project_tasks_exists(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @When("the user tries to delete this project")
    public void the_user_tries_to_delete_this_project() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("this project should no longer exist under Projects")
    public void this_project_should_no_longer_exist_under_projects() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Given("the project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}, project_tasks: [\\{id:{int}}], exists")
    public void the_project_with_project_title_project_completed_false_project_active_false_project_description_project_tasks_exists(String string, String string2, Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("this project should be removed from the todo_tasksof field of todo: \\{id:{int}}")
    public void this_project_should_be_removed_from_the_todo_tasksof_field_of_todo(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Given("the project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}, project_tasks: [], does not exist")
    public void the_project_with_project_title_project_completed_false_project_active_false_project_description_project_tasks_does_not_exist(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("the project should not exist under Projects")
    public void the_project_should_not_exist_under_projects() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}

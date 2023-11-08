package project_B.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ChangingProjectFields {
    @Given("the project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}, project_tasks: [\\{id:{int}},\\{id:{int}}], exists")
    public void the_project_with_project_title_project_completed_false_project_active_false_project_description_project_tasks_exists(String string, String string2, Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @When("the user set the project with project_title: {string}, project_completed: true, project_active: true, project_description: {string}")
    public void the_user_set_the_project_with_project_title_project_completed_true_project_active_true_project_description(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("the project's non-tasks fields should be updated")
    public void the_project_s_non_tasks_fields_should_be_updated() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @When("the user set the project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}")
    public void the_user_set_the_project_with_project_title_project_completed_false_project_active_false_project_description(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("the project's non-tasks fields should remain the original values")
    public void the_project_s_non_tasks_fields_should_remain_the_original_values() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Given("the project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}, project_tasks: [], does not exist")
    public void the_project_with_project_title_project_completed_false_project_active_false_project_description_project_tasks_does_not_exist(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("the project with modified non-tasks fields should not exist under Projects")
    public void the_project_with_modified_non_tasks_fields_should_not_exist_under_projects() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    
    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}

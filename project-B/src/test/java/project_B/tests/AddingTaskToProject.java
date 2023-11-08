package project_B.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddingTaskToProject {

    @Given("the todo with todo_id: {int}, todo_title: {string}, todo_doneStatus: false, todo_description: {string}, exists")
    public void the_todo_with_todo_id_todo_title_todo_done_status_false_todo_description_exists(Integer int1, String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
       throw new io.cucumber.java.PendingException();
    }
     
    @Given("project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}, project_tasks: [], exists")
    public void project_with_project_title_project_completed_false_project_active_false_project_description_project_tasks_exists(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
     
    @When("the user add the todo to the project")
    public void the_user_add_the_todo_to_the_project() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
     
    @Then("the todo should exist in the project's project_tasks field")
    public void the_todo_should_exist_in_the_project_s_project_tasks_field() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
     
    @Given("project with project_title: {string}, project_completed: false, project_active: false, project_description: {string}, project_tasks: [\\{id:{int}},\\{id:{int}}], exists")
    public void project_with_project_title_project_completed_false_project_active_false_project_description_project_tasks_exists(String string, String string2, Integer int1, Integer int2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
     
    @Then("nothing will change")
    public void nothing_will_change() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
     
    @Given("the todo with todo_id: {int}, todo_title: {string}, todo_doneStatus: false, todo_description: {string}, does not exist")
    public void the_todo_with_todo_id_todo_title_todo_done_status_false_todo_description_does_not_exist(Integer int1, String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
     
    @Then("the todo should not exist in the project's project_tasks field")
    public void the_todo_should_not_exist_in_the_project_s_project_tasks_field() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
     
    @Then("an error message should be displayed")
    public void an_error_message_should_be_displayed() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

}

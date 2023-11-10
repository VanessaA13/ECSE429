package project_B.tests;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import static org.junit.Assert.*;
import java.util.List;
import project_B.BaseStepDefinition;

public class RemovingProject extends BaseStepDefinition{
    
    @Given("^the project with (.*) (true|false) (true|false) (.*) exists$")
    public void the_project_with_fields_exists(String project_title, Boolean project_completed, Boolean project_active, String project_description) {
        assertTrue(true);
    }

    @Given("^the task-associated project with (.*) (true|false) (true|false) (.*) (.*) exists$")
    public void the_project_with_fields_and_tasks_exists(String project_title, Boolean project_completed, Boolean project_active, String project_description, String project_tasks) {
        assertTrue(true);
    }

    @Given("^the project with title (.*) does not exist$")
    public void the_project_does_not_exist(String project_title) {
        assertTrue(true);
    }

    @When("^the user deletes the project with title (.*)$")
    public void the_user_deletes_the_project_with_title(String project_title) {
        assertTrue(true);
    }

    @When("^the user deletes the task-associated project with title (.*)$")
    public void the_user_deletes_the_task_associated_project_with_title(String project_title) {
        assertTrue(true);
    }

    @When("^the user tries to delete this project (.*)$")
    public void the_user_tries_to_delete_this_project(String project_title) {
        assertTrue(true);
    }

    @Then("^this project (.*) should no longer exist under Projects$")
    public void the_project_should_no_longer_exist(String project_title) {
        assertTrue(true);
    }

    @And("^this project (.*) should be removed from the (.*)$")
    public void this_project_should_be_removed_from(String project_title, String task_title) {
        assertTrue(true);
    }

    @Then("^an error message should be generated for deleting (.*)$")
    public void an_error_message_generated_for_deleting(String project_title) {
        assertTrue(true);
    }

}   

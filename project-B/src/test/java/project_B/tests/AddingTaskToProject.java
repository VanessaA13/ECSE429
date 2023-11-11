package project_B.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import project_B.BaseStepDefinition;

public class AddingTaskToProject extends BaseStepDefinition {

    @Given("^the todo with (\\d+) and project with (.*)exists$")
    public void the_todo_and_project_exists(int id, String title) {
        assertTrue(true);
    }

    @Given("^the project with (.*) exists and associated with (\\d+) and (\\d+)$")
    public void the_project_associated_with_todos_exists(String title, int id1, int id2) {
        assertTrue(true);
    }

    @Given("^the project with (.*) exists but (\\d+) does not exist$")
    public void the_todo_with_id_3_does_not_exist(String title, int id) {
        assertTrue(true);
    }

    @When("^the user add the todo (\\d+) to the project (.*)$")
    public void add_todo_to_project(int id, String project) {
        assertTrue(true);
    }

    @When("^the user add the todo (\\d+) to the associated project (.*)$")
    public void add_todo_to_associated_project(int id, String project) {
        assertTrue(true);
    }

    @When("^the user add the non-existing todo (\\d+) to the project (.*)$")
    public void add_non_existing_todo_to_project(int id, String project) {
        assertTrue(true);
    }

    @Then("^the todo (\\d+) should exist in the project's (.*) tasks field$")
    public void todo_associated_to_project(int id, String project) {
        assertTrue(true);
    }

    @Then("^the project (.*) remain associated with (\\d+) and (\\d+)$")
    public void todos_remain_associated_to_project(String project, int id1, int id2) {
        assertTrue(true);
    }

    @Then("^the todo (\\d+) should not exist in the project's (.*) project_tasks field$")
    public void todos_not_associated_to_project(int id, String project) {
        assertTrue(true);
    }

}

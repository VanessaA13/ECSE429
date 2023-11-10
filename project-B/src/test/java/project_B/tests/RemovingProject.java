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
        Unirest.post("/projects")
        .body("{\n\"title\":\"" + project_title + "\",\n\"description\":\"" + project_description + "\",\n\"active\":" + project_active + ",\n\"completed\":" + project_completed + "\n}")
        .asJson();
        JSONObject response=findProjectByName(project_title);
        assertNotNull(response);
        assertEquals(project_completed+"", response.getString("completed"));
        assertEquals(project_active+"", response.getString("active"));
        assertEquals(project_description, response.getString("description"));
        assertFalse(response.has("tasks"));
    }

    @Given("^the task-associated project with (.*) (true|false) (true|false) (.*) (.*) exists$")
    public void the_project_with_fields_and_tasks_exists(String project_title, Boolean project_completed, Boolean project_active, String project_description, String project_tasks) {
        Unirest.post("/projects")
        .body("{\n" +
            "\"title\":\"" + project_title + "\",\n" +
            "\"completed\":" + project_completed + ",\n" +
            "\"active\":" + project_active + ",\n" +
            "\"description\":\"" + project_description + "\",\n" +
            "\"tasks\":[" + project_tasks + "]\n" +"}").asJson();
        JSONObject response=findProjectByName(project_title);
        assertNotNull(response);
        assertEquals(project_completed+"", response.getString("completed"));
        assertEquals(project_active+"", response.getString("active"));
        assertEquals(project_description, response.getString("description"));
        assertEquals(project_tasks, response.getJSONArray("tasks").toString());
    }

    @Given("^the project with title (.*) does not exist$")
    public void the_project_does_not_exist(String project_title) {
        JSONObject response=findProjectByName(project_title);
        assertNull(response);
    }

    @When("^the user deletes the project with title (.*)$")
    public void the_user_deletes_the_project_with_title(String project_title) {
        JSONObject response=findProjectByName(project_title);
        Unirest.delete("/projects/"+response.getString("id")).header("accept", "application/json").asJson();
        assertTrue(true);
    }

    @When("^the user deletes the task-associated project with title (.*)$")
    public void the_user_deletes_the_task_associated_project_with_title(String project_title) {
        JSONObject response=findProjectByName(project_title);
        Unirest.delete("/projects/"+response.getString("id")).header("accept", "application/json").asJson();
        assertTrue(true);
    }

    @When("^the user tries to delete this project (.*)$")
    public void the_user_tries_to_delete_this_project(String project_title) {
        assertTrue(true);
    }

    @Then("^this project (.*) should no longer exist under Projects$")
    public void the_project_should_no_longer_exist(String project_title) {
        JSONObject response=findProjectByName(project_title);
        assertNull(response);
    }

    @And("^this project (.*) should be removed from task (\\d+)$")
    public void this_project_should_be_removed_from(int project_id, int task_id) {
        JSONObject response = Unirest.get("/todos/"+(""+task_id)).asJson().getBody().getObject();
        JSONArray target_tasksof=(JSONArray) ((JSONObject)(response.getJSONArray("todos").get(0))).getJSONArray("tasksof");
        boolean isRemoved=true;
        for(int i=0;i<target_tasksof.length();i++){
            if(((JSONObject)target_tasksof.get(i)).getString("id")==(""+project_id)){
                isRemoved=false;
            }
        }
        assertTrue(isRemoved);
    }

    @Then("^an error message should be generated for deleting (.*)$")
    public void an_error_message_generated_for_deleting(int project_id) {
        HttpResponse<JsonNode> response = Unirest.delete("/projects/").header("accept", "application/json").asJson();
        assertTrue(response.getStatus()>=300);
    }

}   

package project_B.tests;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import static org.junit.Assert.*;
import java.util.List;
import project_B.BaseStepDefinition;


public class CreatingProjectWithFields extends BaseStepDefinition {

    @Given("^the todo exists$")
    public void the_todo_exists(DataTable datatable) {
        List<List<String>> rows = datatable.asLists(String.class);
        List<String> task_1=rows.get(1);
        JSONObject response = Unirest.get("/todos/1").asJson().getBody().getObject();
        JSONObject task_1_response=response.getJSONArray("todos").getJSONObject(0);
        assertNotNull(task_1_response);
        assertEquals(task_1_response.getString("title"), task_1.get(1));
        assertEquals(task_1_response.getString("doneStatus"), task_1.get(2).toString());
        assertEquals(task_1_response.getString("description"), (task_1.get(3) != null) ? task_1.get(3):"");
    }
    
    @When("^the user creates project without tasks (.*) (true|false) (true|false) (.*)$")
    public void the_user_creates_project_without_tasks(String project_title, Boolean project_completed, Boolean project_active, String project_description) {
        Unirest.post("/projects")
        .body("{\n\"title\":\"" + project_title + "\",\n\"description\":\"" + project_description + "\",\n\"active\":" + project_active + ",\n\"completed\":" + project_completed + "\n}")
        .asJson();
        JSONObject response=findProjectByName(project_title);
        assertNotNull(response);
    }

    @When("^the user creates project with task (.*) (true|false) (true|false) (.*) (.*)$")
    public void the_user_creates_project_with_task(String project_title, Boolean project_completed, Boolean project_active, String project_description, String project_tasks) {
        Unirest.post("/projects")
        .body("{\n" +
            "\"title\":\"" + project_title + "\",\n" +
            "\"completed\":" + project_completed + ",\n" +
            "\"active\":" + project_active + ",\n" +
            "\"description\":\"" + project_description + "\",\n" +
            "\"tasks\":[" + project_tasks + "]\n" +"}").asJson();
        JSONObject response=findProjectByName(project_title);
        assertNotNull(response);
    }

    @When("^the user creates project with non-existing task (.*) (true|false) (true|false) (.*) (.*)$")
    public void the_user_creates_project_with_non_existing_task(String project_title, Boolean project_completed, Boolean project_active, String project_description, String project_tasks) {
        Unirest.post("/projects")
        .body("{\n" +
            "\"title\":\"" + project_title + "\",\n" +
            "\"completed\":" + project_completed + ",\n" +
            "\"active\":" + project_active + ",\n" +
            "\"description\":\"" + project_description + "\",\n" +
            "\"tasks\":[" + project_tasks + "]\n" +"}").asJson();
        assertTrue(true);
    }
    
    @Then("^the new project with specific fields (.*) (true|false) (true|false) (.*) should exist under Projects$")
    public void the_new_project_with_specific_fields_should_exist_under_projects(String project_title, Boolean project_completed, Boolean project_active, String project_description) {
        JSONObject response=findProjectByName(project_title);
        assertNotNull(response);
        assertEquals(project_completed+"", response.getString("completed"));
        assertEquals(project_active+"", response.getString("active"));
        assertEquals(project_description, response.getString("description"));
        assertFalse(response.has("tasks"));
    }

    @Then("^the new project with specific tasks and fields (.*) (true|false) (true|false) (.*) (.*) should exist under Projects$")
    public void the_new_project_with_specific_fields_and_tasks_should_exist_under_projects(String project_title, Boolean project_completed, Boolean project_active, String project_description, String project_tasks) {
        JSONObject response=findProjectByName(project_title);
        assertNotNull(response);
        assertEquals(project_completed+"", response.getString("completed"));
        assertEquals(project_active+"", response.getString("active"));
        assertEquals(project_description, response.getString("description"));
        assertEquals(project_tasks, response.getJSONArray("tasks").toString());
    }

    
    @Given("^the following todo with todo_id 3 does not exist$")
    public void the_todo_with_todo_id_does_not_exist(DataTable dataTable) {
        HttpResponse<JsonNode> response = Unirest.get("/todos/3").header("accept", "application/json").asJson();
        assertTrue(response.getStatus()>=300);
    }
    
    @Then("^the new project (.*) with specific fields should not exist under Projects and error should be displayed$")
    public void the_new_project_with_specific_fields_should_not_exist_under_projects(String project_title) {
        JSONObject response=findProjectByName(project_title);
        assertNull(response);
    }

}

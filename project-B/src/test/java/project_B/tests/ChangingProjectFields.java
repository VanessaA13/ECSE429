package project_B.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;
import project_B.BaseStepDefinition;

public class ChangingProjectFields extends BaseStepDefinition {
    
    @Given("^the following project exists$")
    public void the_project_with_fields_exists(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        List<String> given_project=rows.get(1);
        Unirest.post("/projects")
        .body("{\n\"title\":\"" + given_project.get(0) + "\",\n\"description\":\"" + given_project.get(3) + "\",\n\"active\":" + given_project.get(2) + ",\n\"completed\":" + given_project.get(1) + "\n}")
        .asJson();
        JSONObject response = findProjectByName(given_project.get(0));
        assertNotNull(response);
        assertEquals(response.getString("completed"), given_project.get(1)+"");
        assertEquals(response.getString("active"), given_project.get(2)+"");
        assertEquals(response.getString("description"), (given_project.get(3) != null) ? given_project.get(3):"");
    }

    @Given("^the following project does not exist$")
    public void the_project_with_fields_does_not_exist(DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        List<String> given_project=rows.get(1);
        JSONObject response = findProjectByName(given_project.get(0));
        assertNull(response);
    }

    @When("^the user set the project (.*) with (.*) (true|false) (true|false) (.*)$")
    public void the_user_set_the_project_with(String old_title, String title, Boolean completed, Boolean active, String description) {
        JSONObject response = findProjectByName(old_title);
        assertNotNull(response);
        String id=response.getString("id");
        Unirest.put("/projects/"+id)
        .body("{\n\"title\":\"" + title + "\",\n\"description\":\"" + description + "\",\n\"active\":" + active + ",\n\"completed\":" + completed + "\n}")
        .asJson();
        JSONObject get_response=Unirest.get("/projects/"+id).asJson().getBody().getObject();
        assertNotNull(get_response);
        JSONObject target=(JSONObject) get_response.getJSONArray("projects").get(0);
        assertEquals(target.getString("title"), title);
        assertEquals(target.getString("completed"), completed+"");
        assertEquals(target.getString("active"), active+"");
        assertEquals(target.getString("description"), description+"");
    }

    @When("^the user set this project (.*) with (.*) (true|false) (true|false) (.*)$")
    public void the_user_set_this_project_with(String old_title, String title, Boolean completed, Boolean active, String description) {
        JSONObject response = findProjectByName(old_title);
        assertNotNull(response);
        String id=response.getString("id");
        Unirest.put("/projects/"+id)
        .body("{\n\"title\":\"" + title + "\",\n\"description\":\"" + description + "\",\n\"active\":" + active + ",\n\"completed\":" + completed + "\n}")
        .asJson();
        JSONObject get_response=Unirest.get("/projects/"+id).asJson().getBody().getObject();
        assertNotNull(get_response);
        JSONObject target=(JSONObject) get_response.getJSONArray("projects").get(0);
        assertEquals(target.getString("title"), title);
        assertEquals(target.getString("completed"), completed+"");
        assertEquals(target.getString("active"), active+"");
        assertEquals(target.getString("description"), description+"");
    }

    @When("^the user tries to set the non-existing project (\\d+) with (.*) (true|false) (true|false) (.*)$")
    public void the_user_set_the_non_existing_project_with(int id, String title, Boolean completed, Boolean active, String description) {
        HttpResponse<JsonNode> response=Unirest.put("/projects/"+id)
        .body("{\n\"title\":\"" + title + "\",\n\"description\":\"" + description + "\",\n\"active\":" + active + ",\n\"completed\":" + completed + "\n}")
        .header("accept", "application/json").asJson();
        assertTrue(response.getStatus()>=300);
    }

    @Then("^the project's non-tasks fields should be updated to (.*) (true|false) (true|false) (.*)$")
    public void the_projects_non_tasks_fields_updated(String title, Boolean completed, Boolean active, String description) {
        JSONObject response = findProjectByName(title);
        assertNotNull(response);
        assertEquals(response.getString("completed"), completed+"");
        assertEquals(response.getString("active"), active+"");
        assertEquals(response.getString("description"), description+"");
    }

    @Then("^the project's non-tasks fields should remain as (.*) (true|false) (true|false) (.*)$")
    public void the_projects_non_tasks_fields_remain(String title, Boolean completed, Boolean active, String description) {
        JSONObject response = findProjectByName(title);
        assertNotNull(response);
        assertEquals(response.getString("completed"), completed+"");
        assertEquals(response.getString("active"), active+"");
        assertEquals(response.getString("description"), description+"");
    }

    @Then("^the project (\\d+) with modified non-tasks fields should not exist under Projects and error should be displayed$")
    public void the_project_does_not_exist(int id) {
        HttpResponse<JsonNode> response=Unirest.get("/projects/"+id).header("accept", "application/json").asJson();
        assertTrue(response.getStatus()>=300);
    }

}

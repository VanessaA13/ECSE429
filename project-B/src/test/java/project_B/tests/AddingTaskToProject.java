package project_B.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;
import project_B.BaseStepDefinition;

public class AddingTaskToProject extends BaseStepDefinition {

    @Given("^the todo with (\\d+) and project with (.*) exists$")
    public void the_todo_and_project_exists(int id, String title) {
        Unirest.post("/projects").body("{\n" + "\"title\":\"" + title + "\"\n" +"}").asJson();
        JSONObject todo = Unirest.get("/todos/"+(""+id)).asJson().getBody().getObject();
        assertNotNull(todo);
        JSONObject project = findProjectByName(title);
        assertNotNull(project);
    }

    @Given("^the project with (.*) exists and associated with (\\d+) and (\\d+)$")
    public void the_project_associated_with_todos_exists(String title, int id1, int id2) {
        JSONObject project = findProjectByName(title);
        assertNotNull(project);
        JSONArray tasks=project.getJSONArray("tasks");
        boolean id1Associated=false;
        boolean id2Associated=false;
        for(int i=0;i<tasks.length();i++){
            if(((JSONObject)tasks.get(i)).getString("id").equals(id1+"")){
                id1Associated=true;
            }
            if(((JSONObject)tasks.get(i)).getString("id").equals(id2+"")){
                id2Associated=true;
            }
        }
        assertTrue(id1Associated);
        assertTrue(id2Associated);
    }

    @Given("^the project with (.*) exists but (\\d+) does not exist$")
    public void the_todo_with_id_3_does_not_exist(String title, int id) {
        Unirest.post("/projects").body("{\n" + "\"title\":\"" + title + "\"\n" +"}").asJson();
        HttpResponse<JsonNode> todo = Unirest.get("/todos/"+(""+id)).asJson();
        assertTrue(todo.getStatus()>=300);
        JSONObject project = findProjectByName(title);
        assertNotNull(project);
    }

    @When("^the user add the todo (\\d+) to the project (.*)$")
    public void add_todo_to_project(int id, String project) {
        JSONObject response = findProjectByName(project);
        assertNotNull(response);
        String project_id=response.getString("id");
        Unirest.post("/projects/"+project_id).body("{\"tasks\":{\"id\":\""+id+"\"}}").asJson();
        assertTrue(true);
    }

    @When("^the user add the todo (\\d+) to the associated project (.*)$")
    public void add_todo_to_associated_project(int id, String project) {
        JSONObject response = findProjectByName(project);
        assertNotNull(response);
        String project_id=response.getString("id");
        Unirest.post("/projects/"+project_id).body("{\"tasks\":{\"id\":\""+id+"\"}}").asJson();
        assertTrue(true);
    }

    @When("^the user add the non-existing todo (\\d+) to the project (.*)$")
    public void add_non_existing_todo_to_project(int id, String project) {
        JSONObject response = findProjectByName(project);
        assertNotNull(response);
        String project_id=response.getString("id");
        Unirest.post("/projects/"+project_id).body("{\"tasks\":{\"id\":\""+id+"\"}}").asJson();
        assertTrue(true);
    }

    @Then("^the todo (\\d+) should exist in the project's (.*) tasks field$")
    public void todo_associated_to_project(int id, String project) {
        JSONObject JSON_project = findProjectByName(project);
        assertNotNull(JSON_project);
        JSONArray tasks=JSON_project.getJSONArray("tasks");
        boolean idAssociated=false;
        for(int i=0;i<tasks.length();i++){
            if(((JSONObject)tasks.get(i)).getString("id").equals(id+"")){
                idAssociated=true;
            }
        }
        assertTrue(idAssociated);
    }

    @Then("^the project (.*) remain associated with (\\d+) and (\\d+)$")
    public void todos_remain_associated_to_project(String title, int id1, int id2) {
        JSONObject project = findProjectByName(title);
        assertNotNull(project);
        JSONArray tasks=project.getJSONArray("tasks");
        boolean id1Associated=false;
        boolean id2Associated=false;
        for(int i=0;i<tasks.length();i++){
            if(((JSONObject)tasks.get(i)).getString("id").equals(id1+"")){
                id1Associated=true;
            }
            if(((JSONObject)tasks.get(i)).getString("id").equals(id2+"")){
                id2Associated=true;
            }
        }
        assertTrue(id1Associated);
        assertTrue(id2Associated);
    }

    @Then("^the todo (\\d+) should not exist in the project's (.*) project_tasks field and error should be displayed$")
    public void todos_not_associated_to_project(int id, String project) {
        JSONObject JSON_project = findProjectByName(project);
        assertNotNull(JSON_project);
        assertFalse(JSON_project.has("tasks"));
    }

}

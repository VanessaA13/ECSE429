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

public class RemovingTaskFromProject extends BaseStepDefinition{
    
    @Given("^the project with (.*) exists and is associated with (\\d+)$")
    public void the_project_associated_to_task(String project, int task) {
        Unirest.post("/projects").body("{" + "\"title\":\"" + project + "\","+"\"tasks\":[{"+"\"id\":"+"\""+task+"\"" +"}]"+"}").asJson();
        JSONObject JSON_project = findProjectByName(project);
        assertNotNull(JSON_project);
        JSONArray tasks=JSON_project.getJSONArray("tasks");
        boolean idAssociated=false;
        for(int i=0;i<tasks.length();i++){
            if(((JSONObject)tasks.get(i)).getString("id").equals(task+"")){
                idAssociated=true;
            }
        }
        assertTrue(idAssociated);
    }

    @Given("^the project with (.*) exists and is associated with (\\d+) and (\\d+)$")
    public void the_project_associated_with_tasks(String title, int task1, int task2) {
        JSONObject project = findProjectByName(title);
        assertNotNull(project);
        JSONArray tasks=project.getJSONArray("tasks");
        boolean id1Associated=false;
        boolean id2Associated=false;
        for(int i=0;i<tasks.length();i++){
            if(((JSONObject)tasks.get(i)).getString("id").equals(task1+"")){
                id1Associated=true;
            }
            if(((JSONObject)tasks.get(i)).getString("id").equals(task2+"")){
                id2Associated=true;
            }
        }
        assertTrue(id1Associated);
        assertTrue(id2Associated);
    }

    @Given("^the project with (.*) exists and unassociated with (\\d+)$")
    public void the_project_associated_with_tasks(String project, int task) {
        Unirest.post("/projects").body("{\n" + "\"title\":\"" + project + "\"\n" +"}").asJson();
        JSONObject JSON_project = findProjectByName(project);
        assertNotNull(JSON_project);
        assertFalse(JSON_project.has("tasks"));
    }
    
    @When("^the user removes (\\d+) from the project (.*)$")
    public void remove_task_from_project(int task, String project) {
        JSONObject response = findProjectByName(project);
        assertNotNull(response);
        String project_id=response.getString("id");
        Unirest.delete("/projects/"+project_id+"/tasks/"+task).header("accept", "application/json").asJson();
        assertTrue(true);
    }

    @When("^the user removes (\\d+) from project (.*) associated to another task$")
    public void remove_task_from_project_associated_to_another_task(int task, String project) {
        JSONObject response = findProjectByName(project);
        assertNotNull(response);
        String project_id=response.getString("id");
        Unirest.delete("/projects/"+project_id+"/tasks/"+task).header("accept", "application/json").asJson();
        assertTrue(true);
    }

    @When("^the user removes (\\d+) from the unassociated project (.*)$")
    public void remove_task_from_unassociated_project(int task, String project) {
        JSONObject response = findProjectByName(project);
        assertNotNull(response);
        String project_id=response.getString("id");
        HttpResponse<JsonNode> delete_response=Unirest.delete("/projects/"+project_id+"/tasks/"+task).header("accept", "application/json").asJson();
        assertTrue(delete_response.getStatus()>=300);
    }

    @Then("^the todo (\\d+) should not exist under the project's (.*) project_tasks field$")
    public void todo_should_not_exist_under_project(int task, String project) {
        JSONObject JSON_project = findProjectByName(project);
        System.out.println(JSON_project.toString());
        assertNotNull(JSON_project);
        assertFalse(JSON_project.has("tasks"));
    }

    @Then("^only the todo (\\d+) should exist under the project's (.*) project_tasks field$")
    public void only_another_todo_exist_under_project(int task, String project) {
        JSONObject JSON_project = findProjectByName(project);
        assertNotNull(JSON_project);
        JSONArray tasks=JSON_project.getJSONArray("tasks");
        boolean idAssociated=false;
        for(int i=0;i<tasks.length();i++){
            if(((JSONObject)tasks.get(i)).getString("id").equals(task+"")){
                idAssociated=true;
            }
        }
        assertTrue(idAssociated);
    }
    
    @Then("^the todo (\\d+) should not exist under the project's (.*) project_tasks field and error should be displayed$")
    public void todo_should_not_exist_under_project_error(int task, String project) {
        JSONObject JSON_project = findProjectByName(project);
        System.out.println(JSON_project.toString());
        assertNotNull(JSON_project);
        assertFalse(JSON_project.has("tasks"));
    }

}

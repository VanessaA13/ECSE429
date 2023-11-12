package project_B.tests;
import io.cucumber.java.en.*;
import io.cucumber.java.mk_latn.No;
import io.cucumber.datatable.DataTable;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import static org.junit.Assert.*;

import java.util.List;

import project_B.BaseStepDefinition;

public class ModifyTodosDescription extends BaseStepDefinition {

    @And("the following todos with description field exist")
    public void the_following_todos_with_description_field_exist(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            boolean doneStatus = (boolean) Boolean.parseBoolean(columns.get(1));
            if(!title) {
                Unirest.post("/todos")
                        .body("{\n\"doneStatus\":" + doneStatus + ",\n  \"description\":\""
                                + columns.get(2) + "\",\n  \"title\":\"" + columns.get(0) + "\"\n}")
                        .asJson();
            }
            title = false;
        }
    }

    @When("a user attempts to modify the description of todos")
    public void a_user_attempts_to_modify_the_description_of_todos(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        JSONObject todo;
        for (List<String> columns : rows) {
            // ignore title row
            boolean doneStatus = (Boolean) Boolean.parseBoolean(columns.get(1));
            if(!title) {
                todo = findTodoByName(columns.get(0));
                Unirest.post("/todos/" + todo.getInt("id"))
                        .body("{\n\"doneStatus\":" + doneStatus + ",\n  \"description\":\""
                                + columns.get(2) + "\",\n  \"title\":\"" + todo.getString("title") + "\"\n}")
                        .asJson();
            }
            title = false;
        }
    }

    @Then("the todo's description field should be updated")
    public void the_todo_s_description_field_should_be_updated(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        JSONObject todo;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                todo = findTodoByName(columns.get(0));
                if (todo.getString("description").equals("null")) {
                    assertEquals(null, columns.get(2));
                    break;
                }
                assertEquals(columns.get(2), todo.getString("description"));
            }
            title = false;
        }
    }

    @When("^a user sends an incorrect url (.*) to modify the description of todos$")
    public void a_user_sends_an_incorrect_url_to_modify_the_description_of_todos(String url){
        try {        
            response = Unirest.post(url)
                            .body("{\n\"doneStatus\":" + ",\n  \"description\":\""
                                     + "\",\n  \"title\":\"" + "\"\n}")
                            .asJson().getBody().getObject();
        } catch (Exception e) {
            
        }                  
            
    }
    

    @Then("the todo's description field should not be updated")
    public void the_todo_s_description_field_should_not_be_updated(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        JSONObject todo;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                todo = findTodoByName(columns.get(0));
                assertEquals(columns.get(2), todo.getString("description"));
            }
            title = false;
        }
    }

    @Then("^the user should receive an error message (.*)$")
    public void the_user_should_receive_an_error_message(String message){
        assertTrue(response.toString().contains(message));
    }
    
}

package project_B.tests;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import static org.junit.Assert.*;

import java.util.List;
import project_B.BaseStepDefinition;

public class DeleteATodo extends BaseStepDefinition {

    @And("the following todo instances exist in the system")
    public void the_following_todo_instances_exist_in_the_system(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                Unirest.post("/todos")
                        .body("{\n\"doneStatus\":\"" + columns.get(1) + "\",\n  \"description\":\""
                                + columns.get(2) + "\",\n  \"title\":\"" + columns.get(0) + "\"\n}")
                        .asJson();
            }
            title = false;
        }
    }

    @When("a user deletes a todo with a specfic id")
    public void a_user_deletes_a_todo_with_a_specfic_id(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                Unirest.delete("/todos/" + columns.get(0))
                        .asJson();
            }
            title = false;
        }
    }

    @Then("the following todos would not exist in the system")
    public void the_following_todos_shall_not_exist_in_the_system(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                JSONObject todo = findTodoByName(columns.get(0));
                assertNull(todo);
            }
            title = false;
        }
    }

    @When("a user delete a todo with a id that does not exist")
    public void a_user_delete_a_todo_with_a_id_that_does_not_exist(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                Unirest.delete("/todos/" + columns.get(0))
                        .asJson();
            }
            title = false;
        }
    }

    @Then("the following todos may not exist in the system")
    public void the_following_todos_may_not_exist_in_the_system(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            JSONObject todo = findTodoByName(columns.get(0));
            assertNull(todo);
            title = false;
        }
    }
    @Then("the server shall raise an error")
    public void the_server_shall_raise_an_error(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                assertEquals(columns.get(1), ("Could not find any instances with todos/" + columns.get(0)));
                
            }
            title = false;
        }
    }
    
}

package project_B.tests;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import static org.junit.Assert.*;

import java.util.List;
import project_B.BaseStepDefinition;
import project_B.BaseTest;

public class AddATodo extends BaseStepDefinition{


    @When("a user create a todo with a title, a doneStatus a description:")
    public void the_following_todos_are_created(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            boolean doneStatus = (boolean) Boolean.parseBoolean(columns.get(1));
            if(!title) {
                response = (Unirest.post("/todos")
                            .body("{\n\"doneStatus\":" + doneStatus + ",\n  \"description\":\""
                                    + columns.get(2) + "\",\n  \"title\":\"" + columns.get(0) + "\"\n}")
                            .asJson().getBody().getObject());
            }
            title = false;
        }
        
    }

    @Then("the following todos shall exist in the system:")
    public void the_following_todos_shall_exist_in_the_system(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                JSONObject todo = findTodoByName(columns.get(0));
                assertNotNull(todo);
                assertEquals(todo.getString("title"), columns.get(0));
                assertEquals(todo.getString("doneStatus"), columns.get(1));
                assertEquals(todo.getString("description"), columns.get(2));
            }
            title = false;
        }
    }

    @When("a user create a todo with a title, a doneStatus an empty description")
    public void a_user_create_a_todo_with_a_title_a_doneStatus_an_empty_description(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            boolean doneStatus = (boolean) Boolean.parseBoolean(columns.get(1));
            if(!title) {
                response = Unirest.post("/todos")
                        .body("{\n\"doneStatus\":" + doneStatus + ",\n  \"description\":\""
                                + columns.get(2) + "\",\n  \"title\":\"" + columns.get(0) + "\"\n}")
                        .asJson().getBody().getObject();
            }
            title = false;
        }
    }

    @Then("the following todos shall still exist in the system")
    public void the_following_todos_shall_still_exist_in_the_system(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                JSONObject todo = findTodoByName(columns.get(0));
                assertNotNull(todo);
                assertEquals(todo.getString("title"), columns.get(0));
                assertEquals(todo.getString("doneStatus"), columns.get(1));
                assertEquals(todo.getString("description"), String.valueOf(columns.get(2)));
            }
            title = false;
        }
    }

    @When("a user create a todo with an empty title")
    public void a_user_create_a_todo_with_an_empty_title(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                Unirest.post("/todos")
                        .body("{\n\"doneStatus\":\"" + Boolean.parseBoolean(columns.get(1)) + "\",\n  \"description\":\""
                                + columns.get(2) + "\",\n  \"title\":\"" + columns.get(0) + "\"\n}")
                        .asJson();
            }
            title = false;
        }
    }

    @Then("the following todos with the title shall not exist")
    public void the_following_todos_shall_not_exist(DataTable table) {
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

    @Then("the server will raise an error")
    public void the_server_will_raise_an_error(DataTable table) {
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
           // ignore title row
           if(!title) {
               assertEquals(columns.get(3), "Failed Validation: title : can not be empty");
           }
            title = false;
        }
       
    }


}


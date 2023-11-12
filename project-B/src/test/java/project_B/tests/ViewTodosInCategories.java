package project_B.tests;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import static org.junit.Assert.*;

import java.util.List;

import project_B.BaseStepDefinition;

public class ViewTodosInCategories extends BaseStepDefinition {
    
    @And("the following todos are in the system")
    public void the_following_todos_are_in_the_system(DataTable table){
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

    @And("the following categories are in the system")
    public void the_following_categories_are_in_the_system(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                Unirest.post("/categories")
                        .body("{\n\"title\":\"" + columns.get(0) + "\"\n}")
                        .asJson();
            }
            title = false;
        }
    }

    @When("a user requests to view all todos in a category")
    public void a_user_requests_to_view_all_todos_in_a_category(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        JSONObject todo;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                JSONObject category = findCategoryByName(columns.get(3));
                todo = findTodoByName(columns.get(0));
                try {
                    Unirest.post("/categories/" + category.getString("id") + "/todos")
                    .body("{\n\"id\":\"" + todo.getString("id") + "\"\n}")
                    .asJson();
                } catch (NullPointerException e) {
                    break;
                }
            }

            title = false;
        }
    }

    @Then("the system will return all todos in that category")
    public void the_system_will_return_all_todos_in_that_category(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                JSONObject category = findCategoryByName(columns.get(3));
                JSONObject todo = findTodoByName(columns.get(0));
                JSONObject response = Unirest.get("/categories/" + category.getString("id") + "/todos").asJson().getBody().getObject();
                JSONArray todos = response.getJSONArray("todos");
                boolean found = false;
                if (todos.length() == 0) {
                    assertEquals(todo, null);
                    break;
                }
                for (Object t : todos) {
                    JSONObject t1 = (JSONObject) t;
                    if (t1.getString("id").equals(todo.getString("id"))) {
                        found = true;
                        break;
                    }
                }
                assertTrue(found);
            }
            title = false;
        }
    }


    @When("^a user inputs an incorrect endpoint (.*) to request to view all todos in a category$")
    public void a_user_inputs_an_incorrect_endpoint_to_request_to_view_all_todos_in_a_category(String endpoint){
        try {
            response = Unirest.get(endpoint).asJson().getBody().getObject();
        } catch (Exception e) {

        }
    }      

    @Then("^the system will return an error (.*)$")
    public void the_system_will_return_an_error_and_not_found_status_code(String error){
        assertTrue(response.toString().contains(error));
    }

}

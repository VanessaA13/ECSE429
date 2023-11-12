package project_B.tests;

import io.cucumber.java.en.*;
import io.cucumber.datatable.DataTable;
import kong.unirest.Unirest;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONException;
import kong.unirest.json.JSONObject;

import static org.junit.Assert.*;

import java.util.List;

import project_B.BaseStepDefinition;

public class DeleteTodoAndCategory extends BaseStepDefinition {
    
    @And("the following todos, categories and relationships in these categories exists:")
    public void the_following_todos_in_these_categories_exists(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        for (List<String> columns : rows) {
            // ignore title row
            boolean doneStatus = (boolean) Boolean.parseBoolean(columns.get(1));
            if(!title) {
                Unirest.post("/todos")
                        .body("{\n\"doneStatus\":" + doneStatus + ",\n  \"description\":\""
                                 + "\",\n  \"title\":\"" + columns.get(0) + "\"\n}")
                        .asJson();
                Unirest.post("/categories" )
                        .body("{\n\"title\":\"" + columns.get(2) + "\"\n}")
                        .asJson();
                JSONObject todo = findTodoByName(columns.get(0));
                JSONObject category = findCategoryByName(columns.get(2));

                Unirest.post("/todos/" + todo.getString("id") + "/categories")
                        .body("{\n\"id\":\"" + category.getString("id") + "\"\n}")
                        .asJson();
            }
            title = false;
        }
    }

    @When("a user deletes a relationship between a todo and a category")
    public void a_user_deletes_a_relationship_between_a_todo_and_a_category(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        JSONObject todo;
        JSONObject category;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                todo = findTodoByName(columns.get(0));
                category = findCategoryByName(columns.get(2));
                Unirest.delete("/todos/" + todo.getString("id") + "/categories/" + category.getString("id"))
                        .asJson();
            }
            title = false;
        }
    }

    @Then("the relationship between the todo and the category no longer exists")
    public void the_relationship_between_the_todo_and_the_category_no_longer_exists(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        JSONObject todo;
        JSONObject category;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                todo = findTodoByName(columns.get(0));
                category = findCategoryByName(columns.get(2));
                String response = Unirest.get("/todos/" + todo.getString("id") + "/categories")
                        .asJson().getBody().toString();
                System.out.println("catId:");
                System.out.println(response);
                boolean found = false;
                JSONObject jsonObj = new JSONObject(response);
                JSONArray categories = jsonObj.getJSONArray("categories");
                for (int i = 0; i < categories.length(); i++) {
                    JSONObject cat = categories.getJSONObject(i);
                    String catId = cat.getString("id");
                    /*try { 
                        catId = cat.getString("id");
                    } catch (JSONException e) {
                        catId = cat.toString();
                    }*/
                    
                    if (cat.toString().contains("id") && catId.equals(category.getString("id"))) {
                        found = true;
                    }
                }
                assertFalse(found);
            }
            title = false;
        }
    }

    @When("a user attempts to delete a relationship between a todo and a category when the relationship does not exist")
    public void a_user_attempts_to_delete_a_relationship_between_a_todo_and_a_category_when_the_relationship_does_not_exist(DataTable table){
        List<List<String>> rows = table.asLists(String.class); // instances of todo
        boolean title = true;
        JSONObject todo;
        JSONObject category;
        for (List<String> columns : rows) {
            // ignore title row
            if(!title) {
                todo = findTodoByName(columns.get(0));
                category = findCategoryByName(columns.get(2));
                response = Unirest.delete("/todos/" + todo.getString("id") + "/categories/" + category.getString("id"))
                            .asJson().getBody().getObject();
            }
            title = false;
        }
    }

    @Then("^the error message (.*) is returned$")
    public void the_error_message_is_returned(String message){
        assertEquals(response.getString("errorMessages"), message);
    }
}

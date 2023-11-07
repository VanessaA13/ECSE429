package project_B.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.After;
import io.cucumber.java.Before;


import io.cucumber.datatable.DataTable;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import project_B.BaseStepDefinition;

import java.util.List;

/*
 * author: Habib Jarweh
 */
public class OrganizeTaskByCategory extends BaseStepDefinition{

    @Given("^(.*) is the title of a category on the system$")
    public void selected_title_is_the_title_of_a_category_on_the_system(String selectedTitle) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
    @When("^a POST request is sent with the following info:$")
    public void a_post_request_is_sent_with_the_following_info(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            response = Unirest.post("/todos/" + findIdFromTodoName(columns.get(0)) + "/categories")
                    .body("{\n\"title\":\"" + columns.get(1) +"\"\n}")
                    .asJson().getBody().getObject();
        }
    }
    @Then("^a relationship should be created between task (.*) and category (.*)$")
    public void a_relationship_should_be_created_between_task_and_category(String todoTitle, String categoryTitle) {
        assertTrue(response.toString().contains(categoryTitle));
    }

    @Given("^there is no existing task with title (.*)$")
    public void there_is_no_existing_task_with_title(String todoTitle) {
        assertNull(findTodoByName(todoTitle));
    }

    @Given("^there is no existing category with title (.*)$")
    public void there_is_no_existing_category_with_title(String categoryTitle) {
        assertNull(findCategoryByName(categoryTitle));
    }

    @Given("^there is an existing category with title (.*)$")
    public void there_is_an_existing_category_with_title_category_title(String categoryTitle) {
        assertNotNull(findCategoryByName(categoryTitle));
    }

    @Then("^the API server should respond with an error message (.*)$")
    public void the_api_server_should_respond_with_an_error_message(String msg) {
        assertTrue(response.getJSONArray("errorMessages").get(0).
            toString().contains(msg));
    }

}

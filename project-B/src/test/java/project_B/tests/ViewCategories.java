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
public class ViewCategories extends BaseStepDefinition{

    @Given("^the following categories are registered in the system:$")
    public void the_following_categories_are_registered_in_the_todo_manager_restapi_system(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
    
        boolean firstLine = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!firstLine) {
                Unirest.post("/categories")
                        .body("{\n\"description\":\"" + columns.get(1) + "\",\n  \"title\":\""
                                + columns.get(0) + "\"\n}")
                        .asJson();
            }
            firstLine = false;
        }
    }

    @When("^a GET request is sent to /categories$")
    public void sendGetRequestToCategories() {
        response = Unirest.get("/categories")
        .asJson().getBody().getObject();
    }

    @Then("the response body should contain the following categories:")
    public void verifyResponseContainsCategories(DataTable dataTable) {
        dataTable.asLists().forEach(category -> {
            String title = category.get(0);
            String description = category.get(1);
            assertTrue(response.toString().contains(title));
            assertTrue(response.toString().contains(description));
        });
    }

    @Then("the response body should only contain the original categories:")
    public void verifyResponseContainsOnlyOriginalCategories(DataTable dataTable) {
        dataTable.asLists().forEach(category -> {
            String title = category.get(0);
            String id = category.get(1);
            assertTrue(response.toString().contains(title));
            assertTrue(response.toString().contains(id));
        });
        assertFalse(response.toString().contains("homework"));
        assertFalse(response.toString().contains("assignment"));
    }

    @When("a GET request is sent to /categoriez")
    public void a_get_request_is_sent_to_categoriez() {
        try {
            response = Unirest.get("/categoriez").asJson().getBody().getObject();
        } catch (Exception e) {
        }
    }

    @Then("^the API server should respond with a (.*) Not Found status code$")
    public void the_api_server_should_respond_with_a_not_found_status_code(Integer int1) {
        assertGetStatusCode("/categoriez", int1 );
    }

}

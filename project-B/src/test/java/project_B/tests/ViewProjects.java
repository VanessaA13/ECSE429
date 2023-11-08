package project_B.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;


import io.cucumber.datatable.DataTable;
import kong.unirest.Unirest;

import static org.junit.Assert.*;

import java.util.List;
import project_B.BaseStepDefinition;


/*
 * author: Habib Jarweh
 */
public class ViewProjects extends BaseStepDefinition{

    @Given("^the following projects are registered in the system:$")
    public void the_following_categories_are_registered_in_the_todo_manager_restapi_system(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
    
        boolean firstLine = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!firstLine) {
                Unirest.post("/projects")
                        .body("{\n\"description\":\"" + columns.get(1) + "\",\n  \"title\":\""
                                + columns.get(0) + "\"\n}")
                        .asJson();
            }
            firstLine = false;
        }
    }

    @Then("the response body should contain the following projects:")
    public void verifyResponseContainsCategories(DataTable dataTable) {
        dataTable.asLists().forEach(category -> {
            String title = category.get(0);
            String description = category.get(1);
            assertTrue(response.toString().contains(title));
            assertTrue(response.toString().contains(description));
        });
    }

    @Then("the response body should only contain the original projects:")
    public void verifyResponseContainsOnlyOriginalProjects(DataTable dataTable) {
        dataTable.asLists().forEach(project -> {
            String title = project.get(0);
            String id = project.get(1);
            assertTrue(response.toString().contains(title));
            assertTrue(response.toString().contains(id));
        });
        assertFalse(response.toString().contains("Renovate Living Room"));
        assertFalse(response.toString().contains("Renovate Bathroom"));
    }

}

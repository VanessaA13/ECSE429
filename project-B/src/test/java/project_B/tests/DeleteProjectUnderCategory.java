package project_B.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;


import kong.unirest.Unirest;


import static org.junit.Assert.*;
import java.util.List;
import project_B.BaseStepDefinition;
import io.cucumber.java.DataTableType;
import org.apache.commons.lang3.StringUtils;



/*
 * author: Vanessa Akhras
 */
public class DeleteProjectUnderCategory extends BaseStepDefinition{
    
    @Given("^(.*) is the project that's under the category$")
public void is_the_project_thats_under_the_category(String projectID) {
    
}
@When("a DELETE request is sent with the following info for the category:")
public void a_delete_request_is_sent_with_the_following_info_for_the_category(io.cucumber.datatable.DataTable dataTable) {
    boolean firstLine = true;
    List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            if(!firstLine)
            {
            response = Unirest.delete("/categories/" + columns.get(0) + "/projects/"+ columns.get(4))                
                    .asJson().getBody().getObject();
                

            }
            firstLine = false;
        }
}
@Then("^the relationship is deleted between the with (.*) and the category with (.*)$")
public void the_relationship_is_deleted_between_the_project_and_the_category(String projectID, String categoryTitle) {
    try {
            response = Unirest.get("/categories").asJson().getBody().getObject();
        } catch (Exception e) {
        }

        assertTrue(response.toString().contains(categoryTitle));
    assertFalse(response.toString().contains("\"projects\":[{\"id\":\""+projectID+"\"}]"));

    //assertFalse(response.toString().concrete)
}


}

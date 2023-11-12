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
public class OrganizeProjectTaskByCategory extends BaseStepDefinition{
    @Given("^(.*) is the id of a category$")
public void category_id_is_the_id_of_a_category(String categoryID) {
    
    
}
    @Given("^(.*) is the id of a project$")
public void project_id_is_the_id_of_a_project(String projectID) {
    
    
}
@Given("^(.*) is the title of a project$")
public void project_title_is_the_title_of_a_project(String projectTitle) {
    
    
}
@Given("^(.*) is the completed value of a project$")
public void project_completed_is_the_completed_value_of_a_project(Boolean projectCompleted) {
    
    
}
@Given("^(.*) is the active value of a project$")
public void project_active_is_the_active_value_of_a_project(Boolean projectActive) {
    
    
}
@Given("^(.*) is the description of a project$")
public void project_description_is_the_description_of_a_project(String projectDescription) {
    
    
}
@When("a POST request is sent with the following info for the category and project:")
public void a_post_request_is_sent_with_the_following_info_for_the_category(io.cucumber.datatable.DataTable dataTable) {
    boolean firstLine = true;
    List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            if(!firstLine)
            {
            response = Unirest.post("/categories/" + columns.get(0) + "/projects")
                    .body("{\n\"id\":\"" + columns.get(3) + "\",\n  \"title\":\"" + columns.get(4) +"\",\n  \"completed\":\""+ columns.get(5)+"\",\n  \"active\":\""+ columns.get(6)+"\",\n  \"description\":\""+ columns.get(7)+"\"\n}")
                    .asJson().getBody().getObject();



            }
            firstLine = false;
        }
    
}

    
@Then("^a relationship is formed between a project with (.*) and (.*) and a category with (.*)$")
public void a_relationship_is_formed_between_a_project_and_a_category(String projectID, String projectTitle, String categoryTitle) {
    try {
            response = Unirest.get("/categories").asJson().getBody().getObject();
        } catch (Exception e) {
        }

        
    assertTrue(response.toString().contains(categoryTitle));
    assertTrue(response.toString().contains("\"projects\":[{\"id\":\""+projectID+"\"}]"));
    
    
}


}

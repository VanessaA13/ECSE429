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
public class ModifyCategory extends BaseStepDefinition{
    
@Then("^a category should be modified and have the new (.*), (.*) and (.*)$")
public void a_category_should_be_modified(String categoryTitle, String categoryDescription, String categoryID) {
    try {
            response = Unirest.get("/categories/"+categoryID).asJson().getBody().getObject();
        } catch (Exception e) {
        }
        
        assertTrue(response.toString().contains(categoryTitle));
        assertTrue(response.toString().contains(categoryID));
        assertTrue(response.toString().contains(categoryDescription));
        assertTrue(response.toString().contains("\"description\":\""));
}

 @Then("^a category with the (.*) will have empty fields apart from the (.*) should be created$")
    public void a_category_with_empty_fields_apart_from_the_title_should_be_created(String categoryID, String categoryTitle) {
        try {
            response = Unirest.get("/categories/"+categoryID).asJson().getBody().getObject();
        } catch (Exception e) {
        }
        assertTrue(response.toString().contains(categoryTitle));
        assertTrue(response.toString().contains(categoryID));
        
    } 

    @When("^a POST request to categories is sent with the category id and the following info:$")
    public void a_post_request_to_categories_is_sent_with_the_following_info(io.cucumber.datatable.DataTable dataTable) {
        boolean firstLine = true;
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            if(!firstLine)
            {
            response = Unirest.post("/categories/"+columns.get(0))
                    .body("{\n\"title\":\"" + columns.get(1) +"\",\n  \"description\":\""+ columns.get(2)+"\"\n}")
                    .asJson().getBody().getObject();

                    System.out.println("check here");
                    System.out.println("{\n\"title\":\"" + columns.get(1) +"\",\n  \"description\":\""+ columns.get(2)+"\"\n}");
            }
            firstLine = false;
        
        }
          
    }

}

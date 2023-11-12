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
public class DeleteCategory extends BaseStepDefinition{
    
    
    @Given("^(.*) is the id of a category with all fields filled$")
    public void is_the_id_of_a_category_with_all_fields_filled(String categoryID) {
        
    }
    

    @When("^a DELETE request is sent with all the fields filled:$")
    public void a_DELETE_request_is_sent_with_all_the_fields_filled(io.cucumber.datatable.DataTable dataTable) {
        AddCategory category  = new AddCategory();
        category.a_post_request_to_categories_is_sent_with_the_following_info(dataTable);
        //.a_post_request_to_categories_is_sent_with_the_following_info(dataTable);
        boolean firstLine = true;
        List<List<String>> rows = dataTable.asLists(String.class);

        for (List<String> columns : rows) {
            if(!firstLine)
            {
            response = Unirest.delete("/categories/"+ columns.get(0))                  
                    .asJson().getBody().getObject();
            }
            firstLine = false;

        }
        
        
    }

    @Given("^(.*) is the title of a category with all fields filled$")
    public void is_the_title_of_a_category_with_all_fields_filled(String categoryTitle) {
    
    }

    @Given("^(.*) is the description of a category with all fields filled$")
    public void is_the_description_of_a_category_with_all_fields_filled(String categoryDescription) {
    
    }
    
    @When("^a DELETE request is sent with the following info:$")
    public void a_DELETE_request_is_sent_with_the_following_info(io.cucumber.datatable.DataTable dataTable) {
        List<List<String>> rows = dataTable.asLists(String.class);
        boolean firstLine = true;
        for (List<String> columns : rows) {
            if(!firstLine)
            {
            response = Unirest.delete("/categories/"+ columns.get(0))                  
                    .asJson().getBody().getObject();
            }
            firstLine = false;
        }
        
    }

    @Then("^the category should be deleted and (.*) and (.*) should not be there$")
    public void the_category_should_be_deleted(String categoryTitle, String categoryDescription) {
        
        assertFalse(response.toString().contains(categoryTitle));
        assertFalse(response.toString().contains(categoryDescription));
        
    } 
    

    

    @Given("^(.*) is the id of a category with only mandatory fields filled$")
    public void is_the_id_of_a_category_with_only_mandatory_fields_filled(String categoryID) {
        
    } 

    
 

    

    
    

}

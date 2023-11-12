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
public class AddCategory extends BaseStepDefinition{
    
    
    @Given("^(.*) is the title of a category$")
    public void selected_title_is_the_title_of_a_category(String selectedTitle) {
        
    }
    
    @Given("^(.*) is the description of a category$")
    public void is_the_description_of_a_category(String categoryDescription) {
        

    }
    @When("^a POST request to categories is sent with the following info:$")
    public void a_post_request_to_categories_is_sent_with_the_following_info(io.cucumber.datatable.DataTable dataTable) {
        boolean firstLine = true;
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            if(!firstLine)
            {
            response = Unirest.post("/categories")
                    .body("{\n\"title\":\"" + columns.get(0) +"\",\n  \"description\":\""+ columns.get(1)+"\"\n}")
                    .asJson().getBody().getObject();
            }
            firstLine = false;
        
        }
          
    }

    @Then("^a category should be created with the title (.*) and description (.*)$")
    public void a_category_should_be_created_with_title_and_description(String categoryTitle, String categoryDescription) {
        
        assertTrue(response.toString().contains(categoryTitle));
        assertTrue(response.toString().contains(categoryDescription));
    }

    @Given("^(.*) is the required field of a category$")
    public void is_the_required_field_of_a_category(String categoryTitle) {
        
        
    }
    @Given("^(.*) is not a required field of a category$")
    public void is_not_the_required_field_of_a_category(String categoryDescription) {
        
        
    }
    
    @Then("^a category should be created with the title (.*) and no (.*)$")
    public void a_category_should_be_created_with_the_title(String categoryTitle, String categoryDescription) {
        assertTrue(response.toString().contains(categoryTitle));
        assertTrue(categoryDescription.equals("[blank]"));
        
    }

    
    

    @Then("^the server should respond with an error message (.*)$")
    public void the_server_should_respond_with_an_error_message(String msg) {
        System.out.println("error:");
            System.out.println(response.getJSONArray("errorMessages").get(0).
          toString());
          System.out.println("what i printed");
          System.out.println(msg);


        assertTrue(response.getJSONArray("errorMessages").get(0).
          toString().contains(msg));
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }

    
    

}

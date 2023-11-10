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



/*
 * author: Vanessa Akhras
 */
public class AddCategory extends BaseStepDefinition{
    
    
    @Given("^(.*) is the title of a category")
    public void selected_title_is_the_title_of_a_category(String selectedTitle) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();
        //assertTrue("hello".contains("h"));
    }
    
    @Given("^(.*) is the description of a category$")
    public void is_the_description_of_a_category(String categoryDescription) {
        // Write code here that turns the phrase above into concrete actions
        //throw new io.cucumber.java.PendingException();

    }
    @When("^a POST request to categories is sent with the following info:$")
    public void a_post_request_to_categories_is_sent_with_the_following_info(io.cucumber.datatable.DataTable dataTable) {
        
        List<List<String>> rows = dataTable.asLists(String.class);
        for (List<String> columns : rows) {
            response = Unirest.post("/categories")
                    .body("{\n\"title\":\"" + columns.get(0) +"\"description\":\""+ columns.get(1)+"\"\n}")
                    //.body("{\n\"title\":\"" + columns.get(1) +"\"\n}")
                    .asJson().getBody().getObject();
        }
        //System.out.println(response.toString());
        
    }

    @Then("^a category should be created with the title (.*) and description (.*)$")
    public void a_category_should_be_created_with_title_and_description(String categoryTitle, String categoryDescription) {
        
        assertTrue(response.toString().contains(categoryTitle));
        //assertTrue(response.toString().contains(categoryDescription));
    }

    @Given("^(.*) is the required field of a category$")
    public void is_the_required_field_of_a_category(String categoryTitle) {
        //idk what to write here
        //assertNull(findCategoryByName(categoryTitle));
        
    }
    @Then("^a category with empty fields apart from the (.*) should be created$")
    public void a_category_with_empty_fields_apart_from_the_title_should_be_created(String categoryTitle) {
        //assertTrue(response.toString().contains(categoryTitle));
        //assertTrue(response.toString().contains(categoryDescription));
    }

    
    

    @Then("^the server should respond with an error message (.*)$")
    public void the_server_should_respond_with_an_error_message(String msg) {
        //assertTrue(response.getJSONArray("errorMessages").get(0).
          //toString().contains(msg));
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public String stringType(String cell) {
        return cell;
    }
    

}

package project_B;

import io.cucumber.java.en.*;

import java.util.List;

import org.junit.jupiter.api.Assertions.*;

public class AddATodoStepDefinitions {

    @Given("the following todos exist:")
    public void the_following_todos_exist(String url) {
        // Code to create todos
        throw new io.cucumber.core.exception.CucumberException("Error: Unable to create todos");
    }
}


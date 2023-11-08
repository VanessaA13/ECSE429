package project_B;


import io.cucumber.java.en.Given;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import kong.unirest.Unirest;

public class MiscStepDefinition extends BaseStepDefinition {

    @Before
    public void initVars() {
        Unirest.config().defaultBaseUrl(BASE_URL);
        startServer();
        counter = 0;
        statusCode = 0;
        errorMessage = "";
        response = null;
        originalValue = null;
        originalTodoList = null;
        taskList = null;
    }

    @After
    public void after() {
        stopServer();
    }
        
    @Given("^the API server is running$")
    public void theAPIServerIsRunning() {
        waitUntilOnline();
    }
}
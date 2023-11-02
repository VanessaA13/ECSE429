package project_B;

import java.io.IOException;

import io.cucumber.java.After;
import io.cucumber.java.Before;
// import gherkin.deps.com.google.gson.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
// import static org.hamcrest.CoreMatchers.containsString;

public class Hooks {

    private static Process process;

        public static Process startApplication() {
        System.out.println("Starting application...");
        Runtime rt = Runtime.getRuntime();
        try {
            process = rt.exec("java -jar /Users/habibjarweh/Downloads/Application_Being_Tested/runTodoManagerRestAPI-1.5.5.jar");
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean appStarted = false;
        while (!appStarted) {
            try {
                get("http://localhost:4567/");
                appStarted = true;
            } catch (Exception e1) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }

        return process;
    }

    public static void stopApplication() {
        if (process != null) process.destroy();
    }

    @Before
    public void beforeScenario() {
        // Code to run before each scenario
        System.out.println("Before each scenario: Performing setup actions");
        startApplication();
    }

    @After
    public void afterScenario() {
        // Code to run after each scenario
        System.out.println("After each scenario: Performing teardown actions");
        stopApplication();
    }
}
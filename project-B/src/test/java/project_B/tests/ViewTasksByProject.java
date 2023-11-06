package project_B.tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import io.cucumber.java.After;
import io.cucumber.java.Before;


import io.cucumber.datatable.DataTable;
import kong.unirest.Unirest;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONArray;
import kong.unirest.json.JSONObject;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import project_B.BaseStepDefinition;

/*
 * author: Habib Jarweh
 */

public class ViewTasksByProject extends BaseStepDefinition {



    @Given("^the following projects exist on the system$")
    public void theFollowingProjectsExistOnTheSystem(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);

        boolean firstLine = true;
        for (List<String> columns : rows) {
            // ignore title row
            if (!firstLine) {
                String title = "\"title\":\"" + columns.get(0) + "\"";
                String completed = "\"completed\":" + columns.get(1);
                String active = "\"active\":" + columns.get(2);
                String description = "\"description\":\"" + columns.get(3) + "\"";
                Unirest.post("/projects")
                        .body("{\n" + title + ",\n" + completed + ",\n" + active + ",\n" + description + "\n}")
                        .asJson();
            }
            firstLine = false;
        }
    }


    @And("the following todos are associated with {string}")
    public void theFollowingTodosAreAssociatedWithClass(String className, DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        int projId = findProjectByName(className).getInt("id");
        boolean firstLine = true;
        for (List<String> columns : rows) {
            // ignore title row
            if(!firstLine) {
                int id = addTodoByRow(columns).getInt("id");
                Unirest.post("/todos/" + id + "/tasksof")
                        .body("{\"id\":\"" + projId + "\"}")
                        .asJson();
            }
            firstLine = false;
        }
    }

    @And("no todos are associated with {string}")
    public void noTodosAreAssociatedWithFACC(String projectTitle) {
        assertEquals(getProjectTasks(projectTitle).length(), 0);
    }

    @Given("^(.*) is the title of a project on the system$")
    public void projectTitleIsTheTitleOfAProjectOnTheSystem(String projectTitle) {
        assertNotNull(findProjectByName(projectTitle));
    }

    @And("^the project with title (.*) has outstanding tasks$")
    public void theClassWithTitleProjectTitleHasOutstandingTasks(String projectTitle) {
        projecteHasOutstandingTasks(projectTitle, true);
    }

    @When("^the user requests the tasks for the project with title (.*)$")
    public void theUserRequestsTheTasksForTheProjectWithTitleProjectTitle(String projectTitle) {
        taskList = new JSONArray();
        JSONArray tasks = getProjectTasks(projectTitle);
        if (tasks == null) {
            // originally called "/projects/-1/tasks" but this returns all tasks for some reason. See bug report
            response = Unirest.get("/projects/-1").asJson().getBody().getObject();
            return;
        }
        for (Object o : tasks) {
            int id = ((JSONObject) o).getInt("id");
            JSONObject todo = (JSONObject) Unirest.get("/todos/" + id).asJson().getBody().getObject()
                    .getJSONArray("todos").get(0);
            taskList.put(todo);
        }
    }

    @Then("^(.*) todos will be returned$")
    public void nTodosWillBeReturned(int n) {
        assertEquals(n, taskList.length());
    }


    @And("^each todo returned will be a task of the project with title (.*)$")
    public void eachTodoReturnedWillBeATaskOfTheProjectWithTitleProjectTitle(String projectTitle) {
        JSONArray tasks = getProjectTasks(projectTitle);
        Set<Integer> taskIDs = new HashSet<>();
        for (Object o : tasks) {
            JSONObject task = (JSONObject) o;
            taskIDs.add(task.getInt("id"));
        }

        for (Object o : taskList) {
            JSONObject todo = (JSONObject) o;
            assertTrue(taskIDs.contains(todo.getInt("id")));
        }
    }


    @And("^the project with title (.*) has no outstanding tasks$")
    public void theClassWithTitleProjectTitleHasNoOutstandingTasks(String projectTitle) {
        JSONArray tasks = getProjectTasks(projectTitle);
        for (Object o : tasks) {
            int id = ((JSONObject)o).getInt("id");
            JSONObject todo = (JSONObject) Unirest.get("/todos/" + id)
                    .asJson().getBody().getObject()
                    .getJSONArray("todos").get(0);
            if (todo.getString("doneStatus").equalsIgnoreCase("false")) {
                fail();
            }
        }
    }


    @Given("^(.*) is not a title of a project on the system$")
    public void projectTitleIsNotATitleOfAProjectOnTheSystem(String projectTitle) {
        assertNull(findProjectByName(projectTitle));
    }
}
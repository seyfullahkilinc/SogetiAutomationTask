package org.example.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class APIStepDefinitions {

    private Response response;

    @Given("User set base URI to {string}")
    public void setBaseUri(String baseUri) {
        RestAssured.baseURI = baseUri;
    }

    @When("User sends a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = RestAssured.get(endpoint);
    }

    @Then("The response status code should be {int}")
    public void verifyStatusCode(int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    @Then("The content type should be {string}")
    public void verifyContentType(String expectedType) {
        if (expectedType.equalsIgnoreCase("JSON")) {
            response.then().contentType(ContentType.JSON);
        }
    }

    @Then("The response time should be below {int} ms")
    public void verifyResponseTime(int maxTime) {
        response.then().time(lessThan((long) maxTime));
    }

    @Then("The response should have country {string} and state {string}")
    public void verifyCountryAndState(String expectedCountry, String expectedState) {
        response.then()
                .body("country", equalTo(expectedCountry))
                .body("state", equalTo(expectedState));
    }

    @Then("For post code {string}, the place name should contain {string}")
    public void verifyPlaceForPostCode(String postCode, String expectedPlaceName) {
        String gPath = "places.find { it.'post code' == '" + postCode + "' }.'place name'";
        response.then().body(gPath, containsString(expectedPlaceName));
    }
    @Then("The response should contain the place name {string}")
    public void verifyPlaceNameInArray(String expectedPlaceName) {
        response.then().body("places.'place name'", org.hamcrest.Matchers.hasItem(expectedPlaceName));
    }
}
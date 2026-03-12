package org.example.stepDefinitions;

import io.cucumber.java.en.Then;
import org.example.pages.HomePage;
import org.example.utils.DriverManager;
import org.testng.asserts.SoftAssert;
import java.util.Map;


public class TC3StepDefinitions {
    @Then("Assert that all the Country specific Sogeti links are working")
    public void verifyAllCountryLinks() {
        Map<String, Integer> results = new HomePage(DriverManager.getDriver()).checkCountryLinksStatus();
        SoftAssert softAssert = new SoftAssert();
        results.forEach((host, statusCode) ->
                softAssert.assertTrue(statusCode < 400, "Link failed: " + host + " | Status: " + statusCode)
        );
        softAssert.assertAll();
    }
}

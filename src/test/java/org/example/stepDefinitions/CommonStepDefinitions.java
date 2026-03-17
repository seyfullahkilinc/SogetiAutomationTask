package org.example.stepDefinitions;

import io.cucumber.java.en.*;
import org.example.pages.HomePage;
import org.example.pages.QualityEngineeringPage;
import org.example.utils.DriverManager;
import org.example.utils.ConfigReader;
import org.testng.Assert;


public class CommonStepDefinitions {
    private final HomePage homePage = new HomePage(DriverManager.getDriver());
    private final QualityEngineeringPage qePage = new QualityEngineeringPage(DriverManager.getDriver());

    @Given("User navigates to the {string}")
    public void userNavigatesToKey(String configKey) {
        String url = ConfigReader.get(configKey);
        homePage.navigateTo(url);
    }


    @And("User handles popups")
    public void userHandlesPopups() {
        homePage.handlePopups();
    }

    @Then("{string} text should be visible on the page")
    public void verifyTextVisibility(String configKey) {
        String expectedText = ConfigReader.get(configKey);
        Assert.assertTrue(homePage.isTextVisible(expectedText), "Verification failed for: " + expectedText);
    }


    @When("User clicks on the {string} icon in the header")
    public void userClicksOnIcon(String configKey) {
        String iconName = ConfigReader.get(configKey);
        homePage.clickWorldIcon();
    }
}
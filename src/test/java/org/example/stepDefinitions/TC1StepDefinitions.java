package org.example.stepDefinitions;

import io.cucumber.java.en.*;
import org.example.pages.HomePage;
import org.example.utils.ConfigReader;
import org.example.utils.DriverManager;

public class TC1StepDefinitions {

    private final HomePage homePage = new HomePage(DriverManager.getDriver());
    @When("User hovers over {string} menu")
    public void userHoversOverMenu(String configKey) {
        String menuName = ConfigReader.get(configKey);
        homePage.hoverOverElement(menuName);
    }

    @And("User clicks on {string} link")
    public void userClicksOnLink(String configKey) {
        String linkName = ConfigReader.get(configKey);
        homePage.clickElement(linkName);
    }
}
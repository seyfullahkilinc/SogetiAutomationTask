package org.example.stepDefinitions;

import io.cucumber.java.en.*;
import org.example.pages.HomePage;
import org.example.utils.DriverManager;
import org.openqa.selenium.WebDriver;

public class TC1StepDefinitions {
    private WebDriver driver = DriverManager.getDriver();
    private HomePage homePage = new HomePage(DriverManager.getDriver());

    @When("User hovers over {string} menu")
    public void userHoversOverMenu(String menuName) {
        homePage.hoverOverElement(menuName);
    }

    @And("User clicks on {string} link")
    public void userClicksOnLink(String linkName) {
        homePage.clickElement(linkName);
    }

}
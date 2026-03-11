package org.example.stepDefinitions;

import io.cucumber.java.en.*;
import org.example.pages.HomePage;
import org.example.pages.QualityEngineeringPage;
import org.example.utils.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TC1StepDefinitions {
    private WebDriver driver = DriverManager.getDriver();
    private HomePage homePage = new HomePage(DriverManager.getDriver());
   private QualityEngineeringPage qePage = new QualityEngineeringPage(driver);

    @Given("User navigates to {string}")
    public void userNavigatesTo(String url) {
        homePage.navigateTo(url);
    }

    @And("User handles popups")
    public void userHandlesPopups() {
        homePage.handlePopups();
    }

    @When("User hovers over {string} menu")
    public void userHoversOverMenu(String menuName) {
        homePage.hoverOverElement(menuName);
    }

    @And("User clicks on {string} link")
    public void userClicksOnLink(String linkName) {
        homePage.clickElement(linkName);
    }

    @Then("{string} text should be visible on the page")
    public void textShouldBeVisible(String text) {
        if (text.equalsIgnoreCase("Quality Engineering")) {
            Assert.assertTrue(qePage.isMainBannerTagVisible(), "Specific banner tag not found!");
        } else {
            Assert.assertTrue(homePage.isTextVisible(text), "Text not visible: " + text);
        }
    }
}
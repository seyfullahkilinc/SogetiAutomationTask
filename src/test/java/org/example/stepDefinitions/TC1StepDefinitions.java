package org.example.stepDefinitions;

import io.cucumber.java.en.*;
import org.example.pages.HomePage;
import org.example.utils.DriverManager;
import org.testng.Assert;

public class TC1StepDefinitions {

    private HomePage homePage = new HomePage(DriverManager.getDriver());

    @Given("User navigates to {string}")
    public void user_navigates_to(String url) {
        homePage.navigateTo(url);
    }

    @When("User hovers over {string} menu")
    public void user_hovers_over_menu(String menuName) {
        homePage.hoverOverElement(menuName);
    }

    @And("User clicks on {string} link")
    public void user_clicks_on_link(String linkName) {
        homePage.clickElement(linkName);
    }

    @Then("{string} text should be visible on the page")
    public void text_should_be_visible(String text) {
        Assert.assertTrue(homePage.isTextVisible(text),
                text + " text is not visible on the page!");
    }
    @And("User handles popups")
    public void user_handles_popups() {
        homePage.handlePopups();
    }
}

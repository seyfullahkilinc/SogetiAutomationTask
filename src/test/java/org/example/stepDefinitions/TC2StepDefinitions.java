package org.example.stepDefinitions;

import io.cucumber.java.en.*;
import org.example.pages.ContactUsPage;
import org.example.utils.ConfigReader;
import org.example.utils.DriverManager;
import org.example.utils.FakerUtils;

public class TC2StepDefinitions {
    private final ContactUsPage contactUsPage = new ContactUsPage(DriverManager.getDriver());

    @When("User clicks on header link {string}")
    public void userClicksOnHeaderLink(String configKey) {
        contactUsPage.clickContactUs();
    }

    @And("User scrolls to {string} section")
    public void userScrollsToSection(String configKey) {
        String sectionTitle = ConfigReader.get(configKey);
        contactUsPage.scrollToSection(sectionTitle);
    }

    @And("User selects a purpose of contact")
    public void userSelectsPurpose() {
        contactUsPage.selectPurpose(1);
    }

    @And("User selects a random country")
    public void userSelectsCountry() {
        contactUsPage.selectCountry(1);
    }

    @And("User fills the field {string} with random first name")
    public void fillFirstName(String configKey) {
        contactUsPage.fillField(ConfigReader.get(configKey), FakerUtils.getFirstName());
    }

    @And("User fills the field {string} with random last name")
    public void fillLastName(String configKey) {
        contactUsPage.fillField(ConfigReader.get(configKey), FakerUtils.getLastName());
    }

    @And("User fills the field {string} with random job title")
    public void fillJobTitle(String configKey) {
        contactUsPage.fillField(ConfigReader.get(configKey), FakerUtils.getJobTitle());
    }

    @And("User fills the field {string} with random email")
    public void fillEmail(String configKey) {
        contactUsPage.fillField(ConfigReader.get(configKey), FakerUtils.getEmail());
    }

    @And("User fills the field {string} with random company name")
    public void fillCompany(String configKey) {
        contactUsPage.fillField(ConfigReader.get(configKey), FakerUtils.getCompanyName());
    }

    @And("User fills the field {string} with random phone number")
    public void fillPhone(String configKey) {
        contactUsPage.fillField(ConfigReader.get(configKey), FakerUtils.getPhoneNumber());
    }

    @And("User fills the field {string} with random message")
    public void fillMessage(String configKey) {
        contactUsPage.fillField(ConfigReader.get(configKey), FakerUtils.getMessage());
    }

    @And("User checks the {string} checkbox")
    public void userChecksCheckbox(String configKey) {
        contactUsPage.acceptAgreement();
    }

    @And("User slides to submit the form")
    public void userSlidesToSubmit() {
        contactUsPage.performSlideToSubmit();
    }
}
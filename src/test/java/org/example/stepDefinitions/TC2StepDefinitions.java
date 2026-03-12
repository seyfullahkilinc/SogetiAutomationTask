package org.example.stepDefinitions;

import io.cucumber.java.en.*;
import org.example.pages.ContactUsPage;
import org.example.utils.DriverManager;
import org.example.utils.FakerUtils;

public class TC2StepDefinitions {
    private final ContactUsPage contactUsPage = new ContactUsPage(DriverManager.getDriver());

    @When("User clicks on header link {string}")
    public void userClicksOnHeaderLink(String linkText) {
        contactUsPage.clickContactUs();
    }

    @And("User scrolls to {string} section")
    public void userScrollsToSection(String section) {
        // Handled within page actions
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
    public void fillFirstName(String label) {
        contactUsPage.fillField(label, FakerUtils.getFirstName());
    }

    @And("User fills the field {string} with random last name")
    public void fillLastName(String label) {
        contactUsPage.fillField(label, FakerUtils.getLastName());
    }

    @And("User fills the field {string} with random job title")
    public void fillJobTitle(String label) {
        contactUsPage.fillField(label, FakerUtils.getJobTitle());
    }

    @And("User fills the field {string} with random email")
    public void fillEmail(String label) {
        contactUsPage.fillField(label, FakerUtils.getEmail());
    }

    @And("User fills the field {string} with random company name")
    public void fillCompany(String label) {
        contactUsPage.fillField(label, FakerUtils.getCompanyName());
    }

    @And("User fills the field {string} with random phone number")
    public void fillPhone(String label) {
        String phone = new com.github.javafaker.Faker().number().digits(10);
        contactUsPage.fillField(label, phone);
    }

    @And("User fills the field {string} with random message")
    public void fillMessage(String label) {
        contactUsPage.fillField(label, FakerUtils.getMessage());
    }

    @And("User checks the {string} checkbox")
    public void userChecksCheckbox(String label) {
        contactUsPage.acceptAgreement();
    }

    @And("User slides to submit the form")
    public void userSlidesToSubmit() {
        contactUsPage.performSlideToSubmit();
    }
}
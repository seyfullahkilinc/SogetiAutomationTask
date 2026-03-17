@UI @Case2
Feature: Contact Us Form Submission

  Scenario: Submit Contact Us form with random data and verify Thank You message
    Given User navigates to the "sogeti.base.url"
    And User handles popups
    When User clicks on header link "contact.us.link.key"
    And User scrolls to "get.in.touch.section.key" section
    And User selects a purpose of contact
    And User fills the field "first.name.label" with random first name
    And User fills the field "last.name.label" with random last name
    And User fills the field "job.title.label" with random job title
    And User fills the field "email.label" with random email
    And User selects a random country
    And User fills the field "company.label" with random company name
    And User fills the field "phone.label" with random phone number
    And User fills the field "message.label" with random message
    And User checks the "agree.checkbox.label" checkbox
    And User slides to submit the form
    Then "thank.you.message" text should be visible on the page
@UI @Case2
Feature: Contact Us Form Submission

  # TC2: Fill and submit the Contact Us form with randomly generated data

  # IMPLEMENTATION NOTE — Anti-Bot Mitigation:
  # The form uses a custom 'Slide to Submit' component to prevent automated bots.
  # This has been successfully automated using a JavaScript-based Event Dispatcher
  # that simulates mousedown, mousemove, and mouseup events to bypass UI restrictions.

  Scenario: Submit Contact Us form with random data and verify Thank You message
    Given User navigates to "https://www.sogeti.com/"
    And User handles popups
    When User clicks on header link "Contact Us"
    And User scrolls to "Get in touch" section
    And User selects a purpose of contact
    And User fills the field "First name" with random first name
    And User fills the field "Last name" with random last name
    And User fills the field "Job Title" with random job title
    And User fills the field "E-mail address" with random email
    And User selects a random country
    And User fills the field "Company" with random company name
    And User fills the field "Phone number" with random phone number
    And User fills the field "Message" with random message
    And User checks the "I agree" checkbox
    And User slides to submit the form
    Then "Thank you" text should be visible on the page

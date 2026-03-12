@UI @Case1
Feature: Quality Engineering Page Verification

  Scenario: Verify navigation and visibility of Quality Engineering content
    Given User navigates to "https://www.sogeti.com/"
    And User handles popups
    When User hovers over "Services" menu
    And User clicks on "Quality Engineering" link
    Then "Quality Engineering" text should be visible on the page
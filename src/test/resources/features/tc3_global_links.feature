@UI @Case3
Feature: Global Country Links Verification

  Scenario: Verify all country specific links are working
    Given User navigates to "https://www.sogeti.com/"
    And User handles popups
    When User clicks on the globe icon in the header
    Then Assert that all the Country specific Sogeti links are working
@UI @Case3
Feature: Global Country Links Verification

  Scenario: Verify all country specific links are working
    Given User navigates to the "sogeti.base.url"
    And User handles popups
    When User clicks on the "globe.icon.key" icon in the header
    Then Assert that all the Country specific Sogeti links are working
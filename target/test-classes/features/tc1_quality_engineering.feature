Feature: Sogeti Website Navigation

  Scenario: Navigate to Quality Engineering page via Services menu
    Given User navigates to "https://www.sogeti.com/"
    And User handles popups
    When User hovers over "Services" menu
    And User clicks on "Quality Engineering" link
    Then "Quality Engineering" text should be visible on the page
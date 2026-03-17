@UI @Case1
Feature: Quality Engineering Page Verification

  Scenario: Verify navigation and visibility of Quality Engineering content
    Given User navigates to the "sogeti.base.url"
    And User handles popups
    When User hovers over "services.menu.key" menu
    And User clicks on "quality.engineering.link.key" link
    Then "quality.engineering.banner" text should be visible on the page
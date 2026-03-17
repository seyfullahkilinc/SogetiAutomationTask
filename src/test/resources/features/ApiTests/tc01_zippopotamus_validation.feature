@API @API_TC01
Feature: Zippopotamus Location API Verification

  Scenario Outline: API_TC01 - Validate detailed geographical nodes for a specific German postal code
    Given User sets the API base URI from "api.base.url"
    When User sends a GET request to "<endpoint>"
    Then The response status code should be 200
    And The content type should be "JSON"
    And The response time should be below <max_time> ms
    And The response should have country "<country>" and state "<state>"
    And For post code "<post_code>", the place name should contain "<place_name>"

    Examples:
      | endpoint         | country | state             | post_code | place_name          | max_time |
      | /de/bw/stuttgart | Germany | Baden-Württemberg | 70597     | Stuttgart Degerloch | 1000     |
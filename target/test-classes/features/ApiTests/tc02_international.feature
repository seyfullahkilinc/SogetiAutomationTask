@API @API_TC02
Feature: Zippopotamus International Data-Driven API Verification

  Scenario Outline: API_TC02 - Verify place names for multiple countries and postal codes
    Given User sets the API base URI from "api.base.url"
    When User sends a GET request to "/<country>/<postal_code>"
    Then The response status code should be 200
    And The content type should be "JSON"
    And The response time should be below 1000 ms
    And The response should contain the place name "<place_name>"

    Examples:
      | country | postal_code | place_name    |
      | us      | 90210       | Beverly Hills |
      | us      | 12345       | Schenectady   |
      | ca      | B2R         | Waverley      |
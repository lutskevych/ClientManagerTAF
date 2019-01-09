Feature: JSON response has correct schema

  Scenario: User navigate to users endpoint and receives response with correct JSON schema
    When user navigates to endpoint
    Then response has correct schema
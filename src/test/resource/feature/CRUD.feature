Feature: Administrator can insert, update and delete user

  Scenario: Administrator inserts user
    When administrator inserts user
    Then user was inserted
    And record has correct data

  Scenario Outline: Administrator filters user by gender
    When administrator select "<gender>" users
    Then response have only "<gender>" users
    Examples:
      | gender |
      | MALE   |
      | FEMALE |

  Scenario: Administrator updates user
    When administrator updates user
    Then user was updated
    And updated fields has correct data

  Scenario: Administrator deletes user
    When administrator deletes user
    Then user was deleted
    And correct record disappeared
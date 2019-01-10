Feature: Administrator can insert, update and delete user

  @delete
  Scenario Outline: Administrator inserts user
    When administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    Then changes are successful
    And user appeared
    And record has correct data
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          |

  @delete
  Scenario Outline: Administrator filters user by gender
    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    When administrator select gender users
    Then response have only gender users
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          |

  @delete @ignored
  Scenario Outline: Administrator filters users by Uid
    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    When administrator select user by uid
    Then response have only user with correct uid
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          |

  @delete
  Scenario Outline: Administrator updates user
    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    When administrator change last name on "<changedLastName>"
    Then changes are successful
    And field with "<changedLastName>" has correct data
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   | changedLastName |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com | Ivanova         |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          | Ivanov          |

  @delete
  Scenario Outline: Administrator deletes user
    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    When administrator deletes user
    Then changes are successful
    And user does not exist
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          |
@ignore
Feature: Administrator can not insert or update incorrect values

  @delete
  Scenario Outline: Administrator can not insert incorrect values for gender fields
    When administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    Then error message with "<statusCode>" appears
    And user does not exist
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   | statusCode |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMAL | 21   | anna.shkulova@gmail.com | 400        |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MAL   | 30   | j.sm@gmail.com          | 400        |

  @delete
  Scenario Outline: Administrator can not update incorrect values for gender fields
    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    When administrator updates gender field with "<incorrectGender>"
    Then error message with "<statusCode>" appears
    And record has correct data
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   | incorrectGender | statusCode |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com | F               | 400        |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          | M               | 400        |

  @delete
  Scenario Outline: Administrator can not insert incorrect values for age fields
    When administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    Then error message with "<statusCode>" appears
    And user does not exist
    Examples:
      | Uid                                  | firstName | lastName | gender | age  | email                   | statusCode |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 113  | anna.shkulova@gmail.com | 404        |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | -1   | j.sm@gmail.com          | 404        |

#  @delete
#  Scenario Outline: Administrator can not update incorrect values for age fields
#    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
#    When administrator updates age field with "<incorrectAge>"
#    Then error message with "<statusCode>" appears
#    And record has correct data
#    Examples:
#      | Uid                                  | firstName | lastName | gender | age | email                   | incorrectAge      | statusCode |
#      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com | -1                | 400        |
#      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          | 123               | 400        |
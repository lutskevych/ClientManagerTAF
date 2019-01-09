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

  @delete
  Scenario Outline: Administrator can not insert incorrect values for full name fields
    When administrator inserts user with "<incorrectFullName>" "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    Then user appeared with correct full name
    Examples:
      | Uid                                  | firstName | lastName | gender | age  | email                   | incorrectFullName |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21   | anna.shkulova@gmail.com | A Shkulova        |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 21   | j.sm@gmail.com          | John S            |

  @delete
  Scenario Outline: Administrator can not update incorrect values for full name fields
    When administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    Then administrator updates full name field with "<incorrectFullName>"
    And record has correct data
    Examples:
      | Uid                                  | firstName | lastName | gender | age  | email                   | incorrectFullName |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21   | anna.shkulova@gmail.com | A Shkulova        |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 21   | j.sm@gmail.com          | John S            |

  @delete
  Scenario Outline: Administrator can not insert incorrect values for date of birth fields
    When administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>", "<incorrectDateOfBirth>"
    Then user appeared with correct date of birth
    Examples:
      | Uid                                  | firstName | lastName | gender | age  | email                   | incorrectDateOfBirth |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21   | anna.shkulova@gmail.com | 3000                 |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 21   | j.sm@gmail.com          | 1900                 |

  @delete
  Scenario Outline: Administrator can not update incorrect values for date of birth fields
    When administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>"
    Then administrator updates date of birth field with "<incorrectDateOfBirth>"
    And record has correct data
    Examples:
      | Uid                                  | firstName | lastName | gender | age  | email                   | incorrectDateOfBirth |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21   | anna.shkulova@gmail.com | 3000                 |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 21   | j.sm@gmail.com          | 1900                 |

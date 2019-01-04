Feature: Administrator can insert, update and delete user

  @delete
  Scenario Outline: Administrator inserts user
    When administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>","<fullName>"
    Then user was inserted
    And record has correct data
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   | fullName      |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com | Anna Shkulova |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          | John Smith    |
      | 125d1a3f-baaa-4b9b-b60c-991469e40235 | Nina      | Shkulova | FEMALE | 22  | nina.shkulova@gmail.com | Nina Shkulova |
      | 125d1a3f-baaa-4b9b-b60c-991469e40236 | Mike      | Smith    | MALE   | 31  | m.sm@gmail.com          | Mike Smith    |

  @delete
  Scenario Outline: Administrator filters user by gender
    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>","<fullName>"
    When administrator select gender users
    Then response have only gender users
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   | fullName      |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com | Anna Shkulova |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          | John Smith    |
      | 125d1a3f-baaa-4b9b-b60c-991469e40235 | Nina      | Shkulova | FEMALE | 22  | nina.shkulova@gmail.com | Nina Shkulova |
      | 125d1a3f-baaa-4b9b-b60c-991469e40236 | Mike      | Smith    | MALE   | 31  | m.sm@gmail.com          | Mike Smith    |

  @delete
  Scenario Outline: Administrator filters users by Uid
    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>","<fullName>"
    When administrator select user by uid
    Then response have only user with correct uid
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   | fullName      |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com | Anna Shkulova |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          | John Smith    |
      | 125d1a3f-baaa-4b9b-b60c-991469e40235 | Nina      | Shkulova | FEMALE | 22  | nina.shkulova@gmail.com | Nina Shkulova |
      | 125d1a3f-baaa-4b9b-b60c-991469e40236 | Mike      | Smith    | MALE   | 31  | m.sm@gmail.com          | Mike Smith    |

  @delete
  Scenario Outline: Administrator updates user
    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>","<fullName>"
    When administrator change last name on "<changedLastName>"
    Then user was updated
    And field with "<changedLastName>" has correct data
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   | fullName      | changedLastName |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com | Anna Shkulova | Ivanova         |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          | John Smith    | Ivanov          |
      | 125d1a3f-baaa-4b9b-b60c-991469e40235 | Nina      | Shkulova | FEMALE | 22  | nina.shkulova@gmail.com | Nina Shkulova | Ivanova         |
      | 125d1a3f-baaa-4b9b-b60c-991469e40236 | Mike      | Smith    | MALE   | 31  | m.sm@gmail.com          | Mike Smith    | Ivanov          |

  Scenario Outline: Administrator deletes user
    Given administrator inserts user with "<Uid>", "<firstName>","<lastName>","<gender>","<age>","<email>","<fullName>"
    When administrator deletes user
    Then user was deleted
    And user does not exist
    Examples:
      | Uid                                  | firstName | lastName | gender | age | email                   | fullName      |
      | 125d1a3f-baaa-4b9b-b60c-991469e40233 | Anna      | Shkulova | FEMALE | 21  | anna.shkulova@gmail.com | Anna Shkulova |
      | 125d1a3f-baaa-4b9b-b60c-991469e40234 | John      | Smith    | MALE   | 30  | j.sm@gmail.com          | John Smith    |
      | 125d1a3f-baaa-4b9b-b60c-991469e40235 | Nina      | Shkulova | FEMALE | 22  | nina.shkulova@gmail.com | Nina Shkulova |
      | 125d1a3f-baaa-4b9b-b60c-991469e40236 | Mike      | Smith    | MALE   | 31  | m.sm@gmail.com          | Mike Smith    |
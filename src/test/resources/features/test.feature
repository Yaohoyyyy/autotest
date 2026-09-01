Feature: User Management API

  Background:
    Given I am authenticated

  @create
  Scenario: Create user successfully
    When I send POST request to "/users" with body:
      """
      {"firstName": "Ivan", "lastName": "Petrov", "email": "petrov_cucumber@yandex.ru"}
      """
    Then response status is 201
    And response body has field "id" not null
    And response body has field "firstName" with value "Ivan"
    And response body has field "lastName" with value "Petrov"
    And response body has field "email" with value "petrov_cucumber@yandex.ru"
    And I store response id as "createdUserId"
    And user exists in database with email "petrov_cucumber@yandex.ru"
    And I delete user with email "petrov_cucumber@yandex.ru"

  @create
  Scenario: Create user without email
    When I send POST request to "/users" with body:
      """
      {"firstName": "Ivan", "lastName": "Petrov"}
      """
    Then response status is 400
    And response message is "email: Email is required"

  @create
  Scenario: Create user without lastName
    When I send POST request to "/users" with body:
      """
      {"firstName": "Ivan", "email": "ivan_nolastname@test.com"}
      """
    Then response status is 400
    And response message is "lastName: Last name is required"

  @create
  Scenario: Create user without firstName
    When I send POST request to "/users" with body:
      """
      {"lastName": "Petrov", "email": "petrov_nofirstname@test.com"}
      """
    Then response status is 400
    And response message is "firstName: First name is required"

  @create
  Scenario: Create user with existing email
    Given user with email "existing_cucumber@yandex.ru" exists in database
    When I send POST request to "/users" with body:
      """
      {"firstName": "Vasya", "lastName": "Kulikov", "email": "existing_cucumber@yandex.ru"}
      """
    Then response status is 400
    And response message is "User with email existing_cucumber@yandex.ru already exists"
    And I delete user with email "existing_cucumber@yandex.ru"

  @get
  Scenario: Get user by ID
    When I send POST request to "/users" with body:
      """
      {"firstName": "GetUser", "lastName": "Test", "email": "getuser_by_id_cucumber@test.com"}
      """
    And I store response id as "userId"
    When I send GET request to "/users/{userId}"
    Then response status is 200
    And response body has field "id" with value "{userId}"
    And response body has field "firstName" with value "GetUser"
    And response body has field "lastName" with value "Test"
    And response body has field "email" with value "getuser_by_id_cucumber@test.com"
    And I delete user with email "getuser_by_id_cucumber@test.com"

  @get
  Scenario: Get user by non-existent ID
    When I send GET request to "/users/999999"
    Then response status is 400
    And response message is "user not found with id: 999999"

  @get
  Scenario: Get user by invalid ID
    When I send GET request to "/users/abc"
    Then response status is 400
    And response message is "user not found with id: abc"

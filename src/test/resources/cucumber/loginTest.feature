Feature: Login Feature

  Scenario: Valid login test
    Given I am navigating to login page
    And  I enter valid email "yurii.voronenko@gmail.com" to email field
    And  I enter valid password "12345678" to password field
    And  I click login button
    Then account page is displayed

  Scenario: Invalid login test (invalid email)
    Given I am navigating to login page
    And  I enter invalid email "invalidLogin@gmail123" to email field
    And  I enter valid password "12345678" to password field
    And  I click login button
    Then account page is not displayed

  Scenario: Invalid login test (invalid password)
    Given I am navigating to login page
    And  I enter valid email "yurii.voronenko@gmail.com" to email field
    And  I enter invalid password "123456" to password field
    And  I click login button
    Then account page is not displayed
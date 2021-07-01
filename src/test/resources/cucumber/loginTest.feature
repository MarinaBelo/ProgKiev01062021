Feature: Login Feature

  @regression @login
  Scenario Outline: Login tests <email> -> <result>
    Given I am navigating to login page
    And  I enter "<email>" to email field
    And  I enter "<password>" to password field
    And  I click login button
    Then Login result must be "<result>" and error message "<error_message>" is displayed

    Examples:
      | email                     | password       | result  | error_message                                                      |
      | yurii.voronenko@gmail.com | 12345678       | success | -------------                                                      |
      | YURII.VORONENKO@GMAIL.COM | 12345678       | success | -------------                                                      |
      | $_INVALID_EMAIL_%         | 12345678       | fail    | Предупреждение: Не совпадает адрес электронной почты и/или пароль. |
      | yurii.voronenko@gmail.com | invalidPass123 | fail    | Предупреждение: Не совпадает адрес электронной почты и/или пароль. |


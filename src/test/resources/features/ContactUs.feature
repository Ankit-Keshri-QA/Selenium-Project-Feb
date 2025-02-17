Feature: WebDriver University Contact Us Page
  As a new User visiting the website
  I would like to enter my details
  So that the website can reach me out on my email

  Scenario: Successful Submission of the user
    Given I am on the WebDriverUniversity HomePage
    When I click on Contact Us Link
    And I enter a First Name
    And I enter a Last Name
    And I enter an email address
    And I enter my Comment
    And I click on Submit button
    Then I should be represented a successful submission of the form

  Scenario: Reset Complete Form
    Given I am on the WebDriverUniversity HomePage
    When I click on Contact Us Link
    And I enter a First Name
    And I enter a Last Name
    And I enter an email address
    And I click on Reset button
    Then my form should be empty once again



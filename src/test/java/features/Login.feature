# new feature
# Tags: optional
@appium
Feature: Login

Scenario: Login Flow Happy Path
    Given I want to login
    And I enter an email address into the email address field
    And I enter a password into the password field
    And I click the Login button
    Then I should be logged into my account and shown the MyAds screen
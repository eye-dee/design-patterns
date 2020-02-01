
Feature: Test Contact API
  In this feature file, REST api for the contact, will be tested.

  Scenario: Create a contact successfully
    Given empty list
    And user saves the new contact
    Then the save 'CREATED'

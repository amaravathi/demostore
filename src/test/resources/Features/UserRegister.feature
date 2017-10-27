@FunctionalTest
Feature: Creating new user in demoStore
@End2EndTest
Scenario: Create new user on demo Store
	Given We are on userRegistration page    
    Then UserName and Email textboxes should be available
    And Enter details in textboxes
    And Submit
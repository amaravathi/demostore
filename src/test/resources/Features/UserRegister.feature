Feature: Creating new user in demoStore

Scenario: Create new user on demo Store
	Given We are UserRegister Page
    When  Click on Register link    
    Then UserName and Email textboxes should be available
    And Enter details in textboxes
    And Submit
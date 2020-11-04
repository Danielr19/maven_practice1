Feature: Test user login

	Scenario: User login successfully with credentials
		Given Browser is open
		And user is in login page
		When user enters username and password
		And use clicks login button
		Then website shows main page

	
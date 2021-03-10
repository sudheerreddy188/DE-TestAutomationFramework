@Regression
Feature: Students Login


Scenario Outline: Verification of students user login
	Given Open the browser and land in home page
	When User  enters "<Credentials>" "<UserName>" and "<Password>" details
	Then Home page navigation is "<Credentials>"
	And Display the user name "<HomePageDisplayName>"

# Four possible combinations for a login scenario (3 invalid and 1 valid)	
Examples:
		|UserName		     |Password|Credentials|HomePageDisplayName|
		|u@phptravels.com   	     |demouser|invalid	  |NA		      |
		|user@phptravels.com	     |duser   |invalid    |NA		      |
		|u@phptravels.com	     |duser   |invalid	  |NA		      |
		|user@phptravels.com	     |demouser|valid      |Demo		      |




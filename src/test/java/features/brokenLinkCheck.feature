@Regression
Feature: Broken Link Check


Scenario: Verification of broken links in home page
	Given Open the browser and land in home page
	When Click on all the available links
	Then No link should be broken


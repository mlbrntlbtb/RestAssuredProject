@RestAssuredCucumber
Feature: (CRUD) operations of User using RESTAssured on RESTAPI application
	#AUTHORIZATIO
	Background: User is authorized to access an accounts list
		Given User has been granted an authorization key
			|AuthorizationKey|
		|Bearer 94fa7aafa024a23bfdba2ee7215214d9abc48dc85fadd3a4099a1f692eedb028|
			
		When User knows where to access the accounts list
			|Address|
		|https://gorest.co.in/public/v2/users|
	
	#CREATE
	Scenario: Authorized user creates new account with valid account information
		Given User is authorized to create a new account
		When User provides valid account information
			|name|email|gender|status|
		|John Wick|excommunicado@gmail.com|male|inactive|
		
		And Create request has been sent to the accounts list
		Then New account will be added in the accounts list
			
	#READ
	Scenario: Authorized user verifies existing new account in accounts list
		Given User is authorized to verify a new acount
		When User provides existing new account information
			|name|email|gender|status|
		|John Wick|excommunicado@gmail.com|male|inactive|
		And Read request has been sent to the accounts list
		Then Existing new account will be verified in the accounts list
		
	#UPDATE
	Scenario: Authorized user updates existing account in accounts list
		Given User is authorized to update an existing account
		When User provides valid account information to update existing account
			|name|email|gender|status|
		|Tony Stark|ironman@gmail.com|male|active|
		
		And Update request has been sent to the accounts list
		Then Existing account will be updated in the accounts list
		
	#DELETE
	Scenario: Authorized user deletes existing account in accounts list
		Given User is authroized to delete an existing account
		When User provides valid account information to delete existing account
		And Delete request has been sent to the accounts list
		Then Existing account will be deleted in the accounts list
		
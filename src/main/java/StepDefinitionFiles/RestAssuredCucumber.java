package StepDefinitionFiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import RestAssuredBasics.*;

public class RestAssuredCucumber 
{
	public static String AuthorizationKey;
	public static String Address;
	public static HashMap<String,String> accountInfo;
	public static RestAssuredPOJOforAccount pojoAccount;
	public static int accountID;
	public static JsonPath accountJsonPath; 
	
	//Method requests
	public RequestSpecification postRequest; //Create method request
	public RequestSpecification getRequest; //Read method request
	public RequestSpecification putRequest; //Update method request
	public RequestSpecification deleteRequest; //Delete method request
	
	//Responses
	public Response postResponse;
	public Response getResponse;
	public Response putResponse;
	public Response deleteResponse;
	
	//Status codes
	public static int STATUS_CODE_OK = 200;
	public static int STATUS_CODE_CREATED = 201;
	public static int STATUS_CODE_DELETED = 204;
	
	//Status lines
	public static String STATUS_LINE_OK = "HTTP/1.1 200 OK";
	public static String STATUS_LINE_CREATED = "HTTP/1.1 201 Created";
	public static String STATUS_LINE_DELETED = "HTTP/1.1 204 No Content";
	
	
	//Background: User is authorized to access an accounts list
	@Given("^User has been granted an authorization key$")
	public void user_has_been_granted_an_authorization_key(DataTable Data) 
	{
		List<Map<String, String>> data = Data.asMaps(String.class, String.class);
		AuthorizationKey = data.get(0).get("AuthorizationKey");
	}
	
	@When("^User knows where to access the accounts list$")
	public void user_knows_where_to_access_the_accounts_list(DataTable Data)
	{
		List<Map<String, String>> data = Data.asMaps(String.class, String.class);
		Address = data.get(0).get("Address");
		RestAssured.baseURI = Address;
	}
	
	
	//***CREATE***
	//Scenario: Authorized user creates new account with valid account information steps
	@Given("User is authorized to create a new account")
	public void User_is_authorized_to_create_a_new_account() 
	{
		postRequest = RestAssured.given();
		postRequest.header("Authorization",AuthorizationKey);
		postRequest.contentType(ContentType.JSON);
	}
	
	@When("^User provides valid account information$")
	public void user_provides_valid_account_information(DataTable Data) 
	{
		List<Map<String,String>> data = Data.asMaps(String.class, String.class);
		
		//Create request body using HashMap
//		accountInfo = new HashMap<String,String>();
//		accountInfo.put("name", data.get(0).get("name"));
//		accountInfo.put("email", data.get(0).get("email"));
//		accountInfo.put("gender", data.get(0).get("gender"));
//		accountInfo.put("status", data.get(0).get("status"));
//		postRequest.body(accountInfo);
		
		//Create request body using POJO (java object -- model)
		pojoAccount = new RestAssuredPOJOforAccount
				(data.get(0).get("name")
				,data.get(0).get("email")
				,data.get(0).get("gender")
				,data.get(0).get("status"));
		postRequest.body(pojoAccount);
	}
	
	@And("Create request has been sent to the accounts list")
	public void create_request_has_been_sent_to_the_accounts_list() 
	{
		postResponse = postRequest.post();
	}
	
	@Then("New account will be added in the accounts list")
	public void new_account_will_be_added_in_the_accounts_list() 
	{
		int statusCode = postResponse.statusCode();
		String statusLine = postResponse.statusLine();
		System.out.println("Status Code: [" + statusCode + "]");
		System.out.println("Status Line: [" + statusLine + "]");
		Assert.assertEquals(statusCode, STATUS_CODE_CREATED);
		Assert.assertEquals(statusLine, STATUS_LINE_CREATED);
		accountJsonPath = postResponse.jsonPath();
	}
	
	
	//***READ***
	//Scenario: Authorized user verifies existing new account in accounts list
	@Given("User is authorized to verify a new acount")
	public void user_is_authorized_to_verify_a_new_acount() 
	{
		getRequest = RestAssured.given();
		getRequest.header("Authorization",AuthorizationKey);
		getRequest.contentType(ContentType.JSON);
	}
	
	@When("^User provides existing new account information$")
	public void user_provides_existing_new_account_information(DataTable Data) 
	{
		List<Map<String,String>> data = Data.asMaps(String.class, String.class);
		
		//Retrieve data using HashMap
//		accountInfo = new HashMap<String,String>();
//		accountInfo.put("name", data.get(0).get("name"));
//		accountInfo.put("email", data.get(0).get("email"));
//		accountInfo.put("gender", data.get(0).get("gender"));
//		accountInfo.put("status", data.get(0).get("status"));
		
		//Retrieve data using POJO (java object -- model)
		pojoAccount = new RestAssuredPOJOforAccount
				(data.get(0).get("name")
				,data.get(0).get("email")
				,data.get(0).get("gender")
				,data.get(0).get("status"));
	}
	
	@And("Read request has been sent to the accounts list")
	public void read_request_has_been_sent_to_the_accounts_list() 
	{
		accountID = accountJsonPath.getInt("id");
		getResponse = getRequest.get("/" + accountID);
	}
	
	@Then("Existing new account will be verified in the accounts list")
	public void existing_new_account_will_be_verified_in_the_accounts_list() 
	{
		int statusCode = getResponse.statusCode();
		String statusLine = getResponse.statusLine();
		System.out.println("Status Code: [" + statusCode + "]");
		System.out.println("StatusLine: [" + statusLine + "]");
		Assert.assertEquals(statusCode, STATUS_CODE_OK);
		Assert.assertEquals(statusLine, STATUS_LINE_OK);
		
		
		//Assert account using created HashMap
//		accountJsonPath = getResponse.jsonPath();
//		Assert.assertEquals(accountJsonPath.getString("name"), accountInfo.get("name"));
//		Assert.assertEquals(accountJsonPath.getString("email"), accountInfo.get("email"));
//		Assert.assertEquals(accountJsonPath.getString("gender"), accountInfo.get("gender"));
//		Assert.assertEquals(accountJsonPath.getString("status"), accountInfo.get("status"));
		
		//Assert account using created POJO (java object -- model)
		RestAssuredPOJOforResponse pojoResponse = getResponse.getBody().as(RestAssuredPOJOforResponse.class);
		Assert.assertEquals(pojoResponse.name, pojoAccount.name);
		Assert.assertEquals(pojoResponse.email, pojoAccount.email);
		Assert.assertEquals(pojoResponse.gender, pojoAccount.gender);
		Assert.assertEquals(pojoResponse.status, pojoAccount.status);
	}
	
	
	//***UPDATE***
	//Scenario: Authorized user updates existing account in accounts list
	@Given("User is authorized to update an existing account")
	public void user_is_authorized_to_update_an_existing_account() 
	{
		putRequest = RestAssured.given();
		putRequest.header("Authorization", AuthorizationKey);
		putRequest.contentType(ContentType.JSON);
	}
	
	@When("User provides valid account information to update existing account")
	public void user_provides_valid_account_information_to_update_existing_account(DataTable Data) 
	{
		List<Map<String,String>> data = Data.asMaps(String.class, String.class);
		
		//Create request body using HashMap
		accountInfo = new HashMap<String,String>();
		accountInfo.put("name", data.get(0).get("name"));
		accountInfo.put("email", data.get(0).get("email"));
		accountInfo.put("gender", data.get(0).get("gender"));
		accountInfo.put("status", data.get(0).get("status"));
		putRequest.body(accountInfo);
		
		//Create request body using POJO (java object -- model)
		pojoAccount = new RestAssuredPOJOforAccount
				(data.get(0).get("name")
				,data.get(0).get("email")
				,data.get(0).get("gender")
				,data.get(0).get("status"));
		putRequest.body(pojoAccount);
	}
	
	@And("Update request has been sent to the accounts list")
	public void update_request_has_been_sent_to_the_accounts_list() 
	{
		putResponse = putRequest.put("/" + accountID);
	}
	
	@Then("Existing account will be updated in the accounts list")
	public void existing_account_will_be_updated_in_the_accounts_list()  
	{
		int statusCode = putResponse.statusCode();
		String statusLine = putResponse.statusLine();
		System.out.println("Status Code: [" + statusCode + "]");
		System.out.println("StatusLine: [" + statusLine + "]");
		Assert.assertEquals(statusCode, STATUS_CODE_OK);
		Assert.assertEquals(statusLine, STATUS_LINE_OK);
		accountJsonPath = putResponse.jsonPath();
	}
	
	
	//***DELETE***
	//Scenario: Authorized user deletes existing account in accounts list
	@Given("User is authroized to delete an existing account")
	public void user_is_authroized_to_delete_an_existing_account() 
	{
		deleteRequest = RestAssured.given();
		deleteRequest.header("Authorization", AuthorizationKey);
		deleteRequest.contentType(ContentType.JSON);
	}
	
	@When("User provides valid account information to delete existing account")
	public void user_provides_valid_account_information_to_delete_existing_account()
	{
		accountID = accountJsonPath.getInt("id");
	}
	
	@And("Delete request has been sent to the accounts list")
	public void delete_request_has_been_sent_to_the_accounts_list() 
	{
		deleteResponse = deleteRequest.delete("/" + accountID);
	}
	
	@Then("Existing account will be deleted in the accounts list")
	public void existing_account_will_be_deleted_in_the_accounts_list() 
	{
		int statusCode = deleteResponse.statusCode();
		String statusLine = deleteResponse.statusLine();
		System.out.println("Status Code: [" + statusCode + "]");
		System.out.println("StatusLine: [" + statusLine + "]");
		Assert.assertEquals(statusCode, STATUS_CODE_DELETED);
		Assert.assertEquals(statusLine, STATUS_LINE_DELETED);
	}
}


package RestAssuredBasics;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssuredQueryParameters 
{
	@Test
	public void Test() 
	{
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
		RequestSpecification httpRequest = RestAssured.given();
		String targetResource = "ISBN";
		String targetValue = "123456789";
		
		//Set query parameters before request method
		Response response = httpRequest.queryParam(targetResource, targetValue).get("/Book");
		
		@SuppressWarnings("rawtypes")
		ResponseBody responseBody = response.getBody();
		String bodyMessage = responseBody.asString();
		
		System.out.println("Response Body: [" + bodyMessage + "]");
		
		JsonPath jsonPath = responseBody.jsonPath();
		
		System.out.println("ISBN: [" + jsonPath.get("isbn") + "]");
		System.out.println("Title: [" + jsonPath.get("title") + "]");
	}
}

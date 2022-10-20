package RestAssuredBasics;

import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class RestAssuredResponseBody 
{
	@Test
	public void Test() 
	{
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
		RequestSpecification httpRequest = RestAssured.given();
		String targetResource = "ISBN";
		String targetValue = "9781449325862";
		Response response = httpRequest.queryParam(targetResource, targetValue).get("/Book");
		
		//Retrieve body of the response
		@SuppressWarnings("rawtypes")
		ResponseBody body = response.getBody();
		//ResponseBody bodyMessage = response.body();
		String bodyMessage = body.asString(); //Use JSON-path to print as JSON format
		
		System.out.println("Response Body Message: [" + bodyMessage + "]");
		
		//Validate body of the response
		Assert.assertEquals(bodyMessage.contains(targetValue), true);
	}
}

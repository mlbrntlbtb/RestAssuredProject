package RestAssuredBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredDeleteRequest 
{
	@Test
	public void Test() 
	{
		//Setup service endpoints' -- use gorest.co.in RESTAPI testing site -- use json viewer to copy json text
		RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		RequestSpecification httpRequest = RestAssured.given();
		
		//Setup request authorization, content-type, json body
		String authorizationKey = "Bearer 94fa7aafa024a23bfdba2ee7215214d9abc48dc85fadd3a4099a1f692eedb028";
		httpRequest.header("Authorization",authorizationKey);
		httpRequest.contentType(ContentType.JSON);
		
		//Set request method as POST
		Response response = httpRequest.delete("/4982");
		
		//Retrieve response status code and status line
		System.out.println("Status Code: [" + response.getStatusCode() + "]");
		System.out.println("Status Line: [" + response.getStatusLine() + "]");
		//System.out.println("Body: [" + response.asString() + "]");
	}
}

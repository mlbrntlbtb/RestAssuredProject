package RestAssuredBasics;

import java.util.HashMap;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredPostRequest 
{
	@Test
	public void Test() 
	{
		//Setup service endpoints' -- use gorest.co.in RESTAPI testing site -- use json viewer to copy json text
		RestAssured.baseURI = "https://gorest.co.in/public/v2/users";
		RequestSpecification httpRequest = RestAssured.given();
		
		//Setup json object parameters
		HashMap<String,String> requestParams = new HashMap<String,String>();
		requestParams.put("name", "genos");
		requestParams.put("email", "genos@gmail.com");
		requestParams.put("gender", "male");
		requestParams.put("status", "active");
		
		//Setup request authorization, content-type, json body
		String authorizationKey = "Bearer 94fa7aafa024a23bfdba2ee7215214d9abc48dc85fadd3a4099a1f692eedb028";
		httpRequest.header("Authorization",authorizationKey);
		httpRequest.contentType(ContentType.JSON);
		httpRequest.body(requestParams);
		
		//Set request method as POST
		Response response = httpRequest.post();
		
		//Retrieve response status code and status line
		System.out.println("Status Code: [" + response.getStatusCode() + "]");
		System.out.println("Status Line: [" + response.getStatusLine() + "]");
		System.out.println("Body: [" + response.asString() + "]");
	}
}

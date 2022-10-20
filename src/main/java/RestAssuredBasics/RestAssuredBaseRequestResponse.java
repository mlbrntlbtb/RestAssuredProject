package RestAssuredBasics;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredBaseRequestResponse 
{
	@Test
	public void Test() 
	{
		//Setup base URI or URL or Service endpoint -  root address for the target resource from the server
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		
		//Setup request specification
		RequestSpecification httpRequest = RestAssured.given();
		
		//Setup method type of request
		Response response = httpRequest.get();
		//Response response = httpRequest.request(Method.GET,"");
		
		//Retrieve status line and message body of the response
		String statusLine = response.statusLine();
		//Retrieves media type of the response e.g. application/json
		String contentType = response.getContentType();
		//Retrieves message body of the response
		String messageBody = response.prettyPrint();
		
		System.out.println("Status Line: [" + statusLine + "]");
		System.out.println("Content Type: [" + contentType + "]");
		System.out.println("Message Body: [" + messageBody + "]");
	}
}

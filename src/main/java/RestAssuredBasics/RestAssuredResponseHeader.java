package RestAssuredBasics;

import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredResponseHeader 
{
	@Test
	public void Test() 
	{
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		RequestSpecification httpRequest = RestAssured.given();	
		Response response = httpRequest.get();	
		
		//Response header can be zero or more
		//Response header is referred to as Metadata of response
	
		//Get all headers from the response
		Headers headersList = response.getHeaders();
		//Headers headersList = response.headers();
		
		for(Header header : headersList) 
		{
			System.out.println("Header Key/Name: [" + header.getName() + "] Header Value: [" + header.getValue() + "]");
		}
		
		//Get specific header from the response
		String contentType = response.getHeader("Content-Type");
		String server = response.getHeader("Server");
		String contentEncoding = response.getHeader("Content-Encoding");
		
		System.out.println("Header Content Type: [" + contentType + "]");
		System.out.println("Header Server: [" + server + "]");
		System.out.println("Header Content Encoding: [" + contentEncoding + "]");
		
		//Validate response headers
		Assert.assertEquals("application/json; charset=utf-8", contentType);
		Assert.assertEquals("nginx/1.17.10 (Ubuntu)", server);
		//Assert.assertEquals("", contentEncoding, "Validating Header Content-Encoding:");
	}
}

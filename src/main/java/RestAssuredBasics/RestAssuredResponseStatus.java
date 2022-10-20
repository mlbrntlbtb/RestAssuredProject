package RestAssuredBasics;

import org.testng.annotations.*;
import io.restassured.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredResponseStatus {
  @Test
  public void Test() 
  {
	  //Specify base URI or URL
	  RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
	  //RestAssured.baseURI = "https://demoqa.com/Account/v1/Userssssss";
	  
	  //Get the RequestSpecification of the request to be sent to the server
	  RequestSpecification httpRequest = RestAssured.given();
	  
	  //Use HTTP request - GET method
	  Response response = httpRequest.get();
	  
	  //Get the status code of the request. If success, status code will be 200 - 299, else failed e.g. 401
	  int statusCode = response.getStatusCode();
	  //Status line retrieves HTTP protocol version, Status code, and status value e.g. 'HTTP/1.1 200 OK'
	  String statusLine = response.getStatusLine();
	  //Retrieves message body of the response
	  String messageBody = response.prettyPrint();
	  
	  //Validate
	  System.out.println("Status Code: [" + statusCode + "]");
	  System.out.println("Status Line: [" + statusLine + "]");
	  System.out.println("Message Body: [" + messageBody + "]");
	  
	  if(statusCode >= 200 & statusCode <= 299)
		  System.out.println("Request successful.");
	  else
		  System.out.println("Request failed.");
	  
	  
  }
}

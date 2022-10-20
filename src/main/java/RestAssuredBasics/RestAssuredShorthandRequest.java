package RestAssuredBasics;
import java.util.HashMap;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredShorthandRequest 
{
	@Test
	public void Test() 
	{
		String baseURL = "https://gorest.co.in/public/v2/users";
		String authorizationKey = "Bearer 94fa7aafa024a23bfdba2ee7215214d9abc48dc85fadd3a4099a1f692eedb028";
		HashMap<String,String> requestParams = new HashMap<String,String>();
		requestParams.put("name", "genos");
		requestParams.put("email", "genos@gmail.com");
		requestParams.put("gender", "male");
		requestParams.put("status", "active");
		
		
		RequestSpecification httpRequest = (RequestSpecification) RestAssured.given()
				.baseUri(baseURL)
				.header("Authorization", authorizationKey)
				.contentType(ContentType.JSON)
				.body(requestParams)
				.get();
	}
}

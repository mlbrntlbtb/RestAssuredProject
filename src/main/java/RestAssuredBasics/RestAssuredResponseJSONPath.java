package RestAssuredBasics;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import Models.Book;
import Models.BookPOJO;

public class RestAssuredResponseJSONPath 
{
	@Test
	public void Test() 
	{
		RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Book";
		//RestAssured.baseURI = "https://demoqa.com/BookStore/v1/Books";
		
		RequestSpecification httpRequest = RestAssured.given();	
		String targetResource = "ISBN";
		String targetValue = "9781449325862";
		//Response response = httpRequest.queryParam(targetResource, targetValue).get("/Book");
		httpRequest.contentType(ContentType.JSON);
		Response response = httpRequest.get("?" + targetResource + "=" + targetValue);
		//Response response = httpRequest.get();
		
		//Test JSON Path and JSON Expression here: https://jsonpath.com/
		//JSON Path Format: $.[childname-node][index] or $.'childname-node[index]' or for wild-card &.[childname-node].*
		//JSON Path Format: $.[childname-node][index-1st-item,index-2nd-item] 
		//Sample JSON path: $.[books][0,1]
		
		//JSON Expression Format: [JSON-path][?(expression)]
		//JSON Expression Format: [JSON-path][?(@.childname-node operator value)
		//Sample JSON Expression: $.[books][?(@.pages==472)]
		
		
		//Retrieve body of the response
		//ResponseBody body = response.getBody();
		//ResponseBody bodyMessage = response.body();
		
		//Get JSON path of the response
		JsonPath jsonPath = response.jsonPath();
		
		//String bodyAsString = body.asString();
		//JsonPath jsonPath = new JsonPath(bodyAsString);
		
		//Print in JSON format
//		String bodyMessageInJsonFormat = jsonPath.prettyPrint();
//		System.out.println("JSON Format: [" + bodyMessageInJsonFormat + "]");
		
		//Print target childname-nodes from JSON path
//		System.out.println("ISBN: [" + jsonPath.get("isbn") + "]");
//		System.out.println("Title: [" + jsonPath.get("title") + "]");
//		System.out.println("Subtitle: [" + jsonPath.get("subTitle") + "]");
//		System.out.println("Author: [" + jsonPath.get("author") + "]");
//		System.out.println("Publish Date: [" + jsonPath.get("publish_date") + "]");
//		System.out.println("Publisher: [" + jsonPath.get("publisher") + "]");
//		System.out.println("Pages: [" + jsonPath.get("pages") + "]");
//		System.out.println("Description: [" + jsonPath.get("description") + "]");
//		System.out.println("Website: [" + jsonPath.get("website") + "]");
		
		//Using jsonPath dot(.) notation
//		System.out.println("ISBN: [" + jsonPath.get("books[0].isbn") + "]");
//		System.out.println("Title: [" + jsonPath.get("books[0].title") + "]");
//		System.out.println("Subtitle: [" + jsonPath.get("books[0].subTitle") + "]");
//		System.out.println("Author: [" + jsonPath.get("books[0].author") + "]");
//		System.out.println("Publish Date: [" + jsonPath.get("books[0].publish_date") + "]");
//		System.out.println("Publisher: [" + jsonPath.get("books[0].publisher") + "]");
//		System.out.println("Pages: [" + jsonPath.get("books[0].pages") + "]");
//		System.out.println("Description: [" + jsonPath.get("books[0].description") + "]");
//		System.out.println("Website: [" + jsonPath.get("books[0].website") + "]");
		
//		//Deserialize JSON array to list of String
		List<String> books = jsonPath.getList("books.title");
//		
//		for(String book : books) 
//		{
//			System.out.println("Book: [" + book + "]");
//		}
//		
		//Deserialize JSON to POJO (java object) 
		//List<Book> bookList = jsonPath.getList("books", Book.class);
//		Book[] bookList = jsonPath.getObject("books", Book[].class);
//		
//		int index = 1;
//		for(Book book : bookList) 
//		{
//			System.out.println("Book No. [" + index + "]");
//			System.out.println("ISBN: [" + book.isbn + "]");
//			System.out.println("Title: [" + book.title + "]");
//			System.out.println("Subtitle: [" + book.subTitle + "]");
//			System.out.println("Author: [" + book.author + "]");
//			System.out.println("Publish Date: [" + book.publish_date + "]");
//			System.out.println("Publisher: [" + book.publisher + "]");
//			System.out.println("Pages: [" + book.pages + "]");
//			System.out.println("Description: [" + book.description + "]");
//			System.out.println("Website: [" + book.website + "]\n\n");
//			index++;
//		}
		
		//Single item array
		BookPOJO book = response.getBody().as(BookPOJO.class);
		System.out.println("ISBN: [" + book.isbn + "]");
		System.out.println("Title: [" + book.title + "]");
		System.out.println("Subtitle: [" + book.subTitle + "]");
		System.out.println("Author: [" + book.author + "]");
		System.out.println("Publish Date: [" + book.publish_date + "]");
		System.out.println("Publisher: [" + book.publisher + "]");
		System.out.println("Pages: [" + book.pages + "]");	
		System.out.println("Description: [" + book.description + "]");
		System.out.println("Website: [" + book.website + "]");
	}
}



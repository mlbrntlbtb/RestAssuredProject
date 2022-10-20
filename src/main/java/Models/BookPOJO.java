package Models;

public class BookPOJO 
{
	public String isbn;
	public String title;
	public String subTitle;
	public String author;
	public String publish_date;
	public String publisher;
	public int pages;
	public String description;
	public String website;
	
	public BookPOJO() 
	{
		//Default constructor required for JSON Deserialization
	}
	
	public BookPOJO(String isbn, String title, String subTitle, String author, String publish_date, String publisher, int pages, String description, String website) 
	{
		this.isbn = isbn;
		this.title = title;
		this.subTitle = subTitle;
		this.author = author;
		this.publish_date = publish_date;
		this.publisher = publisher;
		this.pages = pages;
		this.description = description;
		this.website = website;
	}
	
	//Standard to include getters and setters
}

package RestAssuredBasics;

public class RestAssuredPOJOforResponse{

	public Integer id;
	public String name;
	public String email;
	public String gender;
	public String status;

	public RestAssuredPOJOforResponse() 
	{
		
	}
	
	public RestAssuredPOJOforResponse(Integer id, String name, String email, String gender, String status) 
	{
		this.id = id;
		this.name = name;
		this.email = email;
		this.gender = gender;
		this.status = status;
	}
}

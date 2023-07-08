import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;



public class JiraTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub'
		
		RestAssured.baseURI="http://localhost:8080/";
		
		SessionFilter session= new SessionFilter();
		
		//Lagin 
		given().log().all().header("Content-Type","application/json")
		.body("{\"username\":\"skk89\",\"password\":\"maaadishakti\"}").filter(session).when().post("rest/auth/1/session")
		.then().log().all().assertThat().statusCode(200);
		
		

		
		//Add Comments
	String commentMessage= "Added comment through Java code now";	
	String resonse_AddComments=	given().pathParam("key", "10004").log().all().header("Content-Type","application/json")
		.body("{\r\n"
				+ "  \"body\": \""+commentMessage+"\",\r\n"
				+ "  \"visibility\": {\r\n"
				+ "    \"type\": \"role\",\r\n"
				+ "    \"value\": \"Administrators\"\r\n"
				+ "  }\r\n"
				+ "}").filter(session).when().post("/rest/api/2/issue/{key}/comment")
		.then().log().all().assertThat().statusCode(201).extract().asString();
		JsonPath js=new JsonPath(resonse_AddComments);
		String commentID=js.getString("id");
		
		//Add Attachments
		given().pathParam("key", "10004").log().all().header("X-Atlassian-Token","no-check")
		.multiPart("file", new File("Text.txt")).header("Content-Type","multipart/form-data")
		.filter(session).when().post("rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
		//GetIssue
		String issueDetails=given().pathParam("key", "10004").queryParam("fields", "comment").log().all()
		.filter(session).when().get("rest/api/2/issue/{key}")
		.then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js1= new JsonPath(issueDetails);
		int commentCount=js1.getInt("fields.comment.comments.size()");
		for(int i=0;i<commentCount;i++) {
			if(js1.get("fields.comment.comments["+i+"].id").toString().equalsIgnoreCase(commentID)) {
				String actualMessage=js1.getString("fields.comment.comments["+i+"].body").toString();
				org.junit.Assert.assertEquals(actualMessage,commentMessage);
				
				 	
			}
		}

	}
	
	
	


}
	



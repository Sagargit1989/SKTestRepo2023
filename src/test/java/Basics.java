import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;

import Files.Payload;
import Files.ReusableMethods;
import groovy.lang.Newify;

public class Basics {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://rahulshettyacademy.com";
//		//AddPlace
//		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
//		.body(Payload.addPlace()).when().post("maps/api/place/add/json")
//		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
//		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
//		
		//AddPlace - getting json payload from outside
		//Content of the file to String - Content of File Converted to Bytes- Byte data to String
		String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String( Files.readAllBytes( Paths.get("D:\\Rahul Provided Docs\\AddPlace.json")))).when().post("maps/api/place/add/json")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)").extract().response().asString();
		
		JsonPath js=new JsonPath(response); //for parsing JSON
		String placeId= js.get("place_id");
		
		System.out.println("---"+placeId+"----");
		
		//update Place
		String newAddress="1000 Spring walk, USA";
		String response_UpdatePlace=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId).header("Content-Type","application/json")
		.body(Payload.updatePlace(placeId,newAddress)).when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1=new JsonPath(response_UpdatePlace);
		String msg=js1.get("msg");
		System.out.println("---"+msg+"----");
		
		
		//Get Place
	String response_GetPlace=given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
	.when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200).body("address", equalTo(newAddress)).extract().response().asString();
		
	JsonPath js2=ReusableMethods.rawToJson(response_GetPlace);
	String actualAddress= js2.get("address");
	Assert.assertEquals(actualAddress, newAddress);
		
	}

}

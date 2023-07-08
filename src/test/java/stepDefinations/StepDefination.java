package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import POJO.AddPlace;
import POJO.Location;
import Resources.APIResources;
import Resources.TestDataBuild;
import Resources.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class StepDefination extends Utils{
	
	static ResponseSpecification resspec;
	static RequestSpecification res;
	static Response response;
	TestDataBuild testData=new TestDataBuild();
    static String placeid;
	
	
	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
		 resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
			
		 res=given().spec(requestSpecification())
		.body(testData.addPlacePayLoad(name,language,address));
		 
	}

//	@When("User calls {string} with Post request")
//	public void user_calls_with_post_request(String resource) {
//		
//		APIResources resourceAPI= APIResources.valueOf(resource);
//		
////		 response =res.when().post("/maps/api/place/add/json").
////					then().spec(resspec).extract().response();
//		
//		response =res.when().post(resourceAPI.getResource()).
//				then().spec(resspec).extract().response();
//		
//	
//	}
	
	@When("User calls {string} with {string} request")
	public void user_calls_with_request(String resource, String httpMethod) {
		
		APIResources resourceAPI= APIResources.valueOf(resource);
		
//		 response =res.when().post("/maps/api/place/add/json").
//					then().spec(resspec).extract().response();
		
		if(httpMethod.equalsIgnoreCase("POST"))
		response =res.when().post(resourceAPI.getResource()).
				then().spec(resspec).extract().response();
		else if(httpMethod.equalsIgnoreCase("GET"))
			response =res.when().get(resourceAPI.getResource()).
			then().spec(resspec).extract().response();
			
			
		
	}
	


	@Then("The API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer int1) {
		
	org.junit.Assert.assertEquals(response.getStatusCode(), 200);
	 
	}

	@Then("The {string} in response body is {string}")
	public void the_in_response_body_is(String Key, String exValue) {
		//String responseString=response.asString();
		//System.out.println(responseString);
		//JsonPath js= new JsonPath(responseString);
		//assertEquals(js.get(Key).toString(), exValue);
		
		org.junit.Assert.assertEquals(getJsonPath(response, Key).toString(), exValue);
	    }
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedlName, String resourceName) throws IOException {
		placeid=getJsonPath(response, "place_id");
		
		res=given().spec(requestSpecification()).queryParam("place_id", placeid);
		user_calls_with_request(resourceName,"GET");
		String actualName= getJsonPath(response, "name");
		org.junit.Assert.assertEquals(actualName, expectedlName);
		
		}
	@Given("DeletePlace API payload")
	public void delete_place_api_payload() throws IOException {
		
		 res=given().spec(requestSpecification())
					.body(testData.deletePlace(placeid));
	
	}
}

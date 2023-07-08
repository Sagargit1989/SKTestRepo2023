import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;
import POJO.ResponseAddPlace;


public class NewSpecBuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RestAssured.baseURI="https://rahulshettyacademy.com";
		
		AddPlace ap= new AddPlace();
		ap.setAccuracy("11");
		ap.setAddress("100, Coco Homes, Bangalore 10");
		ap.setName("Sagar House");
		ap.setLanguage("French-IN");
		ap.setPhone_number("(+91) 983 893 3937");
		ap.setWebsite("http://google.com");
		
		Location loc= new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		ap.setLocation(loc);
		
		List<String> typeslist=new ArrayList();
		typeslist.add("Shoe Park");
		typeslist.add("Shop");
		
		ap.setTypes(typeslist);
		
		
		RequestSpecification req =new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123")
				.setContentType(ContentType.JSON).build();

		ResponseSpecification resspec =new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res=given().spec(req)
		.body(ap);
		
		Response response =res.when().post("/maps/api/place/add/json").
		then().spec(resspec).extract().response();
		
		String responseString=response.asString();
		System.out.println(responseString);


		
//		String response_AddPalce=given().queryParam("key", "qaclick123").body(ap)
//		.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().asString();
		
		
//		//getting resonse using POJO
//		ResponseAddPlace response_AddPalce=given().queryParam("key", "qaclick123").body(ap)
//				.when().post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().as(ResponseAddPlace.class);
//		
//		System.out.println(response_AddPalce.getStatus());
//		System.out.println(response_AddPalce.getPlace_id());
	}

}

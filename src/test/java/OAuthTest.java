import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;



import POJO.Api;
import POJO.GetCourse;
import POJO.WebAutomation;
import io.restassured.parsing.Parser;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import io.restassured.response.ResponseBody;

public class OAuthTest {

	public static void main(String[] args) throws InterruptedException {

		
		String[] expectedCourses= {"Selenium Webdriver Java","Cypress","Protractor"};
// TODO Auto-generated method stub

		String url = "https://rahulshettyacademy.com/getCourse.php?code=4%2F0AZEOvhUlSD9h6pnjwIvWT5UDjXXRWndbGi4nQWSFxc9DIQHWkbgpOV7rZXryp-7Nj03bPA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=4&prompt=none";

		String partialcode = url.split("code=")[1];

		String code = partialcode.split("&scope")[0];

		System.out.println(code);

		String response =

				given()

						.urlEncodingEnabled(false)

						.queryParams("code", code)

						.queryParams("client_id",
								"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")

						.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")

						.queryParams("grant_type", "authorization_code")

						.queryParams("state", "verifyfjdss")

						.queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")

						// .queryParam("scope",
						// "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")

						.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")

						.when().log().all()

						.post("https://www.googleapis.com/oauth2/v4/token").asString();

// System.out.println(response);

		JsonPath jsonPath = new JsonPath(response);

		String accessToken = jsonPath.getString("access_token");

		System.out.println(accessToken);

//		String r2 = given().contentType("application/json").
//
//				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
//
//				.when()
//
//				.get("https://rahulshettyacademy.com/getCourse.php")
//
//				.asString();
//
//		System.out.println(r2);
		//Using POJO clases to get the response
		GetCourse gc = given().contentType("application/json").

				queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)

				.when()

				.get("https://rahulshettyacademy.com/getCourse.php")

				.as(GetCourse.class);

		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getInstructor());
		
	List<Api> apicourses=	gc.getCourses().getApi();
	System.out.println(apicourses.get(0).getCourseTitle());
	
	for(int i=0;i<apicourses.size();i++) {
		if(apicourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
			System.out.println("Price for SoapUI Webservices testing course = "+apicourses.get(i).getPrice());
			
		}
	}
	
	
	//get all the courses of WebAutomation
	
	List<WebAutomation> weAutomationcourses =gc.getCourses().getWebAutomation();
	
	List<String> actualCourses= new ArrayList<String>();
	
	System.out.println("All courses under Web Automation");
	for(int i=0;i<weAutomationcourses.size();i++) {
		System.out.println(weAutomationcourses.get(i).getCourseTitle());
		actualCourses.add(weAutomationcourses.get(i).getCourseTitle().toString());
		
	}
	
	List<String> expcourses= Arrays.asList(expectedCourses);
	
	org.junit.Assert.assertTrue(actualCourses.equals(expcourses));
		

	}

}
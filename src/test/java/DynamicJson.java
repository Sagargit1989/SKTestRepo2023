import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	
	@Test(enabled = true,dataProvider = "BooksData")
	public void AddBook(String aisle,String isbn) {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String response_AddBook= given().log().all().header("Content-Type","application/json").body(Payload.addBook(aisle,isbn)).when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= ReusableMethods.rawToJson(response_AddBook);
		String id=js.get("ID");
		System.out.println(id);
		
	
	}
	
	@DataProvider(name="BooksData")
	public Object[][] getData() {
		//Arry= Collecton of elements
		//Mutidimensional Arry= Collection arrays
		return new Object[][] {{"333","skk"},{"444","lkk"},{"555","mkk"}};
	}
	
	@Test(enabled = false)
	public void getBook() {
		RestAssured.baseURI="http://216.10.245.166";
		
		String response_GetBook= given().log().all().queryParam("AuthorName", "Sagar Kumar").header("Content-Type","application/json").when().post("/Library/GetBook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js= ReusableMethods.rawToJson(response_GetBook);
		
		System.out.println(js.getString("book_name"));
		
	}

}

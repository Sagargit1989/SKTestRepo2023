package Files;



import io.restassured.path.json.JsonPath;

public class SumValidation {
	
	@org.junit.Test
	public void sumOfCourses() {
		JsonPath js= new JsonPath(Payload.coursePrice());
		int noOFCourses=js.getInt("courses.size()");
		int sum=0;
		
		for(int i=0;i<noOFCourses;i++) {
		int price=	js.get("courses["+i+"].price");
		int copies=	js.get("courses["+i+"].copies");
		System.out.println(price);
		System.out.println(copies);
		int amount= price*copies;
		sum= sum+amount;
			
		}
	
		System.out.println("Total amount for all the courses = "+sum);
		
		int purchaseAmount= js.get("dashboard.purchaseAmount");
		org.junit.Assert.assertEquals(purchaseAmount, sum);
	}

}

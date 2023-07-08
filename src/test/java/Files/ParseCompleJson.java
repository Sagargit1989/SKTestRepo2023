package Files;

import io.restassured.path.json.JsonPath;

public class ParseCompleJson {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js= new JsonPath(Payload.coursePrice());
		
		//Print no of courses
		int noOfCourse=js.getInt("courses.size()");
		System.out.println("Number of Corses = "+noOfCourse);
		
		//Print purchase Amount
		int purchaseAmount= js.getInt("dashboard.purchaseAmount");
		System.out.println("Purchase Amount = "+purchaseAmount);
		
		//Print title of the first course
		String titleOFFirstCourse=js.getString("courses[0].title");
		System.out.println("Title of first course = "+titleOFFirstCourse);
		
		//Print all courses titles and their respective prices
		for(int i=0;i<noOfCourse;i++) {
			js.get("courses["+i+"].title");
			js.get("courses["+i+"].price");
			System.out.println("Course Title = "+js.get("courses["+i+"].title"));
			System.out.println("Course Price = "+js.get("courses["+i+"].price"));
			
		}
		
		
		//Print no.of copies sold by RPA
		
		for(int i=0;i<noOfCourse;i++) {
			
			if(js.get("courses["+i+"].title").toString().equalsIgnoreCase("RPA")) {
				System.out.println("No of copies sold by RPA = "+js.get("courses["+i+"].copies"));
				break;
			}
			
		}
		
	}

}

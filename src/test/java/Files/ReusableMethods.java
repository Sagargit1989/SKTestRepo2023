package Files;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
	
	public static JsonPath rawToJson(String response_GetPlace) {
		 JsonPath js= new JsonPath(response_GetPlace);
				 return js;
	}

}

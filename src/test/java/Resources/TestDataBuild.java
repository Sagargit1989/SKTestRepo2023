package Resources;

import java.util.ArrayList;
import java.util.List;

import POJO.AddPlace;
import POJO.Location;

public class TestDataBuild {
	
      public AddPlace addPlacePayLoad(String name, String language, String address) {
		AddPlace ap= new AddPlace();
		ap.setAccuracy("11");
		ap.setAddress(address);
		ap.setName(name);
		ap.setLanguage(language);
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
		
		return ap;
	}
      
      public String deletePlace(String placeId) {

    	  return "{\r\n"
    	  		+ "  \"place_id\": \""+placeId+"\"\r\n"
    	  		+ "}" ;
      }

}

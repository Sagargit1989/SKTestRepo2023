import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class GetTestDataFromExcel {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		TestDataDrivenFromXL getDataFromXL= new TestDataDrivenFromXL();
		ArrayList<Object> dataList= getDataFromXL.getData(); 
		System.out.println(dataList.get(0));
		System.out.println(dataList.get(1));
		System.out.println(dataList.get(2));
		
//		Iterator<Object>  xlData= dataList.iterator();
//		while (xlData.hasNext()) {
//			Object object = (Object) xlData.next();
//			System.out.println(object.toString());
//			
//		}

	}

}

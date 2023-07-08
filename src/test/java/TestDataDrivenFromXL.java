import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class TestDataDrivenFromXL {
	
	public ArrayList<Object> getData() throws IOException {

		
		ArrayList<Object> list= new ArrayList<Object>();
		
		FileInputStream fis= new FileInputStream("C:\\Users\\Asus\\eclipse-workspace\\APITesting\\Test.xlsx");
		XSSFWorkbook workbook= new XSSFWorkbook(fis);
		
		int Sheets=workbook.getNumberOfSheets();
		
		//find test Case column
		for(int i=0;i<Sheets;i++) {
			String sheetName=workbook.getSheetAt(i).getSheetName();
			
			if(sheetName.equalsIgnoreCase("Test Data")) {
				XSSFSheet sheet=	workbook.getSheetAt(i);
				Iterator<Row> rows=sheet.iterator();
				
				Row  firstRow=rows.next();
				
				Iterator<Cell> cells = firstRow.iterator();
				int k=0;
				int column = 0;
			
			while (cells.hasNext()) {
				Cell cellValue = (Cell) cells.next();
				if(cellValue.getStringCellValue().equalsIgnoreCase("Test Cases")) {
					column=k;
				}
				k++;
				
			}
			System.out.println(column);
			
			//once column is identified scan entire Test case call to find Registration test case row
		while(rows.hasNext()) {
			Row row= rows.next();
			if(row.getCell(column).getStringCellValue().equalsIgnoreCase("RegistrationTC")) {
				//After you get Registarion pull all the data
				Iterator<Cell> cell= row.cellIterator();
				while(cell.hasNext()) {
					System.out.println(cell.next().getStringCellValue());
					list.add(cell.next().getStringCellValue().toString());
				}
			}
		}
				
				
			}
			
			
		}
		return list;
		
		

	
		
	}


}

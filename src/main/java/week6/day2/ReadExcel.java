package week6.day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	public static void main(String[] args) throws IOException {
		XSSFWorkbook wb = new XSSFWorkbook("./Data/New Microsoft Excel Worksheet.xlsx");
		XSSFSheet sheet = wb.getSheet("Sheet1");
		
		int lastRowNum = sheet.getLastRowNum();
		int lastCellNum = sheet.getRow(0).getLastCellNum();
		System.out.println(lastRowNum);
		System.out.println(lastCellNum);
		
		for (int i = 1; i <= lastRowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			for (int j = 1; j < lastCellNum; j++) {
				XSSFCell cell = row.getCell(j);
				System.out.println(cell);
				
			}
			
		}

	}

}

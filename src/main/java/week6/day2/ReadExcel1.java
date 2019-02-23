package week6.day2;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel1 {
	
	static String[][] data;

	public static Object[][] readData(String dataSheetName) {
		XSSFWorkbook wb;
		try {
			wb = new XSSFWorkbook("./Data/"+dataSheetName+".xlsx");

			XSSFSheet sheet = wb.getSheet("Sheet1");

			int lastRowNum = sheet.getLastRowNum();
			int lastCellNum = sheet.getRow(0).getLastCellNum();

			data = new String[lastRowNum][lastCellNum];
			System.out.println(lastRowNum);
			System.out.println(lastCellNum);

			for (int i = 1; i <= lastRowNum; i++) {
				XSSFRow row = sheet.getRow(i);
				for (int j = 1; j < lastCellNum; j++) {
					XSSFCell cell = row.getCell(j);
					data[i - 1][j] = cell.getStringCellValue();

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;

	}
}

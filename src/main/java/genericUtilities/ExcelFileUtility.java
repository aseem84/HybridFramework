package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtility {
	public final String fileName = ".\\src\\test\\resources\\TestData.xlsx";

	public String readExcelData(String sheetName, int rowNo, int colNo) throws Throwable {
		String excelFilePath = ".\\src\\test\\resources\\TestData.xlsx";
		FileInputStream fise = new FileInputStream(excelFilePath);
		Workbook wb = WorkbookFactory.create(fise);
		String excelValue = wb.getSheet(sheetName).getRow(rowNo).getCell(colNo).getStringCellValue();
		/*
		 * Sheet sh = wb.getSheet(sheetName); Row rw = sh.getRow(rowNo); Cell cl =
		 * rw.getCell(colNo); String excelValue = cl.getStringCellValue();
		 */
		wb.close();
		return excelValue;
	}

	public String[][] readExcelData(String sheetName) throws EncryptedDocumentException, IOException {
		String[][] data = null;
		FileInputStream fis = new FileInputStream(fileName);
		Workbook wb = WorkbookFactory.create(fis);

		Sheet sh = wb.getSheet(sheetName);

		int totalRows = sh.getLastRowNum() + 1;
		int totalCells = sh.getRow(0).getLastCellNum();
		data = new String[totalRows - 1][totalCells]; // for method 2 this statement is not necessary

		// Method-01 START
//		for (int i = 1; i < totalRows; i++) {
//            Row row = sh.getRow(i);
//            if (row == null) {
//                continue;
//            }
//            for (int j = 0; j < totalCells; j++) {
//            	Cell cell = row.getCell(j);
//                data[i - 1][j] = cell.getStringCellValue();
//            }
//		}
//		wb.close();
//		List<String[]> resultList1 = new ArrayList<>();
//		for (String[] row : data) { 
//			if (Arrays.stream(row).anyMatch(item -> item != null)) 
//			{ 
//				resultList1.add(row); 
//			} 
//		}
//		String[][] result1 = resultList1.toArray(new String[0][]);
//		return result1;
		// ************************************************************************

		// Method-02 START

		List<String[]> resultList = new ArrayList<>();
		for (int i = 1; i < totalRows; i++) {
			Row row = sh.getRow(i);
			if (row == null) {
				continue;
			}
			String[] rowData = new String[totalCells];
			for (int j = 0; j < totalCells; j++) {
				//Cell cell = row.getCell(j);
				Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				rowData[j] = cell.getStringCellValue().trim();
			}
			resultList.add(rowData);
		}
		wb.close(); // Convert ArrayList to 2D array
		String[][] result = resultList.toArray(new String[0][0]);
		return result;

		// Method-02 END
		
	}

}

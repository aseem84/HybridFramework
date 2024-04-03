package genericUtilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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
		 * Sheet sh = wb.getSheet(sheetName); 
		 * Row rw = sh.getRow(rowNo); 
		 * Cell cl = rw.getCell(colNo); 
		 * String excelValue = cl.getStringCellValue();
		 */
		wb.close();
		return excelValue;
	}
	
	public String[][] readExcelData(String sheetName) throws EncryptedDocumentException, IOException
	{
		String[][] data = null;
		FileInputStream fis = new FileInputStream(fileName);
		Workbook wb = WorkbookFactory.create(fis);
		
		Sheet sh = wb.getSheet(sheetName);
		
		int totalRows = sh.getLastRowNum()+1;
		int totalCells = sh.getRow(0).getLastCellNum();
		data = new String[totalRows-1][totalCells];
		
		for(int i = 1; i < totalRows; i++)
		{
			for(int j = 0; j < totalCells; j++)
			{
				data[i-1][j] = sh.getRow(i).getCell(j).getStringCellValue();
			}
		}
		wb.close();
		return data;
	}

}

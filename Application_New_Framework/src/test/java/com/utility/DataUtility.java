package com.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
/**
 * DataUtility class is used to fetch the data from external Files -> Properties & Excel File.
 */
public class DataUtility implements FrameworkConstants{
	
	/**
	 * It is used to fetch the data from properties file
	 * @param key
	 * @return value
	 * @throws IOException
	 */

	public String readDataFromProperties(String key) throws IOException {

		FileInputStream fis = new FileInputStream(PROPERTIES_PATH);
		Properties pro = new Properties();
		pro.load(fis);
		return pro.getProperty(key);
	}
	
	/**
	 * It is used to fetch single data from excel file
	 * @param sheetName
	 * @param rowNum
	 * @param cellNum
	 * @return value
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */

	public String readDataFromExcel(String sheetName, int rowNum, int cellNum) throws EncryptedDocumentException, IOException {

		FileInputStream fis = new FileInputStream(EXCEL_PATH);
		Workbook book = WorkbookFactory.create(fis);
		Sheet sh = book.getSheet(sheetName);
		return sh.getRow(rowNum).getCell(cellNum).toString();
	}

}
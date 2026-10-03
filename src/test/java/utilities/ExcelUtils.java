package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
 

 
public class ExcelUtils {
	 private Workbook workbook;
	    private Sheet sheet;
	    

	    // Constructor to load Excel file and sheet
	    public ExcelUtils(String filePath, String sheetName) {
	        try {
	            FileInputStream fis = new FileInputStream(filePath);
	            workbook = new XSSFWorkbook(fis);
	            sheet = workbook.getSheet(sheetName);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Get row count
	    public int getRowCount() {
	        return sheet.getPhysicalNumberOfRows();
	    }

	    // Get column count
	    public int getColumnCount() {
	        return sheet.getRow(0).getLastCellNum();
	    }

	    // Get cell data by row and column
	    public String getCellData(int rowNum, int colNum) {
	        Row row = sheet.getRow(rowNum);
	        Cell cell = row.getCell(colNum);

	        if (cell == null) {
	            return "";
	        }

	        switch (cell.getCellType()) {
	            case STRING:
	                return cell.getStringCellValue();
	            case NUMERIC:
	                if (DateUtil.isCellDateFormatted(cell)) {
	                    return cell.getDateCellValue().toString();
	                } else {
	                    return String.valueOf(cell.getNumericCellValue());
	                }
	            case BOOLEAN:
	                return String.valueOf(cell.getBooleanCellValue());
	            case FORMULA:
	                return cell.getCellFormula();
	            case BLANK:
	                return "";
	            default:
	                return "";
	        }
	    }

	    // Convert entire sheet to 2D Object array (for DataProvider)
	    public Object[][] getSheetData() {
	        int rowCount = getRowCount();
	        int colCount = getColumnCount();

	        Object[][] data = new Object[rowCount - 1][colCount]; // skip header row

	        for (int i = 1; i < rowCount; i++) {
	            for (int j = 0; j < colCount; j++) {
	                data[i - 1][j] = getCellData(i, j);
	            }
	        }
	        return data;
	    }
	    
	    // ✅ Write result back into Excel
	    public void setCellData(int rowNum, int colNum, String value,String filePath) {
	        try {
	            Row row = sheet.getRow(rowNum);
	            if (row == null) row = sheet.createRow(rowNum);

	            Cell cell = row.getCell(colNum);
	            if (cell == null) cell = row.createCell(colNum);

	            cell.setCellValue(value);

	            FileOutputStream fos = new FileOutputStream(filePath);
	            workbook.write(fos);
	            fos.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}

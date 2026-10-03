package utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {
	@DataProvider(name = "excelData")
    public Object[][] getData() {
        String filePath = "src/test/resources/TestData.xlsx";
        String sheetName = "UserData";

        ExcelUtils excel = new ExcelUtils(filePath, sheetName);
        return excel.getSheetData();
    }

}

package testCases;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.UserRegistrationPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.ExcelUtils;
//@Listeners(utilities.ExtentReportManager.class)

public class UserRegistrationTest extends BaseClass {
	// Initialize Page Object
	
	@Test(groups = {"regression"})
	 public void verifyAccountRegistrationTest()
	
	{  UserRegistrationPage page = new UserRegistrationPage(driver);
		page.clickRegistrationLink();
		Assert.assertEquals(page.getTitle(),prop.getProperty("expectedTitle1"));
		page.enterUsername(randomeString().toLowerCase());
		page.enterFirstName(randomeString().toLowerCase());
		page.enterLastName(randomeString().toLowerCase());
		page.enterEmail(randomeAlphaNumaric()+"@gmail.com");
		page.selectCountryByVisibleText("India");
		page.enterPassword("Balwadangi@90Year");
		page.clickRegistrationButton();
	}
	
	
	@Test 
	public void VerifyUserLoginTest()
	{  
	UserRegistrationPage page = new UserRegistrationPage(driver);
	  HomePage homepage = new HomePage(driver);
	  loger.info("Starting login test...");
	  page.clickOnLoginLink();
	  homepage.enterUsername(prop.getProperty("Username2"));
	  homepage.enterPassword(prop.getProperty("Password2"));
	  homepage.clickLoginButton();
	 Assert.assertEquals(homepage.getAccountText(),prop.getProperty("homePagetxt"));
	 homepage.clickLogOutButton();
	 
	 
		 
	}
	
	@Test(groups = {"smoke"})
	public void VerifyUserLogin2Test()
	
	{  
		UserRegistrationPage page = new UserRegistrationPage(driver);
	  HomePage homepage = new HomePage(driver);
	  loger.info("Starting login test...");
	  page.clickOnLoginLink();
	  homepage.enterUsername(prop.getProperty("Username2"));
	  homepage.enterPassword(prop.getProperty("Password2"));
	  homepage.clickLoginButton();
	 Assert.assertEquals(homepage.getAccountText(),prop.getProperty("homePagetxt"));
	 homepage.clickLogOutButton();
	 
		 
	}

	 @Test(groups = {"smoke"},dataProvider = "excelData",dataProviderClass = DataProviders.class)
	    public void verifyAccountLogin(String username,String password,String result) {
	        ExcelUtils excel = new ExcelUtils("src/test/resources/TestData.xlsx","UserData");
	        String filePath="src/test/resources/TestData.xlsx";

	        try {
	            // Run your test steps

	    		UserRegistrationPage page = new UserRegistrationPage(driver);
	    	  HomePage homepage = new HomePage(driver);
	    	  loger.info("Starting login test...");
	    	  page.clickOnLoginLink();
	    	  homepage.enterUsername(username);
	    	  homepage.enterPassword(password);
	    	  homepage.clickLoginButton();
	    	 Assert.assertEquals(homepage.getAccountText(),prop.getProperty("homePagetxt"));
	    	 homepage.clickLogOutButton();

	            // ✅ Mark PASS in Excel (row index matches test data row)
	            excel.setCellData(/* rowNum */ 1, /* colNum */ 2, "PASS",filePath); 
	        } catch (Exception e) {
	            // ✅ Mark FAIL in Excel
	            excel.setCellData(/* rowNum */ 1, /* colNum */ 2, "FAIL",filePath);
	        }
	    }
	

}

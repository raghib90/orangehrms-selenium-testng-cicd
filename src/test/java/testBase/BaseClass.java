package testBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Properties;
import java.util.UUID;
import java.util.Date;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	public static WebDriver driver;
	//public WebDriver driver;
	public Properties prop;
	public Logger loger;
	//@BeforeClass
	public void setUp() {
		loger=LogManager.getLogger(this.getClass());
		 try {
	            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
	            prop = new Properties();
	            prop.load(fis);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    driver=new ChromeDriver();
		// Implicit wait
          driver.manage().timeouts().implicitlyWait(
        	    Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait")))
        	);
         // Maximize window
        driver.manage().window().maximize();
        // Navigate to application URL
        driver.get(prop.getProperty("baseUrl2"));
    
            
	}
	
	@BeforeClass(groups={"smoke","Master"})
	@Parameters({"browser", "baseUrl"})
	public void setUpUsingXML(String browser,String baseUrl) {
		loger=LogManager.getLogger(this.getClass());
		
		 try {
	            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
	            prop = new Properties();
	            prop.load(fis);
	        } catch (IOException e) {
	           
	        }
		  if (browser.equalsIgnoreCase("chrome")) {
			  loger.info("Browser started");
	            driver = new ChromeDriver();
	        } else if (browser.equalsIgnoreCase("firefox")) {
	            driver = new FirefoxDriver();
	        } else if (browser.equalsIgnoreCase("edge")) {
	            driver = new EdgeDriver();
	        } else {
	            throw new IllegalArgumentException("Browser not supported: " + browser);
	        }
	    
		// Implicit wait
          driver.manage().timeouts().implicitlyWait(
        	    Duration.ofSeconds(Integer.parseInt(prop.getProperty("implicitWait")))
        	);
         // Maximize window
        driver.manage().window().maximize();
        loger.debug("Browser window maximized successfully");
        // Navigate to application URL
        driver.get(baseUrl);
    
            
	}
	
	 @AfterClass(groups={"smoke","Master"})
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit(); // closes all browser windows and ends the WebDriver session
	        }
	    }
	 public static String getUniqueString() {
	        return UUID.randomUUID().toString().replace("-", "").substring(0, 10);
	    }
	 
	 public String randomeString()
	 {   String generateString=RandomStringUtils.randomAlphabetic(3);
		 return generateString;
	 }
	 
	 public String randomeNumber()
	 {   String generateNumber=RandomStringUtils.randomNumeric(2);
		 return generateNumber;
	 }
	 public String randomeAlphaNumaric()
	 {   String generateAlphaNumeric=RandomStringUtils.randomAlphabetic(3);
	     String generateNumber=RandomStringUtils.randomNumeric(2);
		 return (generateAlphaNumeric+generateNumber);
	 }
	 
	 public  String captureScreenshot(String testName) {
	        try {
	            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	         // Generate timestamp
	            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());

	            // Build destination path with test name + timestamp
	            String destPath = System.getProperty("user.dir") + "/reports/screenshots/" 
	                              + testName + "_" + timeStamp + ".png";
	            
	            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/reports/screenshots/"));
	            Files.copy(srcFile.toPath(), Paths.get(destPath));
	            return destPath;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

}

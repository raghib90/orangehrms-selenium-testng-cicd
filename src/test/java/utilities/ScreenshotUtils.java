package utilities;

import java.io.File;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.OutputType;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {

	 public static String captureScreenshot(WebDriver driver, String testName) {
	        try {
	            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
	            String destPath = System.getProperty("user.dir") + "/reports/screenshots/" + testName + ".png";
	            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/reports/screenshots/"));
	            Files.copy(srcFile.toPath(), Paths.get(destPath));
	            return destPath;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	 public static void  clearScreenshotFolder() {
		    String screenshotDir = System.getProperty("user.dir") + "/reports/screenshots/";
		    File folder = new File(screenshotDir);

		    if (folder.exists()) {
		        File[] files = folder.listFiles();
		        if (files != null) {
		            for (File file : files) {
		                if (file.isFile()) {
		                    file.delete();
		                }
		            }
		        }
		    } else {
		        // Create folder if it doesn't exist
		        folder.mkdirs();
		    }
		}
	 
	 public static String captureElementScreenshot(WebElement element, String elementName) {
	        try {
	            // Capture element screenshot
	            File srcFile = element.getScreenshotAs(OutputType.FILE);

	            // Add timestamp for uniqueness
	            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	            String destPath = System.getProperty("user.dir") + "/reports/screenshots/"
	                              + elementName + "_" + timeStamp + ".png";

	            // Ensure directory exists
	            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/reports/screenshots/"));

	            // Save file
	            Files.copy(srcFile.toPath(), Paths.get(destPath));

	            return destPath;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	 // ✅ Example element screenshot (say, login button)
//	    try {
//	        WebElement loginButton = driver.findElement(By.id("loginBtn"));
//	        String elementPath = ScreenshotUtils.captureElementScreenshot(loginButton, "LoginButton");
//	        if (elementPath != null) {
//	            test.fail("Element screenshot (Login Button):",
//	                MediaEntityBuilder.createScreenCaptureFromPath(elementPath).build());
//	        }
//	    } catch (Exception e) {
//	        test.info("Element screenshot not captured: " + e.getMessage());
//	    }
//
//	    test.fail(result.getThrowable());
//	}
}

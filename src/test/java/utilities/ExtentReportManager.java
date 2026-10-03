package utilities;

 
import java.text.SimpleDateFormat;

 
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;

public class ExtentReportManager implements ITestListener {
	public   ExtentReports extent;
	public ExtentSparkReporter sparkReporter;
	public ExtentTest test;
	String repName;
	 
	 
	public void onStart(ITestContext context) {
	    System.out.println("Starting Test Suite: " + context.getName());
	    ScreenshotUtils.clearScreenshotFolder();
	    
	    String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	    repName="Test-Report"+timeStamp+".html";
	    sparkReporter=new ExtentSparkReporter(".\\reports"+repName);
	    sparkReporter.config().setDocumentTitle("OpenCrat Automation");
	    sparkReporter.config().setReportName("Open Cart Login");
	    sparkReporter.config().setTheme(Theme.DARK);
	    extent=new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    extent.setSystemInfo("Application Name","Open Crat");
	    extent.setSystemInfo("Module Name","Open Crat Login");
	    extent.setSystemInfo("User Name",System.getProperty("user.name"));
	    extent.setSystemInfo("environment","QA");
	    String browser = context.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("browser",browser);
	    String Os = context.getCurrentXmlTest().getParameter("OS");
	    extent.setSystemInfo("Operating system",Os);
	    extent.setSystemInfo("Suite Name", context.getSuite().getName());
	    List<String>includeGroups=context.getCurrentXmlTest().getIncludedGroups();
	    if(!includeGroups.isEmpty())
	    {
	    	extent.setSystemInfo("Groups",includeGroups.toString());
	    }
	    
	    
        System.out.println(timeStamp);
	    // Initialize ExtentReports here if you want per-suite setup
	  
	    context.setAttribute("ExtentReports", extent);
	}

	    public void onTestSuccess(ITestResult result) {
	    	test=extent.createTest(result.getTestClass().getName());
	    	test.assignCategory(result.getMethod().getGroups());
	    	test.log(Status.PASS, result.getName()+" got successfully executed");
	       
	    }

	    public void onTestFailure(ITestResult result) {
	    	test=extent.createTest(result.getTestClass().getName());
	    	test.assignCategory(result.getMethod().getGroups());
	    	test.log(Status.FAIL, result.getName()+" Failed");
	    	 String screenshotPath = new BaseClass().captureScreenshot(result.getMethod().getMethodName());
	    	 if (screenshotPath != null) {
	    	        test.fail(result.getThrowable(),
	    	            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    	    } else {
	    	        test.fail(result.getThrowable());
	    	    }
	    }

	    public void onFinish(ITestContext context) {
	        extent.flush(); // writes everything to the report
	    }
}

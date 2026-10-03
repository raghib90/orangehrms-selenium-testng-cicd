package pageObject;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ActionUtils;

public class HomePage extends BasePage {
	private ActionUtils actionUtils;
	public HomePage(WebDriver driver) {
		super(driver);
		  actionUtils = new ActionUtils(driver, 10);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(xpath="//h3[normalize-space()='Login']")
	WebElement loginText;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement userNameTxt;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement passwordTxt;
	
	@FindBy(xpath="//button[@class='btn btn-primary btn-lg hidden-xs']")
	WebElement loginBtn;
	
	@FindBy(xpath="//a[@class='btn btn-black navbar-btn']")
	WebElement logOutBtn;
	
	@FindBy(xpath="//a[contains(text(),'Account')]")
	List<WebElement> accountText;
	
	 // Action Methods
    public String getLoginText() {
        return loginText.getText();
    }
    
    public String getAccountText() {
        return accountText.get(1).getText();
    }


    public void enterUsername(String user) {
    	actionUtils.waitForText(loginText,"Login");
    	userNameTxt.clear();
    	userNameTxt.sendKeys(user);
    }

    public void enterPassword(String pass) {
    	passwordTxt.clear();
    	passwordTxt.sendKeys(pass);
    }

    public void clickLoginButton() {
        loginBtn.click();
    }
    public void clickLogOutButton() {
        logOutBtn.click();
    }
}

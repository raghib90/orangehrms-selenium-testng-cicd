package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class UserRegistrationPage extends BasePage {

	public UserRegistrationPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	String expectedTitle="OpenCart - Open Source Shopping Cart Solution";
	
	@FindBy(xpath="//a[@class='btn btn-black navbar-btn']")
	WebElement registrationLink;
	
	@FindBy(xpath="//input[@id=\"input-username\"]")
	WebElement userNametxt;
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement firstNametxt;

	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement lastNametxt;

	@FindBy(xpath="//input[@id='input-email']")
	WebElement emailtxt;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement passwordtxt;

	@FindBy(xpath="//button[@class='btn btn-primary btn-lg hidden-xs']")
	WebElement registrationButton;
	
	@FindBy(xpath="//select[@id='input-country']")
	WebElement countryDropdown;
	
	@FindBy(xpath="//a[@class='btn btn-link navbar-btn']")
	WebElement loginBtnLink;
	
	 // Action Methods
    public void clickRegistrationLink() {
        registrationLink.click();
    }
    
    public void clickOnLoginLink() {
    	loginBtnLink.click();
    }
    public void clickRegistrationButton() {
    	registrationButton.click();
    }

    public void enterUsername(String username) {
        userNametxt.clear();
        userNametxt.sendKeys(username);
    }

    public void enterFirstName(String firstName) {
        firstNametxt.clear();
        firstNametxt.sendKeys(firstName);
    }

    public void enterLastName(String lastName) {
        lastNametxt.clear();
        lastNametxt.sendKeys(lastName);
    }

    public void enterEmail(String email) {
        emailtxt.clear();
        emailtxt.sendKeys(email);
    }
    public void enterPassword(String pwd) {
        emailtxt.clear();
        emailtxt.sendKeys(pwd);
    }
    // Composite Action Method
    public void registerNewUser(String username, String firstName, String lastName, String email, String password) {
        enterUsername(username);
        enterFirstName(firstName);
        enterLastName(lastName);
        enterEmail(email);
         
    }
    // Dropdown Selection Methods
    public void selectCountryByVisibleText(String countryName) {
        Select select = new Select(countryDropdown);
        select.selectByVisibleText(countryName);
    }
    
    public String getTitle()
    {
    	 
    	String actualTitle=driver.getTitle();
    	return actualTitle;
    	
    }
}

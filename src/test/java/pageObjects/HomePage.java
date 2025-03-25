//Page object class for HomePage by using pagefactory model

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//importing BasePage constructor method into the HomePage class
public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver)

	{
		super(driver);
	}
//Locators or webelements
	
	@FindBy(xpath ="//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement lnkLogin;
	
//Action Methods
	public void clickMyaccount()
	{
		lnkMyaccount.click();
	}
	
	public void clickRegister()
	{
		lnkRegister.click();
	
	}
	
	public void clickLogin()
	{
		lnkLogin.click();
	}
	
}

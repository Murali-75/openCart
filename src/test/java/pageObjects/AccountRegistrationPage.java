//Page object class for RegistrationPage
package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
//constructor invoke through inheritance from BasePage
public class AccountRegistrationPage extends BasePage {
	public  AccountRegistrationPage(WebDriver driver)
	
	{
		super(driver);
	}
	
	//Locators
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstName;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastName;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")  
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtconfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkdPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	//Action Methods
	
	public void FirstName(String fname) 
	{
		txtFirstName.sendKeys(fname);
		
	}
	
	public void LastName(String Lname) 
	{
		txtLastName.sendKeys(Lname);
		
	}
	
	
	public void Email(String email) 
	{
		txtEmail.sendKeys(email);
		
	}
	
	public void Phone(String phone) 
	{
		txtTelephone.sendKeys(phone);
		
	}
	
	public void Password(String pass) 
	{
		txtPassword.sendKeys(pass);
		
	}
	
	public void Confirmpwd(String Confpwd) 
	{
		txtconfirmPassword.sendKeys(Confpwd);
		
	}
	
	public void Chkboxpolicy() 
	{
		chkdPolicy.click();
		
	}
	
	public void Continue() 
	{   //sometimes click wont work then
		btnContinue.click();
		//sol2 
		//btnContinue.submit();
		
		//sol3
		
		//Actions act = new Actions(driver);
		//act.moveToElement(btnContinue).click().perform();
		
		//sol4
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();",btnContinue);
		
		//sol5
		//btnContinue.sendKeys(Keys.RETURN);
		
		//sol6
		//WebDriverWait mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btnContinue).click();
	}	
		public String getConfirmationMsg() 
		{
			try
			{	
				return(msgConfirmation.getText());
			}
		    
			catch(Exception e)
			{
				return(e.getMessage());
			}
		
	    }
}

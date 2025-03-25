package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	//constructor
		public MyAccountPage(WebDriver driver) 
		{
			super(driver);
		}

		@FindBy(xpath="//h2[normalize-space()='My Account']")   //MyAccount  Page heading
		
		WebElement   msgHeading;
		
		public boolean isMyAccountPage()
		{
			
			try 
			{
				
			return( msgHeading.isDisplayed());	
			}
			catch(Exception  e)
			{
				return false;
			}
		}
		
		
		
}

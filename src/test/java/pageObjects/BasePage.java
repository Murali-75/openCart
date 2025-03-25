//BasePage class is a constructor method which is used is every classobject page so it is stored in a seperate class for reusibility in other classes by extend in other claases(inheritence).

package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {
	
	WebDriver driver;
	public  BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements( driver,this);
	}

}

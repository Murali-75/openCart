//RandomStringUtils this class is depricated from 3.5 version in comms-lang3 dependency in pom.xml  file


package testCases;

import java.time.Duration;


import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.text.RandomStringGenerator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.Baseclass;

public class TC001_AccountRegistrationTest extends Baseclass {
	
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration()
	{
		
		logger.info("starting TC001_AccountRegistrationTest");
		
		HomePage hp = new HomePage(driver);  //creating object for HomePage to access methods from HomePage class
		hp.clickMyaccount();
		logger.info("Clicked on Myaccount link");
		hp.clickRegister();
		logger.info("Clicked on Register link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver); //creating object for AccountRegistrationPage  to access methods AccountRegistrationPage class
	    logger.info("Providing customer details");
	    regpage.FirstName(randomString().toUpperCase());
	    regpage.LastName(randomString().toUpperCase());
	    regpage.Email(randomString()+"@gmail.com");
	    regpage.Phone(randomNumber());
	    
	    String password=randomAlphaNumeric();
	    regpage.Password(password);
	    regpage.Confirmpwd(password);
	
	    regpage.Chkboxpolicy();

	    regpage.Continue();
	    
	    logger.info("Validating expected message");
	    
	    String  confmsg=regpage.getConfirmationMsg();
	    if(confmsg.equals("Your Account Has Been Created!"))
	    	
	    {
	    	Assert.assertTrue(true);
	    }
	    else
	    	
	    {
	    	//logger.debug("Debug logs");
	    	logger.error("Test failed");
			Assert.fail();
	    }
		
	    //Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		
		
	}	 
	
}
	
	


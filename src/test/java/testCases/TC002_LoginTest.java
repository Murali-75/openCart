package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.Baseclass;

public class TC002_LoginTest extends Baseclass {
	
	@Test(groups={"Sanity","Master"})
	
	public void verify_login()
	{
		logger.info("starting TC002_LoginTest");
		
		//HomePage
		try
		{
		HomePage  hp= new HomePage(driver);//creating object for HomePage class 
				hp.clickMyaccount();
		        hp.clickLogin();
		        
		//LoginPage
		        
		LoginPage lp= new LoginPage(driver);//creating object for LoginPage class 
		
		     lp.setEmail(p.getProperty("email"));
		     lp.setPassword(p.getProperty("password"));
		     lp.clicklogin();
		     
		//MyAccount
		     
		     MyAccountPage myacc =new MyAccountPage(driver);
		     
		     boolean  targetPage =myacc.isMyAccountPage();
		     
		     //Assert.assertEquals(targetPage,true,"Login failed");
		     Assert.assertTrue(targetPage);
		     
		}
		
		catch(Exception e)
		{
			Assert.fail();
		}
		     
		     
		   
	}
	


}

//contains the reusable methods  which are frequently used in the test cases
package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class Baseclass {
	public static 	WebDriver driver;
	public Logger logger;   //Log4j2
	public Properties p;

	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void Setup(String os,String br) throws IOException
	{
		//Loading config.properties file
	    FileReader  file =new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());//Log4j2
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
			
		{
			DesiredCapabilities capabilities=new DesiredCapabilities();
			//os
			if(os.equalsIgnoreCase("windows"))
			{
				capabilities.setPlatform(Platform.WIN10);
				
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			
			
			else
			{
				System.out.println("No matching os");
				return;
			}
			
			//browser
			
			switch (br.toLowerCase())
			{
			case "chrome" :capabilities.setBrowserName("chrome"); break;
			case  "edge"  :capabilities.setBrowserName("MicrosoftEdge"); break;
			case  "firefox"  :capabilities.setBrowserName("firefox"); break;
			default: System.out.println("No matching browser");  return;
			}
			
			//driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub")); depricated
			 //driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(), new ChromeOptions());
			 driver = new RemoteWebDriver(URI.create("http://localhost:4444/wd/hub").toURL(),capabilities);

			
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		 switch(br.toLowerCase())
		  {
		  case "chrome" :driver = new ChromeDriver();  break;
		  case "edge" :driver = new EdgeDriver();  break;
		  case "firefox" :driver = new FirefoxDriver();  break;
		  default :System.out.println("Invalid browser name"); return;
		  }
		}
		driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL1"));        //Reading url from properties file
		driver.manage().window().maximize();
		
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	public String randomString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
		        
	}

	public String randomNumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
		       
	}
   public String randomAlphaNumeric()
    {
    String generatedstring = RandomStringUtils.randomAlphabetic(5);
	String generatednumber = RandomStringUtils.randomNumeric(10);
	return (generatedstring+generatednumber);
    }	
   
   public String captureScreen(String tname) throws Exception {
	   String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	   TakesScreenshot ts = (TakesScreenshot) driver;  //typecasting
	   File  sourceFile = ts.getScreenshotAs(OutputType.FILE);
	   
	   String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp +".png";
	   File targetFile =new File(targetFilePath);
	   
	   sourceFile.renameTo(targetFile);
	   
	   return targetFilePath;
	   
	   
   }


	}



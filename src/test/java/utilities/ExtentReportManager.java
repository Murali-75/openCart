package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.Baseclass;

public class ExtentReportManager  implements ITestListener {
	
	public ExtentSparkReporter SparkReporter;  //UI of the report
	public ExtentReports extent; //populate common info on the report
	public ExtentTest test; //creating test case entries in the report and update the status of the test methods
	
	String  repName;
	
  public void onStart(ITestContext testContext)
 
  {
	  
	  /*SimpleDateFormat  df =new SimpleDateFormat ("yyyy.MM.dd.HH.mm.ss");
	   * Date dt=new Date();
	   * String  currentdatetimestamp=df.format(dt)
	   */
	  
	  String timeStamp =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());  //time stamp
	  repName ="Test-Report" +timeStamp +".html";
	  
	  SparkReporter  =new ExtentSparkReporter(".\\reports\\"+repName);  //specify the location of the file
	  
	  SparkReporter.config().setDocumentTitle("Opencart Automation Report"); //title of the report
	  SparkReporter.config().setReportName("Opencart  Functional Testing");   //name of the report
	  SparkReporter.config().setTheme(Theme.DARK); //Color of doc
	  
	  
	  extent=new ExtentReports();
	  extent.attachReporter( SparkReporter);
	  
	  extent.setSystemInfo("Application","opencart");
	  extent.setSystemInfo("Module","Admin");
	  
	  extent.setSystemInfo("Computer Name","localhost");
	  extent.setSystemInfo("Environment","QA");
	  extent.setSystemInfo("User Name",System.getProperty("user.name"));
	  
	  /*we are getting os and browser from the xml file dynamically
	  
	  extent.setSystemInfo("os","windows10");
	  extent.setSystemInfo("Browser Name","Chrome");
	  */
	  
	  String  os =testContext.getCurrentXmlTest().getParameter("os"); 
	  extent.setSystemInfo("Operating Syatem", os);
	  
	  String  browser =testContext.getCurrentXmlTest().getParameter("browser"); 
	  extent.setSystemInfo("Browser", browser);
	  
	  List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
	  if(!includedGroups.isEmpty())
	  {
		  extent.setSystemInfo("Groups",includedGroups.toString() );
	  }
	  
  }
	  
	public  void onTestSuccess(ITestResult result) 
	{
		test =extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS,result.getName()+"got Successfully executed");
	}
	
	public void onTestFailure(ITestResult result)
	{
		test =extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try
		{
			String imgPath =new Baseclass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void onTestSkipped(ITestResult result)
	{
		test =extent.createTest(result.getTestClass().getName()); //create a new entry in the report
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got skipped");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		
	}
	
	public void onFinish(ITestContext context)
	
	{
	  
	  extent.flush();
	  
	}
	
}
	  /*
	  String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
	  File extentReport =new File(pathOfExtentReport);
	  
	  try {
		  Desktop.getDesktop().browse(extentReport.toURI());
	  }
	  catch(IOException e)
	  {
		  e.printStackTrace();
	  }
			  
		To send Report to any one through email
		  try{
		  URL url = new URL("file://"+System.getProperty("user.dir")+"\\reports\\"+repName);
		  
		  //create the email  message
		  /*
		  ImageHtmlEmail email = new ImageHtmlEmail();
		  email.setDataSourceResolver(new  DataSourceUrlResolver(url) );
		  email.setHostName("smtp.googleamil.com");
		  email.setSmtpPort(465);
		  email.setAuthenticator(new DefaultAuthenticator("murali18112000@gmail.com","password"));
		  email.setSSLOnConnect(true);
		  email.setFrom("murali18112000@gmail.com");  //sender
		  email.setSubject("Test Results");
		  email.setSubject("Test Results");
		  email.setMsg("Please find Attached Report.............");
		  email.addTo("Receiver mail id");//Receiver
		  email.attach(url,  "extent report", "please check report............");
		  email.send(); //send the email
		  
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();	
		  }
		  */
	  
		  
	  


  

	



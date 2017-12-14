package com.bimaone.application.callEntry;


	import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;
	import com.bimaone.framework.constants.Constants;
	import com.relevantcodes.extentreports.LogStatus;


	/**Login.java incorporates TCs for all CallEntry Page 
	*@version   
	*@author Sivakarthic
	*/
	/*Replace Constants file as Constants file in the called object classes  */

	public class CallEntry extends Constants {
		
		CallEntryV000 obj1 = new CallEntryV000();
		
		@BeforeTest
		 public void initiatedriver() throws Exception
			{
				Properties prop = Constants.loadProperties();
			   // Constants.driver = new FirefoxDriver();
			   // Constants.driver.get(prop.getProperty("URL"));
				driver.findElement(By.xpath(prop.getProperty("Login_Username"))).click();
				driver.findElement(By.xpath(prop.getProperty("Logout"))).click();
			    driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("USERID"));
				driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(prop.getProperty("PASSWORD"));
				driver.findElement(By.xpath(prop.getProperty("Login"))).click();
		        Constants.driver.manage().window().maximize();
		        Constants.TestReports = Constants.ExecutionReports.startTest("CALL ENTRY PAGE TESTING");
			}

		 /**
		 * callentryTCs : Call to Callentry TCs
		 */
		    	@Test
		    	public void callentryTCs() throws Exception
		    	{
		    		try {
		    			Constants.TestReports.log(LogStatus.PASS, "CALL TO CallEntryPage TCs FOR BIMAONE");
		    			obj1.BimaOne_TCs_CallEntryPage();
		    		}
		    	    catch (Exception E)
		    	    {
		    	    System.out.println(E);
		    		System.out.println("CALL TO CallEntry TCs: FAIL");
		    	    Constants.TestReports.log(LogStatus.FAIL, "CALL TO CallEntryPage TCs");	
		    	    }
		    		
		    	} 
		    	
		    	 @AfterTest
		 	    public void finish ()
		 	    {
		 			
		 			Constants.ExecutionReports.endTest(Constants.TestReports);
		 			Constants.ExecutionReports.flush();
		 			//Constants.driver.close();
		 			
		 		
		 		} 
		 		


	}








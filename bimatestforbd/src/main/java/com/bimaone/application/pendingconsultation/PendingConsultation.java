package com.bimaone.application.pendingconsultation;

import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.bimaone.framework.constants.Constants;
import com.relevantcodes.extentreports.LogStatus;


/**PendingConsultation.java incorporates TCs for all PendingConsultation Page 
*@version   
*@author Sivakarthic
*/
/*Replace Constants file as Constants file in the called object classes  */

public class PendingConsultation extends Constants {
	
	PendingConsultationV000 obj1 = new PendingConsultationV000();
	
	@BeforeTest
	 public void initiatedriver() throws Exception
		{
			Properties prop = Constants.loadProperties();
		    //Constants.driver = new FirefoxDriver();
		    //Constants.driver.get(prop.getProperty("URL"));
		    driver.findElement(By.xpath(prop.getProperty("Login_Username"))).click();
	     	driver.findElement(By.xpath(prop.getProperty("Logout"))).click();
			driver.findElement(By.xpath(prop.getProperty("username"))).clear();
			driver.findElement(By.xpath(prop.getProperty("password"))).clear();
			Thread.sleep(1000);
		    driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("USERID"));
			driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(prop.getProperty("PASSWORD"));
			driver.findElement(By.xpath(prop.getProperty("Login"))).click();
	        //Constants.driver.manage().window().maximize();
	        Constants.TestReports = Constants.ExecutionReports.startTest("PENDING CONSULTATION PAGE TESTING");
		}

	 /**
	 * PendingConsultationTCs : Call to PendingConsultation TCs
	 */
	    	@Test
	    	public void callentryTCs() throws Exception
	    	{
	    		try {
	    			Constants.TestReports.log(LogStatus.PASS, "CALL TO PendingConsultation TCs FOR BIMAONE");
	    			obj1.BimaOne_TCs_PendingConsultationPage();
	    		}
	    	    catch (Exception E)
	    	    {
	    	    System.out.println(E);
	    		System.out.println("CALL TO PendingConsultation TCs: FAIL");
	    	    Constants.TestReports.log(LogStatus.FAIL, "CALL TO PendingConsultation TCs");	
	    	    }
	    		
	    	} 
	    	
	    	 @AfterTest
	 	    public void finish ()
	 	    {
	 			
	 			Constants.ExecutionReports.endTest(Constants.TestReports);
	 			Constants.ExecutionReports.flush();
	 			Constants.driver.close();
	 			
	 		
	 		} 
	 		


}








package com.bimaone.application.welcomecall;



import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.bimaone.framework.constants.Constants;
import com.relevantcodes.extentreports.LogStatus;


/**WelcomeCall.java incorporates TCs for all WelcomeCall Page 
*@version   
*@author Sivakarthic
*/
/*Replace Constants file as Constants file in the called object classes  */

public class WelcomeCall extends Constants {
	
	WelcomeCallV000 obj1 = new WelcomeCallV000();
	
	@BeforeTest
	 public void initiatedriver() throws Exception
		{
			//Properties prop = Constants.loadProperties();
		    //Constants.driver = new FirefoxDriver();
		    //Constants.driver.get(prop.getProperty("URL"));
		    //driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("USERID"));
			//driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(prop.getProperty("PASSWORD"));
			//driver.findElement(By.xpath(prop.getProperty("Login"))).click();
	        //Constants.driver.manage().window().maximize();
	        Constants.TestReports = Constants.ExecutionReports.startTest("WELCOME CALL PAGE TESTING");
		}

	 /**
	 * WelcomeCallTCs : Call to WelcomeCall TCs
	 */
	    	@Test
	    	public void callentryTCs() throws Exception
	    	{
	    		try {
	    			Constants.TestReports.log(LogStatus.PASS, "CALL TO WelcomeCall TCs FOR BIMAONE");
	    			obj1.BimaOne_TCs_WelcomeCallPage();
	    		}
	    	    catch (Exception E)
	    	    {
	    	    System.out.println(E);
	    		System.out.println("CALL TO WelcomeCall TCs: FAIL");
	    	    Constants.TestReports.log(LogStatus.FAIL, "CALL TO WelcomeCall TCs");	
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








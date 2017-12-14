package com.bimaone.application.login;

import java.util.Properties;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.bimaone.framework.constants.Constants;
import com.relevantcodes.extentreports.LogStatus;


/**Login.java incorporates TCs for all LoginPage Page 
*@version   
*@author Sivakarthic
*/
/*Replace Constants file as Constants file in the called object classes  */

public class Login extends Constants {
	
	LoginV000 obj1 = new LoginV000();
	
	@BeforeTest
	 public void initiatedriver() throws Exception
		{
			Properties prop = Constants.loadProperties();
		    Constants.driver = new FirefoxDriver();
		    Constants.driver.get(prop.getProperty("URL"));
	        Constants.driver.manage().window().maximize();
	        Constants.TestReports = Constants.ExecutionReports.startTest("LOGIN PAGE TESTING");
		}

	 /**
	 * callLoginTCs : Call to LoginPage TCs
	 */
	    	@Test
	    	public void callLoginTCs() throws Exception
	    	{
	    		try {
	    			Constants.TestReports.log(LogStatus.PASS, "CALL TO LOGIN TCs FOR BIMAONE");
	    			obj1.BimaOne_TCs_LoginPage();
	    		}
	    	    catch (Exception E)
	    	    {
	    	    System.out.println(E);
	    		System.out.println("CALL TO LOGIN TCs: FAIL");
	    	    Constants.TestReports.log(LogStatus.FAIL, "CALL TO LOGIN TCs");	
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






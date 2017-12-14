package com.bimaone.application.login;

import java.sql.ResultSet;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.bimaone.framework.constants.Constants;

import com.bimaone.framework.dal.DBConnection;
import com.google.common.base.Verify;
import com.relevantcodes.extentreports.LogStatus;

/**
 * LoginV001.java incorporates TCs for LoginPage
 * 
 * @version *@author
 */

public class LoginV001 extends Constants{

	public static String userName;
	public static String user_availability_before;
	public static String user_availability_after;

	

	/**
	 * BO_TC01_LoginV001 : TC to check username , password and login button are
	 * displayed. Applicable for
	 */

	public static void BO_TC01_LoginV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			WebElement usrname = driver.findElement(By.xpath(prop.getProperty("username")));
			WebElement pswd = driver.findElement(By.xpath(prop.getProperty("password")));
			WebElement lgn = driver.findElement(By.xpath(prop.getProperty("Login")));
			Verify.verify(usrname.isDisplayed() || pswd.isDisplayed() || lgn.isDisplayed());
            Constants.getscreenshot();
			Constants.TestReports.log(LogStatus.PASS, "BO_TC01_LoginV001 : TC to check username , pswd & login ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL, "BO_TC01_LoginV001 : TC to check username , pswd & login");
		}
	}

	/**
	 * BO_TC02_LoginV001 : TC to check login button is disabled for empty values . Applicable for
	 */

	public static void BO_TC02_LoginV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("USERID"));
			WebElement lgn = driver.findElement(By.xpath(prop.getProperty("Login")));
			lgn.click();
			WebElement fpsd = driver.findElement(By.xpath(prop.getProperty("forgetpsd")));
			Verify.verify(fpsd.isDisplayed());
            Constants.getscreenshot();
			Constants.TestReports.log(LogStatus.PASS, "BO_TC02_LoginV001 : TC to check login button disabled ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL, "BO_TC02_LoginV001 : TC to check login button disabled");
		}
	}
	
	/**
	 * BO_TC03_LoginV001 : TC to check Login Button Functionality with check
	 * username with DB after getting login. Applicable for
	 */

	public static void BO_TC03_LoginV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			driver.findElement(By.xpath(prop.getProperty("username"))).clear();
			driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("USERID"));
			driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(prop.getProperty("PASSWORD"));
			driver.findElement(By.xpath(prop.getProperty("Login"))).click();
			String query = "SELECT user_name from bima_user where user_name='" + prop.getProperty("USERID") + "'";
			ResultSet rsObject1 = DBConnection.DBConnect(query);
			while (rsObject1.next()) {
				userName = rsObject1.getString("user_name");
			}
			String TC01_LoginV001_Actual01 = Constants.driver
					.findElement(By.xpath(prop.getProperty("Login_Username"))).getText();
			if (TC01_LoginV001_Actual01.replaceAll("\\s+", "").equalsIgnoreCase(userName.replaceAll("\\s+", "")))
				;
			DBConnection.Dbcon.close();
            Constants.getscreenshot();
			driver.findElement(By.xpath(prop.getProperty("Login_Username"))).click();
			driver.findElement(By.xpath(prop.getProperty("Logout"))).click();
			Constants.TestReports.log(LogStatus.PASS, "BO_TC03_LoginV001 : TC to check Login Button Functionality ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL, "BO_TC03_LoginV001 : TC to check Login Button Functionality ");
		}
	}
	
	
	/**
	 * BO_TC03_LoginV001 : TC to check Login Button Functionality with check
	 * username with DB after getting login. Applicable for
	 */

	public static void BO_TC04_LoginV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
            Thread.sleep(1500);
			String query = "select user_name , user_retry_count from bima_user ORDER BY RAND() limit 1; ";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				userName = rsObject.getString("user_name");
				user_availability_before = rsObject.getString("user_retry_count");

			}
			System.out.println(userName+user_availability_before);
			driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(userName);
			driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(prop.getProperty("wrongpswd"));
			driver.findElement(By.xpath(prop.getProperty("Login"))).click();
			
			String query2 = "select user_retry_count from bima_user where user_name='"+userName+"'";
			ResultSet rsObject1 = DBConnection.DBConnect(query2);
			while (rsObject1.next()) {
				user_availability_after = rsObject1.getString("user_retry_count");
			}
			System.out.println(userName+user_availability_after);
            Constants.getscreenshot();
			Verify.verify(!user_availability_after.equals(user_availability_before));
			DBConnection.Dbcon.close();
			Constants.TestReports.log(LogStatus.PASS, "BO_TC04_LoginV001 : TC to check Login retry Functionality ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL, "BO_TC04_LoginV001 : TC to check Login retry Functionality ");
		}
	}
	
	
}

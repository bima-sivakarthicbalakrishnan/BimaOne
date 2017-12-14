package com.bimaone.application.welcomecall;

import java.sql.ResultSet;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.bimaone.framework.constants.Constants;
import com.bimaone.framework.dal.DBConnection;
import com.google.common.base.Verify;
import com.relevantcodes.extentreports.LogStatus;

public class WelcomeCallV001 extends Constants {

	public static String retry_cont;
	public static String agen_id;
	public static String usr_id;
	public static String usr_nam;
	public static String usrfull_nam;
	public static String msisd;
	public static String ids;
	public static String pol_id;
	public static String policy_nam;
	public static String meta_value_gen;
	public static String meta_value_age;
	public static String content_subscription ;
	public static String stats;
	
	

	/**
	 * BO_TC01_WelcomeCallV001 : TC to fetch welcome call details from DB
	 * Applicable for
	 */

	public static void BO_TC01_WelcomeCallV001() throws Exception {
		try {
			Thread.sleep(1000);
			
			String upquery = "update welcome_calls set status ='1' where id in (1,2);";
	        DBConnection.Connect(upquery);
	        
			String query = "select retry_count,agent_id,user_id from welcome_calls where status = 1 order by id limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				retry_cont = rsObject.getString("retry_count");
				agen_id = rsObject.getString("agent_id");
				usr_id = rsObject.getString("user_id");
			}
			
			String query1 = "select user_name from bima_user where id='"+agen_id+"';";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				usr_nam = rsObject1.getString("user_name");
			}
			
			String query2 = "select CONCAT(first_name, ' ', last_name) AS username , msisdn , id  from bima_customer where user_id='"+usr_id+"';";
			ResultSet rsObject2 = DBConnection.DBConnect(query2);
			while (rsObject2.next()) {
				usrfull_nam = rsObject2.getString("username");
				msisd = rsObject2.getString("msisdn");
				ids = rsObject2.getString("id");
				
			}
			
			String query3 = "select policy_id from customer_coverage_policy where customer_id='"+ids+"' ;";
			ResultSet rsObject3 = DBConnection.DBConnect(query3);
			while (rsObject3.next()) {
				pol_id = rsObject3.getString("policy_id");
			}
			
			String query4 = "select policy_name from bima_policy where id='"+pol_id+"';";
			ResultSet rsObject4 = DBConnection.DBConnect(query4);
			while (rsObject4.next()) {
				policy_nam = rsObject4.getString("policy_name");
			}
			
			String query5 = "select meta_value from customer_meta_data where customer_id='"+ids+"' and meta_key='AGE';";
			ResultSet rsObject5 = DBConnection.DBConnect(query5);
			while (rsObject5.next()) {
				meta_value_age = rsObject5.getString("meta_value");
			}
			
			String query6 = "select meta_value from customer_meta_data where customer_id='"+ids+"' and meta_key='GENDER';";
			ResultSet rsObject6 = DBConnection.DBConnect(query6);
			while (rsObject6.next()) {
				meta_value_gen = rsObject6.getString("meta_value");
			}
			

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC01_WelcomeCallV001 : TC to fetch welcome call details from DB");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC01_WelcomeCallV001 : TC to fetch welcome call details from DB");
		}
	}

	/**
	 * BO_TC02_WelcomeCallV001 : TC to print the fetched Welcome call DB values .
	 * Applicable for
	 */

	public static void BO_TC02_WelcomeCallV001() throws Exception {
		try {


			System.out.println(retry_cont);
			System.out.println(agen_id);
			System.out.println(usr_id);
			System.out.println(usr_nam);
			System.out.println(usrfull_nam);
			System.out.println(msisd);
			System.out.println(ids);
			System.out.println(pol_id);
			System.out.println(policy_nam);
			System.out.println(meta_value_age);
			System.out.println(meta_value_gen);


			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC02_WelcomeCallV001 : TC to print the fetched Welcome call DB values");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC02_WelcomeCallV001 : TC to print the fetched Welcome call DB values ");
		}
	}

	/**
	 * BO_TC03_WelcomeCallV001 : TC to compare welcome values with DB .
	 * Applicable for
	 */

	public static void BO_TC03_WelcomeCallV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			//driver.findElement(By.xpath(prop.getProperty("Login_Username"))).click();
			//driver.findElement(By.xpath(prop.getProperty("Logout"))).click();
			driver.findElement(By.xpath(prop.getProperty("username"))).clear();
			driver.findElement(By.xpath(prop.getProperty("password"))).clear();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("AGENTUSRNAME"));
			driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(prop.getProperty("AGENTPASSWORD"));
			driver.findElement(By.xpath(prop.getProperty("Login"))).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("welcomecall"))).click();;
            Thread.sleep(1000);
			WebElement we2 = driver.findElement(By.xpath(prop.getProperty("welcomtable")));
			Verify.verify(we2.isDisplayed());
			WebElement we3 = driver.findElement(By.xpath("//tr/td[1]"));
			Verify.verify(we3.isDisplayed());
			WebElement we4 = driver.findElement(By.xpath("//tr/td[2]"));
			Verify.verify(we4.isDisplayed());
			WebElement we5 = driver.findElement(By.xpath("//tr/td[3]"));
			Verify.verify(we5.isDisplayed());
			WebElement we6 = driver.findElement(By.xpath("//tr/td[4]"));
			Verify.verify(we6.isDisplayed());
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC03_WelcomeCallV001 : TC to compare welcome values with DB ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC03_WelcomeCallV001 : TC to compare welcome values with DB ");
		}
	}

	/**
	 * BO_TC04_WelcomeCallV001 : TC to Welcome call details with DB.
	 * Applicable for
	 */

	public static void BO_TC04_WelcomeCallV001() throws Exception {
		try {

            String st1 = driver.findElement(By.xpath("//tr/td[2]")).getText();
            Verify.verify(st1.equals(msisd));	
            
            String st2 = driver.findElement(By.xpath("//tr/td[3]")).getText();
            Verify.verify(st2.equals(policy_nam));	
            
            String st3 = driver.findElement(By.xpath("//tr/td[4]")).getText();
            Verify.verify(st3.equals(retry_cont));	
            
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC04_WelcomeCallV001 : TC to Welcome call details with DB");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC04_WelcomeCallV001 : TC to Welcome call details with DB");
		}
	}

	
	/**
	 * BO_TC05_WelcomeCallV001 : TC to check welcome call details page values with DB .
	 * Applicable for
	 */

	public static void BO_TC05_WelcomeCallV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			
			driver.findElement(By.xpath(prop.getProperty("select"))).click();

            String st1 = driver.findElement(By.xpath(prop.getProperty("welcomehead"))).getText();
            Verify.verify(st1.equals("Welcome Call"));	
            
            String st2 = driver.findElement(By.xpath(prop.getProperty("custoname"))).getText();
            Verify.verify(st2.equals("Customer Name"));	
            String st3 = driver.findElement(By.xpath(prop.getProperty("custoage"))).getText();
            Verify.verify(st3.equals("Age"));	
            String st4 = driver.findElement(By.xpath(prop.getProperty("customsisdn"))).getText();
            Verify.verify(st4.equals("MSISDN"));	
            String st5 = driver.findElement(By.xpath(prop.getProperty("custogen"))).getText();
            Verify.verify(st5.equals("Gender"));	
            String st6 = driver.findElement(By.xpath(prop.getProperty("custoprodsub"))).getText();
            Verify.verify(st6.equals("Subscribed Product"));	
            String st7 = driver.findElement(By.xpath(prop.getProperty("coveredcusto"))).getText();
            Verify.verify(st7.equals("Covered Customer"));	
            
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC05_WelcomeCallV001 : TC to check welcome call details page values with DB .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC05_WelcomeCallV001 : TC to check welcome call details page values with DB . ");
		}
	}
	
	
	/**
	 * BO_TC06_WelcomeCallV001 :  TC to check welcome call details page values with DB . 
	 * Applicable for
	 */

	public static void BO_TC06_WelcomeCallV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			

            String st1 = driver.findElement(By.xpath(prop.getProperty("welcomehead"))).getText();
            Verify.verify(st1.equals("Welcome Call"));	
            
            System.out.println(st1+"Welcome Call");

            
            String st2 = driver.findElement(By.xpath(prop.getProperty("custonameinp"))).getAttribute("value");
            Verify.verify(st2.equals(usrfull_nam));	
            System.out.println(st2+usrfull_nam);
            
            String st3 = driver.findElement(By.xpath(prop.getProperty("custoageinp"))).getAttribute("value");
            Verify.verify(st3.equals(meta_value_age));	
            System.out.println(st3+meta_value_age);
            String st4 = driver.findElement(By.xpath(prop.getProperty("customsisdninp"))).getAttribute("value");
            Verify.verify(st4.equals(msisd));	
            System.out.println(st4+msisd);
            //String st5 = driver.findElement(By.xpath(prop.getProperty("custogeninp"))).getAttribute("value");
            //Verify.verify(meta_value_gen.equals("MALE"));
            //System.out.println(st5+meta_value_gen);
            String st6 = driver.findElement(By.xpath(prop.getProperty("custoprodsubinp"))).getAttribute("value");
            Verify.verify(st6.equals(policy_nam));
            System.out.println(st6+policy_nam);
            String st7 = driver.findElement(By.xpath(prop.getProperty("coveredcustoinp"))).getAttribute("value");
            Verify.verify(st7.equals(usrfull_nam));
            System.out.println(st7+usrfull_nam);
            
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC06_WelcomeCallV001 :  TC to check welcome call details page values with DB ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC06_WelcomeCallV001 :  TC to check welcome call details page values with DB  ");
		}
	}
	
	
	/**
	 * BO_TC07_WelcomeCallV001 : TC to check web elements in details page.
	 * Applicable for
	 */

	public static void BO_TC07_WelcomeCallV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			
            String st1 = driver.findElement(By.xpath(prop.getProperty("welcheader"))).getText();
            System.out.println(st1);
            Verify.verify(st1.equals("Welcome Call"));	
            String st2 = driver.findElement(By.xpath(prop.getProperty("weldetails1"))).getText();
            System.out.println(st2);
            Verify.verify(st2.equals(prop.getProperty("welcexp1")));	
            String st3 = driver.findElement(By.xpath(prop.getProperty("weldetails2"))).getText();
            System.out.println(st3);
            //Verify.verify(st3.equals(prop.getProperty("welcexp2")));	
            String st4 = driver.findElement(By.xpath(prop.getProperty("weldetails3"))).getText();
            System.out.println(st4);
            //Verify.verify(st4.equals(prop.getProperty("welcexp3")));	
            //String st5 = driver.findElement(By.xpath(prop.getProperty("weldetails4"))).getText();
            //Verify.verify(st5.equals(prop.getProperty("welcexp4")));	
            //System.out.println(st5);
            String st6 = driver.findElement(By.xpath(prop.getProperty("weldetails5"))).getText();
            System.out.println(st6);
            Verify.verify(st6.equals(prop.getProperty("welcexp5")));	
            
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC07_WelcomeCallV001 : TC to check elements in details page.");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC07_WelcomeCallV001 : TC to check elements in details page. ");
		}
	}
	
	
	/**
	 * BO_TC08_WelcomeCallV001 : TC to check Coaching program options .
	 * Applicable for
	 */

	public static void BO_TC08_WelcomeCallV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			

            String st1 = driver.findElement(By.xpath(prop.getProperty("availproglabel"))).getText();
            Verify.verify(st1.equals("Available Programs"));
            System.out.println(st1+"Available Programs");

            String st2 = driver.findElement(By.xpath(prop.getProperty("availprogoptions1"))).getText();
            Verify.verify(st2.equals("Stay Healthy program"));	
            System.out.println(st2+"Stay Healthy program");

            String st3 = driver.findElement(By.xpath(prop.getProperty("availprogoptions2"))).getText();
            Verify.verify(st3.equals("Lose Weight program"));	
            System.out.println(st3+"Lose Weight program");

            String st4 = driver.findElement(By.xpath(prop.getProperty("availprogoptions3"))).getText();
            Verify.verify(st4.equals("Diabetes and Hypertension program"));	
            System.out.println(st4+"Diabetes and Hypertension program");

            String st5 = driver.findElement(By.xpath(prop.getProperty("availprogoptions4"))).getText();
            Verify.verify(st5.equals("Women's health and child care program"));	
            System.out.println(st5+"Women's health and child care program");

            String st6 = driver.findElement(By.xpath(prop.getProperty("availprogoptions5"))).getText();
            Verify.verify(st6.equals("No program"));	
            System.out.println(st6+"No program");
            
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC08_WelcomeCallV001 : TC to check Coaching program options .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC08_WelcomeCallV001 : TC to check Coaching program options . ");
		}
	}
	
	
	/**
	 * BO_TC09_WelcomeCallV001 : TC to check submit button is disabled with entering mandatory details .
	 * Applicable for
	 */

	public static void BO_TC09_WelcomeCallV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			

            driver.findElement(By.xpath(prop.getProperty("submitbutwelc"))).click();
            String st2 = driver.findElement(By.xpath(prop.getProperty("custonameinp"))).getAttribute("value");
            Verify.verify(st2.equals(usrfull_nam));
            Thread.sleep(1500);

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC09_WelcomeCallV001 : TC to check submit button is disabled with entering mandatory details");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC09_WelcomeCallV001 : TC to check submit button is disabled with entering mandatory details");
		}
	}
	
	/**
	 * BO_TC10_WelcomeCallV001 : TC to check coaching prog details in DB after submitting.
	 * Applicable for
	 */

	public static void BO_TC10_WelcomeCallV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			
            Thread.sleep(1500);

            driver.findElement(By.xpath(prop.getProperty("stayhealthopt"))).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath(prop.getProperty("submitbutwelc"))).click();
            Thread.sleep(1500);
          //  driver.findElement(By.xpath(prop.getProperty("stayhealthopt"))).click();
          //  Thread.sleep(1500);
            String query1 = "select content_subscription_id from user_preference order by id desc limit 1;";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				content_subscription = rsObject1.getString("content_subscription_id");
			}
            
            Verify.verify(content_subscription.equals("STAY_HEALTHY_PROGRAM"));
            Thread.sleep(1500);
            
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC10_WelcomeCallV001 : TC to check coaching prog details in DB after submitting.");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC10_WelcomeCallV001 : TC to check coaching prog details in DB after submitting. ");
		}
	}
	
	
	/**
	 * BO_TC11_WelcomeCallV001 : TC to check call incomplete for WC .
	 * Applicable for
	 */

	public static void BO_TC11_WelcomeCallV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
            Thread.sleep(1500);

			String st = driver.findElement(By.xpath("//tbody/tr[1]/th")).getText();
            Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("select"))).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath(prop.getProperty("callincompwc"))).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath(prop.getProperty("calldiswc"))).click();
            Thread.sleep(1500);
            driver.findElement(By.xpath(prop.getProperty("submpopupwc"))).click();
            Thread.sleep(1500);
            String query1 = "select status from welcome_calls where id='"+st+"';";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				stats = rsObject1.getString("status");
			}
            
            Verify.verify(stats.equals("4"));
            
            System.out.println(stats);
            
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC11_WelcomeCallV001 : TC to check call incomplete for WC ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC11_WelcomeCallV001 : TC to check call incomplete for WC  ");
		}
	}
	
}
	
	

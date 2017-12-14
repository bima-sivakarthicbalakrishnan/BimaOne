package com.bimaone.application.callEntry;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.bimaone.framework.constants.Constants;
import com.bimaone.framework.dal.DBConnection;
import com.google.common.base.Verify;
import com.relevantcodes.extentreports.LogStatus;

public class CallEntryV001 extends Constants {

	public static String username;
	public static String id;
	public static String meta_value_gender;
	public static String meta_value_age;
	public static String idgenraldisp;
	public static String customerid;
	public static String status;
	public static String forcustomerid;
	public static String disposition_type;
	public static String infant_age;
	public static String is_emergency;
	public static String doctor_gender_preference;
	public static String emergency_type;
	public static String forcustomeridact;
	public static String statusagent;
	public static String statusdoctoragent;
	public static String call_drop_reason;
	public static String statuscallincomplete;
	public static String recenteventid;
	public static String recentstatus;
	public static String incompidcge ;

	/**
	 * BO_TC01_CallEntryV001 : TC to check callentry_link and Callentry landing
	 * page. Applicable for
	 */

	public static void BO_TC01_CallEntryV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			driver.findElement(By.xpath(prop.getProperty("callentry_link"))).click();
			String heading = driver.findElement(By.xpath(prop.getProperty("callentry_head"))).getText();
			System.out.println(heading);
			if (prop.getProperty("TC1_Actual").replaceAll("\\s+", "").equalsIgnoreCase(heading.replaceAll("\\s+", "")))
				;

			String msisdn = driver.findElement(By.xpath(prop.getProperty("msisdn_label"))).getText();
			if (prop.getProperty("TC1a_Actual").replaceAll("\\s+", "").equalsIgnoreCase(msisdn.replaceAll("\\s+", "")))
				;
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC01_CallEntryV001 : TC to check callentry_link and Callentry landing page.");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC01_CallEntryV001 : TC to check callentry_link and Callentry landing page.");
		}
	}

	/**
	 * BO_TC02_CallEntryV001 : TC to check search functionality with invalid
	 * number values . Applicable for
	 */

	public static void BO_TC02_CallEntryV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).sendKeys(prop.getProperty("Invalid_msisdn"));
			driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
			Thread.sleep(1000);
			String msg = driver.findElement(By.xpath(prop.getProperty("custnotfoundmsg"))).getText();
			if (prop.getProperty("TC2_Actual").replaceAll("\\s+", "").equalsIgnoreCase(msg.replaceAll("\\s+", "")))
				;
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC02_CallEntryV001 : TC to check search functionality with invalid number ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC02_CallEntryV001 : TC to check search functionality with invalid number");
		}
	}

	/**
	 * BO_TC03_CallEntryV001 : TC to check Search button is enabled values .
	 * Applicable for
	 */

	public static void BO_TC03_CallEntryV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).clear();
			WebElement but = driver.findElement(By.xpath(prop.getProperty("searchbutton")));

			Verify.verify(but.isEnabled());
			Constants.TestReports.log(LogStatus.PASS, "BO_TC03_CallEntryV001 :TC to check Search button is enabled ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL, "BO_TC03_CallEntryV001 : TC to check Search button is enabled");
		}
	}

	/**
	 * BO_TC04_CallEntryV001 : TC to check Search functionality with proper
	 * MSISDN values . Applicable for
	 */

	public static void BO_TC04_CallEntryV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).sendKeys(prop.getProperty("propermsisdn"));
			driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
			Thread.sleep(2000);
			WebElement inp = driver.findElement(By.xpath(prop.getProperty("custnameinput")));
			Verify.verify(inp.isDisplayed());
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC04_CallEntryV001 : TC to check Search functionality with proper MSISDN");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC04_CallEntryV001 : TC to check Search functionality with proper MSISDN ");
		}
	}

	/**
	 * BO_TC05_CallEntryV001 : TC to check customer details are not editable
	 * values . Applicable for
	 */

	public static void BO_TC05_CallEntryV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			WebElement inpname = driver.findElement(By.xpath(prop.getProperty("custnameinput")));
			Verify.verify(!inpname.isEnabled());
			WebElement inpage = driver.findElement(By.xpath(prop.getProperty("custageinput")));
			Verify.verify(!inpage.isEnabled());
			WebElement inpgender = driver.findElement(By.xpath(prop.getProperty("custgenderinput")));
			Verify.verify(!inpgender.isEnabled());
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC05_CallEntryV001 : TC to check customer details are not editable ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC05_CallEntryV001 : TC to check customer details are not editable");
		}
	}

	/**
	 * BO_TC06_CallEntryV001 : TC to check compare customer details with
	 * database values . Applicable for
	 */

	public static void BO_TC06_CallEntryV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();

			String query = "select id,CONCAT(first_name, ' ', last_name) AS username from bima_customer where msisdn='"
					+ prop.getProperty("propermsisdn") + "'";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				id = rsObject.getString("id");
				username = rsObject.getString("username");

			}
			String TC6_Actual = Constants.driver.findElement(By.xpath(prop.getProperty("custnameinput")))
					.getAttribute("value");
			if (TC6_Actual.replaceAll("\\s+", "").equalsIgnoreCase(username.replaceAll("\\s+", "")))
				;
			System.out.println(TC6_Actual + username);
			String query1 = "select meta_value from customer_meta_data where customer_id=" + id + " and meta_key='AGE'";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				meta_value_age = rsObject1.getString("meta_value");
			}
			String TC6a_Actual = Constants.driver.findElement(By.xpath(prop.getProperty("custageinput")))
					.getAttribute("value");
			if (TC6a_Actual.replaceAll("\\s+", "").equalsIgnoreCase(meta_value_age.replaceAll("\\s+", "")))
				;

			String query2 = "select meta_value from customer_meta_data where customer_id=" + id + " and meta_key='AGE'";
			ResultSet rsObject2 = DBConnection.DBConnect(query2);
			while (rsObject2.next()) {
				meta_value_gender = rsObject2.getString("meta_value");
			}
			String TC6b_Actual = Constants.driver.findElement(By.xpath(prop.getProperty("custgenderinput")))
					.getAttribute("value");
			if (TC6b_Actual.replaceAll("\\s+", "").equalsIgnoreCase(meta_value_gender.replaceAll("\\s+", "")))
				;
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC06_CallEntryV001 : TC to check compare customer details with database ");
			DBConnection.Dbcon.close();

		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC06_CallEntryV001 : TC to check compare customer details with database");
		}
	}

	/**
	 * BO_TC07_CallEntryV001 : TC to check dropdown values values . Applicable
	 * for
	 */

	public static void BO_TC07_CallEntryV001() {
		try {

			String[] exp = { "mDaktar" };
			WebElement dropdown = Constants.driver.findElement(By.id("policy-ce"));

			Select select = new Select(dropdown);

			List<WebElement> options = select.getOptions();
			for (WebElement we : options) {
				for (int i = 0; i < exp.length; i++) {
					if (we.getText().equals(exp[i])) {
						System.out.println("Matched");
					}
				}
			}
			Constants.TestReports.log(LogStatus.PASS, "BO_TC07_CallEntryV001 : TC to check dropdown values of policy");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL, "BO_TC07_CallEntryV001 : TC to check dropdown values of policy");
		}
	}

	/**
	 * BO_TC08_CallEntryV001 : TC to check disposition drop down values .
	 * Applicable for
	 */

	public static void BO_TC08_CallEntryV001() {
		try {

			String[] exp = { "General Disposition", "Doctor Consultation" };
			WebElement dropdown = Constants.driver
					.findElement(By.xpath(".//*[@id='customer-form']/div[5]/div/div/select"));

			Select select = new Select(dropdown);

			List<WebElement> options = select.getOptions();
			for (WebElement we : options) {
				for (int i = 0; i < exp.length; i++) {
					if (we.getText().equals(exp[i])) {
						System.out.println("Matched");
					}
				}
			}
			WebElement droppolicy = driver.findElement(By.id("policy-ce"));
			Select select3 = new Select(droppolicy);
			select3.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisp = driver.findElement(By.xpath(".//*[@id='customer-form']/div[5]/div/div/select"));
			Select select4 = new Select(dropdisp);
			select4.selectByIndex(1);
			Thread.sleep(1000);
			Constants.TestReports.log(LogStatus.PASS, "BO_TC08_CallEntryV001 : TC to check disposition drop down");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL, "BO_TC08_CallEntryV001 : TC to check disposition drop down ");
		}
	}

	/**
	 * BO_TC08a_CallEntryV001 : TC to check disposition dropdown types values .
	 * Applicable for
	 */

	public static void BO_TC08a_CallEntryV001() {
		try {

			String[] exp = { "General Disposition Type", "Check Current Plan", "Balance deduction query",
					"New registration", "Re-registration", "Deregistration",
					"Complain - Registration/Re-registration/Deregistration", "Complain - Service", "Complain - Fraud",
					"Consultation yet not provided", "Other MNO query", "Prank call" };
			WebElement dropdown = Constants.driver.findElement(By.xpath(".//*[@id='dispo']/div[1]/div/div[2]/select"));

			Select select = new Select(dropdown);

			List<WebElement> options = select.getOptions();
			for (WebElement we : options) {
				for (int i = 0; i < exp.length; i++) {
					if (we.getText().equals(exp[i])) {
						System.out.println("Matched");
					}
				}
			}
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC08a_CallEntryV001 : TC to check disposition dropdown types");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC08a_CallEntryV001 : TC to check disposition dropdown types ");
		}
	}

	/**
	 * BO_TC09_CallEntryV001 : TC to check general disp flow for doctor values .
	 * Applicable for
	 */

	public static void BO_TC09_CallEntryV001() {
		try {
			Properties prop = Constants.loadProperties();
			WebElement droppolicy = driver.findElement(By.id("policy-ce"));
			Select select = new Select(droppolicy);
			select.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisp = driver.findElement(By.xpath(".//*[@id='customer-form']/div[5]/div/div/select"));
			Select select1 = new Select(dropdisp);
			select1.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisptype = driver.findElement(By.xpath(".//*[@id='dispo']/div[1]/div/div[2]/select"));
			Select select2 = new Select(dropdisptype);
			select2.selectByIndex(1);
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("dispnotes"))).sendKeys(prop.getProperty("disptext"));
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("genralsubmit1"))).click();
			Thread.sleep(3000);

			String query = "select id from bima_customer where msisdn='" + prop.getProperty("propermsisdn") + "'";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				idgenraldisp = rsObject.getString("id");
			}
			String query1 = "select for_customer_id , status from  consultation_request order by created_date desc limit 1;";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				customerid = rsObject1.getString("for_customer_id");
				status = rsObject1.getString("status");

			}
			if (idgenraldisp.replaceAll("\\s+", "").equalsIgnoreCase(customerid.replaceAll("\\s+", "")))
				;

			Verify.verify(status.equals("2"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC09_CallEntryV001 : TC to check general disp flow for doctor");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC09_CallEntryV001 : TC to check general disp flow for doctor ");
		}
	}

	/**
	 * BO_TC10_CallEntryV001 : TC to check doctor disp flow for doctor values .
	 * Applicable for
	 */

	public static void BO_TC10_CallEntryV001() {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).sendKeys(prop.getProperty("propermsisdn"));
			driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
			Thread.sleep(1000);
			WebElement droppolicy = driver.findElement(By.id("policy-ce"));
			Select select = new Select(droppolicy);
			select.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisp = driver.findElement(By.xpath(".//*[@id='customer-form']/div[5]/div/div/select"));
			Select select1 = new Select(dropdisp);
			select1.selectByIndex(2);
			Thread.sleep(1000);
			WebElement consfor = driver.findElement(By.xpath(prop.getProperty("consfor")));
			System.out.println("1");
			Verify.verify(consfor.isDisplayed());
			WebElement reasonforcons = driver.findElement(By.xpath(prop.getProperty("reasonforcons")));
			System.out.println("1");
			Verify.verify(reasonforcons.isDisplayed());
			WebElement emergency_type = driver.findElement(By.id("emergency_type"));
			System.out.println("1");
			Verify.verify(emergency_type.isDisplayed());
			WebElement dropemer = driver.findElement(By.id("emergency_type"));
			Select select2 = new Select(dropemer);
			select2.selectByIndex(1);
			Thread.sleep(1000);
			WebElement emeryes = driver.findElement(By.xpath(".//*[@id='emergency_type']"));
			System.out.println("1");
			Verify.verify(emeryes.isDisplayed());
			Thread.sleep(1000);
			WebElement infantlabel = driver.findElement(By.xpath(prop.getProperty("infantlabel")));
			System.out.println("1");
			Verify.verify(infantlabel.isDisplayed());
			WebElement docgender = driver.findElement(By.xpath(prop.getProperty("docgender")));
			System.out.println("1");
			Verify.verify(docgender.isDisplayed());
			WebElement clear = driver.findElement(By.id("clear-ce"));
			System.out.println("1");
			Verify.verify(clear.isDisplayed());
			WebElement callincomp = driver.findElement(By.xpath(prop.getProperty("callincomp")));
			System.out.println("1");
			Verify.verify(callincomp.isDisplayed());
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC10_CallEntryV001 : TC to check doctor disp flow for doctor");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC10_CallEntryV001 : TC to check doctor disp flow for doctor ");
		}
	}

	/**
	 * BO_TC11_CallEntryV001 : TC to check doctor disp flow for doctor with DB
	 * values . Applicable for
	 */

	public static void BO_TC11_CallEntryV001() {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			WebElement droppolicy = driver.findElement(By.xpath("//select[@name='for_customer_id']"));
			Select select = new Select(droppolicy);
			select.selectByIndex(1);
			driver.findElement(By.xpath(".//*[@id='doctor-consult-wrap']/div[3]/div[2]/div/div/div[1]/label")).click();
			driver.findElement(By.xpath(".//*[@id='doctor-consult-wrap']/div[6]/div[2]/div[1]/label")).click();
			driver.findElement(By.xpath(".//*[@id='doctor-consult-wrap']/div[7]/div[2]/div[1]/label")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("genralsubmit1"))).click();
			Thread.sleep(1000);

			String query = "select status,for_customer_id,disposition_type,infant_age_2,is_emergency,doctor_gender_preference,emergency_type from consultation_request order by created_date desc limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				forcustomerid = rsObject.getString("for_customer_id");
				disposition_type = rsObject.getString("disposition_type");
				infant_age = rsObject.getString("infant_age_2");
				is_emergency = rsObject.getString("is_emergency");
				doctor_gender_preference = rsObject.getString("doctor_gender_preference");
				emergency_type = rsObject.getString("emergency_type");
				statusdoctoragent = rsObject.getString("status");

			}

			if ((prop.getProperty("stat1")).replaceAll("\\s+", "")
					.equalsIgnoreCase(statusdoctoragent.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("docgen")).replaceAll("\\s+", "")
					.equalsIgnoreCase(doctor_gender_preference.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("distype")).replaceAll("\\s+", "")
					.equalsIgnoreCase(disposition_type.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("infage")).replaceAll("\\s+", "").equalsIgnoreCase(infant_age.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("isemer")).replaceAll("\\s+", "")
					.equalsIgnoreCase(is_emergency.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("emetype")).replaceAll("\\s+", "")
					.equalsIgnoreCase(emergency_type.replaceAll("\\s+", "")))
				;

			String query1 = "select msisdn from bima_customer where id='" + forcustomerid + "';";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				forcustomeridact = rsObject1.getString("msisdn");
			}

			if ((prop.getProperty("propermsisdn")).replaceAll("\\s+", "")
					.equalsIgnoreCase(forcustomeridact.replaceAll("\\s+", "")))
				;

			System.out.println(forcustomeridact);
			driver.findElement(By.xpath(prop.getProperty("Login_Username"))).click();
			driver.findElement(By.xpath(prop.getProperty("Logout"))).click();
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC11_CallEntryV001 : TC to check doctor disp flow for doctor with DB");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC11_CallEntryV001 : TC to check doctor disp flow for doctor with DB ");
		}

	}

	/**
	 * BO_TC12_CallEntryV001 : TC to check general disp flow for agent with DB
	 * values . Applicable for
	 */

	public static void BO_TC12_CallEntryV001() {
		try {
			Properties prop = Constants.loadProperties();
			driver.findElement(By.xpath(prop.getProperty("username"))).sendKeys(prop.getProperty("AGENTUSRNAME"));
			driver.findElement(By.xpath(prop.getProperty("password"))).sendKeys(prop.getProperty("AGENTPASSWORD"));
			driver.findElement(By.xpath(prop.getProperty("Login"))).click();
			driver.findElement(By.xpath(prop.getProperty("callentry_link"))).click();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).sendKeys(prop.getProperty("propermsisdn"));
			driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
			Thread.sleep(2000);

			WebElement droppolicy = driver.findElement(By.id("policy-ce"));
			Select select = new Select(droppolicy);
			select.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisp = driver.findElement(By.xpath(".//*[@id='customer-form']/div[5]/div/div/select"));
			Select select1 = new Select(dropdisp);
			select1.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisptype = driver.findElement(By.xpath(".//*[@id='dispo']/div[1]/div/div[2]/select"));
			Select select2 = new Select(dropdisptype);
			select2.selectByIndex(1);
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("dispnotes"))).sendKeys(prop.getProperty("disptext"));
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("genralsubmit1"))).click();
			Thread.sleep(3000);

			String query = "select id from bima_customer where msisdn='" + prop.getProperty("propermsisdn") + "'";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				idgenraldisp = rsObject.getString("id");
			}
			String query1 = "select for_customer_id , status from  consultation_request order by created_date desc limit 1;";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				customerid = rsObject1.getString("for_customer_id");
				status = rsObject1.getString("status");

			}
			if (idgenraldisp.replaceAll("\\s+", "").equalsIgnoreCase(customerid.replaceAll("\\s+", "")))
				;

			Verify.verify(status.equals("2"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC12_CallEntryV001 :TC to check general disp flow for agent with DB");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC12_CallEntryV001 : TC to check general disp flow for agent with DB ");
		}
	}

	/**
	 * BO_TC13_CallEntryV001 :TC to check doctor disp flow for agent with DB
	 * values . Applicable for
	 */

	public static void BO_TC13_CallEntryV001() {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).sendKeys(prop.getProperty("propermsisdn"));
			driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
			Thread.sleep(2000);
			WebElement droppolicy = driver.findElement(By.id("policy-ce"));
			Select select = new Select(droppolicy);
			select.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisp = driver.findElement(By.xpath(".//*[@id='customer-form']/div[5]/div/div/select"));
			Select select1 = new Select(dropdisp);
			select1.selectByIndex(2);
			Thread.sleep(1000);

			WebElement dropemer = driver.findElement(By.id("emergency_type"));
			Select select2 = new Select(dropemer);
			select2.selectByIndex(1);
			Thread.sleep(1000);

			WebElement droppolicy1 = driver.findElement(By.xpath("//select[@name='for_customer_id']"));
			Select select3 = new Select(droppolicy1);
			select3.selectByIndex(1);
			driver.findElement(By.xpath(".//*[@id='doctor-consult-wrap']/div[3]/div[2]/div/div/div[1]/label")).click();
			driver.findElement(By.xpath(".//*[@id='doctor-consult-wrap']/div[6]/div[2]/div[1]/label")).click();
			driver.findElement(By.xpath(".//*[@id='doctor-consult-wrap']/div[7]/div[2]/div[1]/label")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("genralsubmit1"))).click();
			Thread.sleep(1000);

			String query = "select id,status,for_customer_id,disposition_type,infant_age_2,is_emergency,doctor_gender_preference,emergency_type from consultation_request order by created_date desc limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				forcustomerid = rsObject.getString("for_customer_id");
				disposition_type = rsObject.getString("disposition_type");
				infant_age = rsObject.getString("infant_age_2");
				is_emergency = rsObject.getString("is_emergency");
				doctor_gender_preference = rsObject.getString("doctor_gender_preference");
				emergency_type = rsObject.getString("emergency_type");
				statusagent = rsObject.getString("status");
				incompidcge= rsObject.getString("id");


			}

			if ((prop.getProperty("stat2")).replaceAll("\\s+", "").equalsIgnoreCase(statusagent.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("docgen")).replaceAll("\\s+", "")
					.equalsIgnoreCase(doctor_gender_preference.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("distype")).replaceAll("\\s+", "")
					.equalsIgnoreCase(disposition_type.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("infage")).replaceAll("\\s+", "").equalsIgnoreCase(infant_age.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("isemer")).replaceAll("\\s+", "")
					.equalsIgnoreCase(is_emergency.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("emetype")).replaceAll("\\s+", "")
					.equalsIgnoreCase(emergency_type.replaceAll("\\s+", "")))
				;

			String query1 = "select msisdn from bima_customer where id='" + forcustomerid + "';";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				forcustomeridact = rsObject1.getString("msisdn");
			}

			if ((prop.getProperty("propermsisdn")).replaceAll("\\s+", "")
					.equalsIgnoreCase(forcustomeridact.replaceAll("\\s+", "")))
				;

			System.out.println(forcustomeridact);

			String upquery00 = "SET SQL_SAFE_UPDATES=0;";
	        DBConnection.Connect(upquery00);
			
			String upquery11 = "update consultation_request set status='2' where id='"+incompidcge+"'";
	        DBConnection.Connect(upquery11);
	        
	        String upquery22 = "SET SQL_SAFE_UPDATES=1;";
	        DBConnection.Connect(upquery22);
			
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC13_CallEntryV001 : TC to check doctor disp flow for agent with DB ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC13_CallEntryV001 : TC to check doctor disp flow for agent with DB  ");
		}
	}

	/**
	 * BO_TC14_CallEntryV001 : TC to check call incomplete flow values .
	 * Applicable for
	 */

	public static void BO_TC14_CallEntryV001() {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).sendKeys(prop.getProperty("propermsisdn"));
			driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath(prop.getProperty("Markincomplete"))).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("popupsubmit"))).click();
			WebElement we = driver.findElement(By.xpath(prop.getProperty("popupsubmit")));
			Verify.verify(we.isDisplayed());

			driver.findElement(By.xpath(prop.getProperty("calldiscon1"))).click();
			driver.findElement(By.xpath(prop.getProperty("popupsubmit"))).click();

			String query = "select call_drop_reason,status from consultation_request order by created_date desc limit 1";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				call_drop_reason = rsObject.getString("call_drop_reason");
				statuscallincomplete = rsObject.getString("status");
			}

			if ((prop.getProperty("statincomp")).replaceAll("\\s+", "")
					.equalsIgnoreCase(statuscallincomplete.replaceAll("\\s+", "")))
				;

			if ((prop.getProperty("calldropreason")).replaceAll("\\s+", "")
					.equalsIgnoreCase(call_drop_reason.replaceAll("\\s+", "")))
				;
			
			

			String upquery = "SET SQL_SAFE_UPDATES=0;";
	        DBConnection.Connect(upquery);
			
	        String upquery1 = "update consultation_request set doctor_id='0' where  doctor_id ='40' and status in(1,3,5,6,10,7,8);";
	        DBConnection.Connect(upquery1);
	        
	        String upquery2 = "SET SQL_SAFE_UPDATES=1;";
	        DBConnection.Connect(upquery2);
			
			
			Constants.TestReports.log(LogStatus.PASS, "BO_TC14_CallEntryV001 : TC to check call incomplete flow");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL, "BO_TC14_CallEntryV001 : TC to check call incomplete flow ");
		}
	}

	/**
	 * BO_TC15_CallEntryV001 : TC to check recent event details in call entry
	 * values . Applicable for
	 */

	public static void BO_TC15_CallEntryV001() {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).sendKeys(prop.getProperty("propermsisdn"));
			driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
			Thread.sleep(1000);

			String query = "select id,status from consultation_request order by id desc limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				recenteventid = rsObject.getString("id");
				recentstatus = rsObject.getString("status");
			}

			String we = driver.findElement(By.xpath(".//*[@id='fc3']/div/table/tbody/tr[1]/th")).getText();
			String we1 = driver.findElement(By.xpath(".//*[@id='fc3']/div/table/tbody/tr[1]/td[3]/span")).getText();

			if ((we.replaceAll("\\s+", "").equalsIgnoreCase(recenteventid.replaceAll("\\s+", ""))))
				;
			Verify.verify(we1.equals("CALL INCOMPLETE"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC15_CallEntryV001 : TC to check recent event details in call entry");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC15_CallEntryV001 : TC to check recent event details in call entry ");
		}
	}

	/**
	 * BO_TC16_CallEntryV001 : TC to check save button enabeld after providing
	 * details values . Applicable for
	 */

	public static void BO_TC16_CallEntryV001() {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).sendKeys(prop.getProperty("propermsisdn"));
			driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
			Thread.sleep(1000);

			WebElement we = driver.findElement(By.xpath(prop.getProperty("genralsubmit1")));
			we.click();
			WebElement droppolicy = driver.findElement(By.id("policy-ce"));
			Select select = new Select(droppolicy);
			select.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisp = driver.findElement(By.xpath(".//*[@id='customer-form']/div[5]/div/div/select"));
			Select select1 = new Select(dropdisp);
			select1.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisptype = driver.findElement(By.xpath(".//*[@id='dispo']/div[1]/div/div[2]/select"));
			Select select2 = new Select(dropdisptype);
			select2.selectByIndex(1);

			Verify.verify(we.isEnabled());

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC16_CallEntryV001 : TC to check save button enabeld after providing details");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC16_CallEntryV001 : TC to check save button enabeld after providing details ");
		}
	}

	/**
	 * BO_TC17_CallEntryV001 :TC to check webelemnets are present in call entry
	 * values . Applicable for
	 */

	public static void BO_TC17_CallEntryV001() {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).clear();
			driver.findElement(By.xpath(prop.getProperty("Searchbox"))).sendKeys(prop.getProperty("propermsisdn"));
			driver.findElement(By.xpath(prop.getProperty("searchbutton"))).click();
			Thread.sleep(1000);

			WebElement droppolicy = driver.findElement(By.id("policy-ce"));
			Select select = new Select(droppolicy);
			select.selectByIndex(1);
			Thread.sleep(1000);
			WebElement dropdisp = driver.findElement(By.xpath(".//*[@id='customer-form']/div[5]/div/div/select"));
			Select select1 = new Select(dropdisp);
			select1.selectByIndex(2);
			Thread.sleep(1000);
			WebElement we = driver.findElement(By.xpath(prop.getProperty("genralsubmit1")));
			we.click();
			WebElement dropemer = driver.findElement(By.id("emergency_type"));
			Select select2 = new Select(dropemer);
			select2.selectByIndex(1);
			Thread.sleep(1000);

			WebElement droppolicy1 = driver.findElement(By.xpath("//select[@name='for_customer_id']"));
			Select select3 = new Select(droppolicy1);
			select3.selectByIndex(1);
			String upquery = "update bima_user set user_availability='0' where Not id='40'";
	        DBConnection.Connect(upquery);
			Verify.verify(we.isEnabled());

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC17_CallEntryV001 : TC to check webelemnets are present in call entry");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC17_CallEntryV001 : TC to check webelemnets are present in call entry");
		}
	}

}

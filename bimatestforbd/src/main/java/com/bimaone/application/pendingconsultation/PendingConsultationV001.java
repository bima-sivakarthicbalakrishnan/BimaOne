package com.bimaone.application.pendingconsultation;

import java.sql.ResultSet;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.bimaone.framework.constants.Constants;
import com.bimaone.framework.dal.DBConnection;
import com.google.common.base.Verify;
import com.relevantcodes.extentreports.LogStatus;

public class PendingConsultationV001 extends Constants {

	public static String addchefcom;
	public static String addchefcomnote;
	public static String othsitchefcom;
	public static String acutechefcom;
	public static String allerg;
	public static String vaccin;
	public static String past_medi;
	public static String medical_cond;
	public static String preg;
	public static String breast_feed;
	public static String married;
	public static String weigh;
	public static String immuniz;
	public static String diagn;
	public static String icd_cod;
	public static String medicine_nam;
	public static String durat;
	public static String duration_un;
	public static String dos;
	public static String dosage_un;
	public static String quant;
	public static String quantity_un;
	public static String quantity_fr;
	public static String followup_int;
	public static String ref_to_er;
	public static String comm_cons;
	public static String current_compl;
	public static String situation_typ;
	public static String milvik_proto;
	public static String currcomm;
	public static String heart_rat;
	public static String respiratory_rat;
	public static String blood_press_d;
	public static String blood_press_s;
	public static String tempera;
	public static String recenteventid;
	public static String comp_stat ;
	public static String followup_stat ;
	public static String followup_int_yes ;
	public static String incomp_stat ;
	public static String cod_followup ;
	public static String fp_id ;
	public static String comp_stat_fp ;
	public static String followed_treatment_ ;
	public static String condition_improved_ ;
	public static String fp_stat_fp ;
	public static String incomp_stat_fp ;

	
	
	
	/**
	 * BO_TC01_PendingConsultationV001 : TC to create a pending consultation.
	 * Applicable for
	 */

	public static void BO_TC01_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			String upquery = "update bima_user set user_availability='0' where Not id='40'";
	        DBConnection.Connect(upquery);
			driver.findElement(By.xpath(prop.getProperty("callentry_link"))).click();
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

			String query = "select id from consultation_request order by created_date desc limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				recenteventid = rsObject.getString("id");
			}

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC01_PendingConsultationV001 : TC to create a pending consultation");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC01_PendingConsultationV001 : TC to create a pending consultation");
		}
	}

	/**
	 * BO_TC02_PendingConsultationV001 : TC to check cheif comp tab to follow up tab are displaying.
	 * Applicable for
	 */

	public static void BO_TC02_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);

			String we = driver.findElement(By.xpath(prop.getProperty("msisdnpencon"))).getAttribute("value");
			System.out.println(we);
			Verify.verify(we.equals(prop.getProperty("propermsisdn")));
			String we1 = driver.findElement(By.id("cc")).getText();
			Verify.verify(we1.equals("Chief Complaint"));
			String we2 = driver.findElement(By.id("pmh")).getText();
			we2.equals("Past Medical History");
			String we3 = driver.findElement(By.id("hpi")).getText();
			Verify.verify(we3.equals("History of Present Illness"));
			String we4 = driver.findElement(By.id("ds")).getText();
			Verify.verify(we4.equals("Diagnosis"));
			String we5 = driver.findElement(By.id("tt")).getText();
			Verify.verify(we5.equals("Treatment"));
			String we6 = driver.findElement(By.id("fu")).getText();
			Verify.verify(we6.equals("Follow-Up"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC02_PendingConsultationV001 : TC to check cheif comp tab to follow up tab are displaying");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC02_PendingConsultationV001 : TC to check cheif comp tab to follow up tab are displaying");
		}
	}

	/**
	 * BO_TC03_PendingConsultationV001 : TC to check Cheif complaint webelements .
	 * Applicable for
	 */

	public static void BO_TC03_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);

			String we = driver.findElement(By.xpath(prop.getProperty("acuteminor"))).getText();
			Assert.assertEquals(we, "ACUTE MINOR AILMENTS :");
			String we1 = driver.findElement(By.xpath(prop.getProperty("otherSit"))).getText();
			Assert.assertEquals(we1, "OTHER SITUATION :");
			WebElement we2 = driver.findElement(By.xpath(prop.getProperty("address")));
			Verify.verify(we2.isDisplayed());
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC03_PendingConsultationV001 : TC to check Cheif complaint webelements");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC03_PendingConsultationV001 : TC to check Cheif complaint webelements");
		}
	}

	/**
	 * BO_TC04_PendingConsultationV001 : TC to save cheif complaints details .
	 * Applicable for
	 */

	public static void BO_TC04_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);

			driver.findElement(By.id("Fever")).click();
			// driver.findElement(By.id("Cough")).click();
			Thread.sleep(1000);

			driver.findElement(By.id("General Health Advice")).click();
			// driver.findElement(By.id("Nutrition")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath("//label[text()='Addressable']")).click();
			driver.findElement(By.xpath(prop.getProperty("addressabletext"))).sendKeys("testautomation");
			driver.findElement(By.xpath(prop.getProperty("continue"))).click();

			String query = "select addressability from consultation order by created_date desc limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				addchefcom = rsObject.getString("addressability");
			}
			Verify.verify(addchefcom.equals("ADDRESSABLE"));

			String query1 = "Select addressability_note from consultation_chief_complaint order by created_date desc limit 1;";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				addchefcomnote = rsObject1.getString("addressability_note");
			}
			Verify.verify(addchefcomnote.equals("testautomation"));

			String query2 = "select other_situations from consultation_chief_complaint_other_situations order by created_date desc limit 1;";
			ResultSet rsObject2 = DBConnection.DBConnect(query2);
			while (rsObject2.next()) {
				othsitchefcom = rsObject2.getString("other_situations");
			}
			Verify.verify(othsitchefcom.equals("GENERAL_HEALTH_ADVICE"));

			String query3 = "select acute_minor_ailments from consultation_chief_complaint_acute_minor_ailments order by created_date desc limit 1;";
			ResultSet rsObject3 = DBConnection.DBConnect(query3);
			while (rsObject3.next()) {
				acutechefcom = rsObject3.getString("acute_minor_ailments");
			}
			Verify.verify(acutechefcom.equals("FEVER"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC04_PendingConsultationV001 : TC to save cheif complaints details .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC04_PendingConsultationV001 : TC to save cheif complaints details .");
		}
	}

	/**
	 * BO_TC05_PendingConsultationV001 : TC to check past medical history elements .
	 * Applicable for
	 */

	public static void BO_TC05_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);

			String we = driver.findElement(By.xpath(prop.getProperty("precond"))).getText();
			Verify.verify(we.equals("PREVIOUS CONDITIONS :"));
			String we1 = driver.findElement(By.xpath(prop.getProperty("para"))).getText();
			Verify.verify(we1.equals("PARAMETERS :"));
			/*
			 * String we2 =
			 * driver.findElement(By.xpath(prop.getProperty("fempat"))).getText(
			 * ); Verify.verify(we2.equals("* For Female Patients Only"));
			 * String we3 =
			 * driver.findElement(By.xpath(prop.getProperty("infant"))).getText(
			 * ); Verify.verify(we3.equals("* For Infants Only"));
			 */
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC05_PendingConsultationV001 : TC to check past medical history elements .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC05_PendingConsultationV001 : TC to check past medical history elements . ");
		}
	}

	/**
	 * BO_TC06_PendingConsultationV001 : TC to save past medical hist values .
	 * Applicable for
	 */

	public static void BO_TC06_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			driver.findElement(By.id("Smoker")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("aller"))).sendKeys("testautomation");
			driver.findElement(By.xpath(prop.getProperty("vacci"))).sendKeys("testautomation");
			// driver.findElement(By.xpath(prop.getProperty("pastmedi"))).sendKeys("testautomation");
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("weig"))).clear();
			driver.findElement(By.xpath(prop.getProperty("weig"))).sendKeys("70");
			driver.findElement(By.xpath(prop.getProperty("feet"))).clear();
			driver.findElement(By.xpath(prop.getProperty("feet"))).sendKeys("5");
			driver.findElement(By.xpath(prop.getProperty("inch"))).clear();
			driver.findElement(By.xpath(prop.getProperty("inch"))).sendKeys("0");
			/*
			 * Thread.sleep(1000);
			 * driver.findElement(By.xpath(prop.getProperty("preg"))).click();
			 * driver.findElement(By.xpath(prop.getProperty("brefeed"))).click()
			 * ;
			 * driver.findElement(By.xpath(prop.getProperty("married"))).click()
			 * ;
			 * driver.findElement(By.xpath(prop.getProperty("immun"))).click();
			 */
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("continue"))).click();

			String query = "select allergies,vaccination,past_medication from past_medical_history order by created_date desc limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				allerg = rsObject.getString("allergies");
				vaccin = rsObject.getString("vaccination");
				// past_medi = rsObject.getString("past_medication");

			}
			Verify.verify(allerg.equals("testautomation"));
			Verify.verify(vaccin.equals("testautomation"));
			// Verify.verify(past_medi.equals("testautomation"));

			String query1 = "select medical_condition from past_medical_conditions order by created_date desc limit 1;";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				medical_cond = rsObject1.getString("medical_condition");
			}
			Verify.verify(medical_cond.equals("SMOKER"));

			String query2 = "select weight from physical_parameters order by created_date desc limit 1;";
			ResultSet rsObject2 = DBConnection.DBConnect(query2);
			while (rsObject2.next()) {
				weigh = rsObject2.getString("weight");
			}
			Verify.verify(weigh.equals("70.00"));

			/*
			 * String query3 =
			 * "select pregnant,breast_feeding,married from female_questions order by id desc limit 1;"
			 * ; ResultSet rsObject3 = DBConnection.DBConnect(query3); while
			 * (rsObject3.next()) { preg = rsObject3.getString("pregnant");
			 * breast_feed = rsObject3.getString("breast_feeding"); married =
			 * rsObject3.getString("married");
			 * 
			 * } Verify.verify(preg.equals("1"));
			 * Verify.verify(breast_feed.equals("1"));
			 * Verify.verify(married.equals("1"));
			 * 
			 * String query4 =
			 * "Select immunization from infant_questions order by id desc limit 1;"
			 * ; ResultSet rsObject4 = DBConnection.DBConnect(query4); while
			 * (rsObject4.next()) { immuniz =
			 * rsObject4.getString("immunization"); }
			 * Verify.verify(immuniz.equals("1"));
			 */

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC06_PendingConsultationV001 : TC to save past medical hist values .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC06_PendingConsultationV001 : TC to save past medical hist values . ");
		}
	}

	/**
	 * BO_TC07_PendingConsultationV001 : TC to check history for present illness elements .
	 * Applicable for
	 */

	public static void BO_TC07_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);

			String we = driver.findElement(By.xpath(prop.getProperty("currcomp"))).getText();
			Verify.verify(we.equals("PLEASE ENTER CURRENT COMPLAINT"));
			String we1 = driver.findElement(By.xpath(prop.getProperty("examina"))).getText();
			Verify.verify(we1.equals("EXAMINATION"));
			String we2 = driver.findElement(By.xpath(prop.getProperty("consulmilvik"))).getText();
			Verify.verify(we2.contains("Consulted Milvik's Protocol? :"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC07_PendingConsultationV001 : TC to check history for present illness elements .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC07_PendingConsultationV001 : TC to check history for present illness elements . ");
		}
	}

	/**
	 * BO_TC08_PendingConsultationV001 : TC to save the present illness values .
	 * Applicable for
	 */

	public static void BO_TC08_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("currcomptext"))).sendKeys("testautomation");
			driver.findElement(By.xpath(prop.getProperty("currcompcomm"))).sendKeys("testautomation");
			Thread.sleep(1000);
			WebElement dropdown = Constants.driver.findElement(By.id("typeOfProtocolId"));
			Select select = new Select(dropdown);
			select.selectByIndex(1);
			WebElement dropdown1 = Constants.driver.findElement(By.id("getProtocol"));
			Select select1 = new Select(dropdown1);
			select1.selectByIndex(1);
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("exhr"))).sendKeys("70");
			driver.findElement(By.xpath(prop.getProperty("exrr"))).sendKeys("70");
			driver.findElement(By.xpath(prop.getProperty("exbp1"))).sendKeys("70");
			driver.findElement(By.xpath(prop.getProperty("exbp2"))).sendKeys("70");
			driver.findElement(By.xpath(prop.getProperty("ext"))).sendKeys("70");
			driver.findElement(By.xpath(prop.getProperty("consyes"))).click();
			driver.findElement(By.xpath(prop.getProperty("continue"))).click();
			Thread.sleep(1000);

			String query = "select current_complaint,situation_type,milvik_protocol,comments from current_complaint_details order by created_date desc limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				current_compl = rsObject.getString("current_complaint");
				situation_typ = rsObject.getString("situation_type");
				milvik_proto = rsObject.getString("milvik_protocol");
				currcomm = rsObject.getString("comments");
			}
			Verify.verify(current_compl.equals("testautomation"));
			Verify.verify(situation_typ.equals("ACUTE_MINOR_AILMENT"));
			Verify.verify(milvik_proto.equals("1_1_FEVER"));
			Verify.verify(currcomm.equals("testautomation"));

			String query1 = "select heart_rate,respiratory_rate,blood_pressure_d,blood_pressure_s,temperature from current_examination order by created_date desc limit 1;";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				heart_rat = rsObject1.getString("heart_rate");
				respiratory_rat = rsObject1.getString("respiratory_rate");
				blood_press_d = rsObject1.getString("blood_pressure_d");
				blood_press_s = rsObject1.getString("blood_pressure_s");
				tempera = rsObject1.getString("temperature");
			}
			Verify.verify(heart_rat.equals("70"));
			Verify.verify(respiratory_rat.equals("70"));
			Verify.verify(blood_press_d.equals("70"));
			Verify.verify(blood_press_s.equals("70"));
			Verify.verify(tempera.equals("70"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC08_PendingConsultationV001 : TC to save the present illness values ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC08_PendingConsultationV001 : TC to save the present illness values ");
		}
	}

	/**
	 * BO_TC09_PendingConsultationV001 : TC to check diagnosis elements.
	 * Applicable for
	 */

	public static void BO_TC09_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);

			WebElement we = driver.findElement(By.id("add-diagnosis"));
			Verify.verify(we.isDisplayed());
			WebElement we3 = driver.findElement(By.xpath(prop.getProperty("tick")));
			Verify.verify(we3.isDisplayed());
			driver.findElement(By.id("add-diagnosis")).click();
			// WebElement we1 =
			// driver.findElement(By.xpath(prop.getProperty("icd10first")));
			// Verify.verify(we1.isDisplayed());
			// WebElement we2 =
			// driver.findElement(By.xpath(prop.getProperty("diagtext")));
			// Verify.verify(we2.isDisplayed());

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC09_PendingConsultationV001 : TC to check diagnosis elements.");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC09_PendingConsultationV001 : TC to check diagnosis elements. ");
		}
	}

	/**
	 * BO_TC10_PendingConsultationV001 : TC to add and save a diagnosis details .
	 * Applicable for
	 */

	public static void BO_TC10_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("icd10first"))).click();
			driver.findElement(By.xpath(prop.getProperty("icd10text"))).sendKeys("test");
			Thread.sleep(3000);
			driver.findElement(By.xpath(prop.getProperty("selectid"))).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("diagtext"))).sendKeys("test");
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("subdiagpopup"))).click();
			Thread.sleep(1000);

			String query = "select diagnosis,icd_code from diagnosis order by created_date desc limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				diagn = rsObject.getString("diagnosis");
				icd_cod = rsObject.getString("icd_code");

			}
			Verify.verify(diagn.equals("test"));
			Verify.verify(icd_cod.equals("A04"));
			driver.findElement(By.xpath(prop.getProperty("continue"))).click();
			Thread.sleep(1000);
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC10_PendingConsultationV001 : TC to add and save a diagnosis details ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC10_PendingConsultationV001 : TC to add and save a diagnosis details  ");
		}
	}

	/**
	 * BO_TC11_PendingConsultationV001 : TC to check treatment elements .
	 * Applicable for
	 */

	public static void BO_TC11_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			WebElement we = driver.findElement(By.xpath(".//*[@id='c2']/div/div/button"));
			Verify.verify(we.isDisplayed());
			WebElement we1 = driver.findElement(By.xpath(prop.getProperty("medicpreslabel")));
			Verify.verify(we1.isDisplayed());
			WebElement we2 = driver.findElement(By.xpath(prop.getProperty("outcomelable")));
			Verify.verify(we2.isDisplayed());
			WebElement we3 = driver.findElement(By.xpath(prop.getProperty("infcons")));
			Verify.verify(we3.isDisplayed());
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC11_PendingConsultationV001 : TC to check treatment elements .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC11_PendingConsultationV001 : TC to check treatment elements .");
		}
	}

	/**
	 * BO_TC12_PendingConsultationV001 : TC to add and save a medication details and complete a pending consultation .
	 * Applicable for
	 */

	public static void BO_TC12_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			driver.findElement(By.xpath(".//*[@id='c2']/div/div/button")).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("medicname"))).sendKeys("test");
			driver.findElement(By.xpath(prop.getProperty("dos"))).sendKeys("2");
			driver.findElement(By.xpath(prop.getProperty("medidur"))).sendKeys("2");
			driver.findElement(By.xpath(prop.getProperty("mediquan"))).sendKeys("2");
			Thread.sleep(1000);

			WebElement drop = driver.findElement(By.xpath(prop.getProperty("dosunit")));
			Select select = new Select(drop);
			select.selectByIndex(1);
			Thread.sleep(1000);

			WebElement drop1 = driver.findElement(By.xpath(prop.getProperty("durunit")));
			Select select1 = new Select(drop1);
			select1.selectByIndex(1);
			Thread.sleep(1000);

			WebElement drop2 = driver.findElement(By.xpath(prop.getProperty("quantype")));
			Select select2 = new Select(drop2);
			select2.selectByIndex(1);
			Thread.sleep(1000);

			WebElement drop3 = driver.findElement(By.xpath(prop.getProperty("quatfreq")));
			Select select3 = new Select(drop3);
			select3.selectByIndex(1);
			driver.findElement(By.xpath(prop.getProperty("submedipopup"))).click();

			Thread.sleep(1000);

			driver.findElement(By.xpath(prop.getProperty("nofollowup"))).click();
			driver.findElement(By.xpath(prop.getProperty("refftoer"))).click();
			driver.findElement(By.xpath(prop.getProperty("complete"))).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath(".//*[@id='informed_consent_dialog']/div/div[2]/div[1]/div/div/div[2]/label")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("call-customer-input-submit")).click();
			Thread.sleep(1000);

			

			String query = "select medicine_name,duration,duration_unit,dosage,dosage_unit,quantity,quantity_unit,quantity_freq from medical_prescription order by created_date desc limit 1;";
			ResultSet rsObject = DBConnection.DBConnect(query);
			while (rsObject.next()) {
				medicine_nam = rsObject.getString("medicine_name");
				durat = rsObject.getString("duration");
				duration_un = rsObject.getString("duration_unit");
				dos = rsObject.getString("dosage");
				dosage_un = rsObject.getString("dosage_unit");
				quant = rsObject.getString("quantity");
				quantity_un = rsObject.getString("quantity_unit");
				quantity_fr = rsObject.getString("quantity_freq");

			}
			Verify.verify(medicine_nam.equals("test"));
			Verify.verify(durat.equals("2"));
			Verify.verify(duration_un.equals("DAYS"));
			Verify.verify(dos.equals("2"));
			Verify.verify(dosage_un.equals("G"));
			Verify.verify(quant.equals("2"));
			Verify.verify(quantity_un.equals("TABLET"));
			Verify.verify(quantity_fr.equals("EVERY_4_HOURS"));

			String query1 = "select followup_interval,referred_to_er from consultation_outcome order by created_date desc limit 1;";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				followup_int = rsObject1.getString("followup_interval");
				ref_to_er = rsObject1.getString("referred_to_er");

			}
			Verify.verify(followup_int.equals("0"));
			Verify.verify(ref_to_er.equals("1"));

			String query2 = "select communication_consent from consultation order by created_date desc limit 1;";
			ResultSet rsObject2 = DBConnection.DBConnect(query2);
			while (rsObject2.next()) {
				comm_cons = rsObject2.getString("communication_consent");
			}
			//Verify.verify(comm_cons.equals("1"));
			
			String query3 = "Select status , call_on_date from consultation_request order by created_date desc limit 1;";
			ResultSet rsObject3 = DBConnection.DBConnect(query3);
			while (rsObject3.next()) {
				comp_stat = rsObject3.getString("status");
				cod_followup = rsObject3.getString("call_on_date");

			}
			Verify.verify(comp_stat.equals("2"));


			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC12_PendingConsultationV001 : TC to add and save a medication details and complete a pending consultation ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC12_PendingConsultationV001 : TC to add and save a medication details and complete a pending consultation ");
		}
	}

	/**
	 * BO_TC13_PendingConsultationV001 : TC to check last call summary and past events elements.
	 * Applicable for
	 */

	public static void BO_TC13_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("callentry_link"))).click();
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

			WebElement we1 = driver.findElement(By.xpath(prop.getProperty("lschead")));
			Verify.verify(we1.isDisplayed());
			WebElement we2 = driver.findElement(By.xpath(prop.getProperty("pechead")));
			Verify.verify(we2.isDisplayed());
			WebElement we3 = driver.findElement(By.xpath(prop.getProperty("lscconsfor")));
			Verify.verify(we3.isDisplayed());

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC13_PendingConsultationV001 : TC to check last call summary and past events elements.");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC13_PendingConsultationV001 : TC to check last call summary and past events elements.");
		}
	}

	/**
	 * BO_TC14_PendingConsultationV001 : TC to check last call and past event data with DB.
	 * Applicable for
	 */

	public static void BO_TC14_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			String we1 = driver.findElement(By.xpath(prop.getProperty("diagdetail"))).getText();
			Verify.verify(we1.equals("test"));
			String we2 = driver.findElement(By.xpath(prop.getProperty("medidetail"))).getText();
			Verify.verify(we2.equals("test"));
			String we3 = driver.findElement(By.xpath(prop.getProperty("pecdiagdetail"))).getText();
			Verify.verify(we3.equals("test"));
			String we4 = driver.findElement(By.xpath(prop.getProperty("peceveid"))).getText();
			Verify.verify(we4.equals(recenteventid));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC14_PendingConsultationV001 : TC to check last call and past event data with DB.");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC14_PendingConsultationV001 : TC to check last call and past event data with DB. ");
		}
	}

	/**
	 * BO_TC15_PendingConsultationV001 : TC to check follow up pending consultation flow .
	 * Applicable for
	 */

	public static void BO_TC15_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			driver.findElement(By.id("tt")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath(prop.getProperty("yesfollowup"))).click();
			driver.findElement(By.xpath(prop.getProperty("refftoer"))).click();
			driver.findElement(By.xpath(prop.getProperty("complete"))).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath(".//*[@id='informed_consent_dialog']/div/div[2]/div[1]/div/div/div[2]/label")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("call-customer-input-submit")).click();
			Thread.sleep(1000);

			String query1 = "select followup_interval from consultation_outcome order by created_date desc limit 1;";
			ResultSet rsObject1 = DBConnection.DBConnect(query1);
			while (rsObject1.next()) {
				followup_int_yes = rsObject1.getString("followup_interval");

			}
			Verify.verify(followup_int_yes.equals("3"));

			String query3 = "Select id ,status from consultation_request order by created_date desc limit 1;";
			ResultSet rsObject3 = DBConnection.DBConnect(query3);
			while (rsObject3.next()) {
				followup_stat = rsObject3.getString("status");
				fp_id = rsObject3.getString("id");

			}
			Verify.verify(followup_stat.equals("4"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC15_PendingConsultationV001 : TC to check follow up pending consultation flow .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC15_PendingConsultationV001 : TC to check follow up pending consultation flow . ");
		}
	}
	
	
	/**
	 * BO_TC18_PendingConsultationV001 : TC to make doctor online and offline .
	 * Applicable for
	 */

	public static void BO_TC18_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			
			String upquery = "update consultation_request set call_on_date='"+cod_followup+"' order by created_date desc limit 1";
	        DBConnection.Connect(upquery);
	        
	        driver.findElement(By.xpath(prop.getProperty("offline"))).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(prop.getProperty("online"))).click();
			Thread.sleep(5000);
			String st= driver.findElement(By.xpath(prop.getProperty("fpid"))).getText();
            Verify.verify(st.equals(fp_id)); 
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC18_PendingConsultationV001 : TC to make doctor online and offline .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC18_PendingConsultationV001 : TC to make doctor online and offline .");
		}
	}
	
	/**
	 * BO_TC19_PendingConsultationV001 : TC to check complete the followup .
	 * Applicable for
	 */

	public static void BO_TC19_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			
	        driver.findElement(By.xpath(prop.getProperty("fidaccept"))).click();
			Thread.sleep(5000);
	        //driver.findElement(By.xpath(prop.getProperty("isfpid"))).click();
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("isfpid"))));
			element.click();
	        driver.findElement(By.xpath(prop.getProperty("fpidcond"))).click();
	        driver.findElement(By.xpath(prop.getProperty("nofpid"))).click();
	        driver.findElement(By.xpath(prop.getProperty("noerfpid"))).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("complete"))).click();
			Thread.sleep(1000);
			driver.findElement(By.id("call-customer-input-submit")).click();
			Thread.sleep(1000);
			String query2 = "select followed_treatment,condition_improved from consultation_followup order by created_date desc limit 1;";
			ResultSet rsObject2 = DBConnection.DBConnect(query2);
			while (rsObject2.next()) {
				followed_treatment_ = rsObject2.getString("followed_treatment");
				condition_improved_ = rsObject2.getString("condition_improved");
			}
			//Verify.verify(followed_treatment_.equals("0"));
			//Verify.verify(condition_improved_.equals("1"));
			
			String query3 = "Select status from consultation_request order by created_date desc limit 1;";
			ResultSet rsObject3 = DBConnection.DBConnect(query3);
			while (rsObject3.next()) {
				comp_stat_fp = rsObject3.getString("status");
			}
			Verify.verify(comp_stat_fp.equals("2"));
			
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC19_PendingConsultationV001 : TC to check complete the followup ");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC19_PendingConsultationV001 : TC to check complete the followup ");
		}
	}
	
	
	/**
	 * BO_TC20_PendingConsultationV001 : TC to make a followup in to another follow up.
	 * Applicable for
	 */

	public static void BO_TC20_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("callentry_link"))).click();
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

			Thread.sleep(1000);
			driver.findElement(By.id("tt")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath(prop.getProperty("yesfollowup"))).click();
			driver.findElement(By.xpath(prop.getProperty("refftoer"))).click();
			driver.findElement(By.xpath(prop.getProperty("complete"))).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath(".//*[@id='informed_consent_dialog']/div/div[2]/div[1]/div/div/div[2]/label")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("call-customer-input-submit")).click();
			Thread.sleep(1000);
			
			String upquery = "update consultation_request set call_on_date='"+cod_followup+"' order by created_date desc limit 1";
	        DBConnection.Connect(upquery);
	        
	        driver.findElement(By.xpath(prop.getProperty("offline"))).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(prop.getProperty("online"))).click();
			Thread.sleep(5000);
			//String st= driver.findElement(By.xpath(prop.getProperty("fpid"))).getText();
            //Verify.verify(st.equals(fp_id)); 
            
            driver.findElement(By.xpath(prop.getProperty("fidaccept"))).click();
			//Thread.sleep(5000);
            WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("isfpid"))));
			element.click();
            //driver.findElement(By.xpath(prop.getProperty("isfpid"))).click();
	        driver.findElement(By.xpath(prop.getProperty("fpidcond"))).click();
	        driver.findElement(By.xpath(prop.getProperty("yesfpid"))).click();
	        driver.findElement(By.xpath(prop.getProperty("noerfpid"))).click();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("complete"))).click();
			Thread.sleep(2000);
			
			driver.findElement(By.xpath(".//*[@id='informed_consent_dialog']/div/div[2]/div[1]/div/div/div[2]/label")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("call-customer-input-submit")).click();
			Thread.sleep(1000);
			
			String query3 = "Select status from consultation_request order by created_date desc limit 1;";
			ResultSet rsObject3 = DBConnection.DBConnect(query3);
			while (rsObject3.next()) {
				fp_stat_fp = rsObject3.getString("status");
			}
			Verify.verify(fp_stat_fp.equals("4"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC20_PendingConsultationV001 : TC to make a followup in to another follow up.");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC20_PendingConsultationV001 : TC to make a followup in to another follow up.");
		}
	

	
			
			
}
	

	/**
	 * BO_TC21_PendingConsultationV001 : TC to  make a follow up into a incomplete flow.
	 * Applicable for
	 */

	public static void BO_TC21_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("callentry_link"))).click();
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

			Thread.sleep(1000);
			driver.findElement(By.id("tt")).click();
			Thread.sleep(1000);

			driver.findElement(By.xpath(prop.getProperty("yesfollowup"))).click();
			driver.findElement(By.xpath(prop.getProperty("refftoer"))).click();
			driver.findElement(By.xpath(prop.getProperty("complete"))).click();
			Thread.sleep(3000);
			
			driver.findElement(By.xpath(".//*[@id='informed_consent_dialog']/div/div[2]/div[1]/div/div/div[2]/label")).click();
			Thread.sleep(1000);
			driver.findElement(By.id("call-customer-input-submit")).click();
			Thread.sleep(1000);

			
			String upquery = "update consultation_request set call_on_date='"+cod_followup+"' order by created_date desc limit 1";
	        DBConnection.Connect(upquery);
	        
	        driver.findElement(By.xpath(prop.getProperty("offline"))).click();
			Thread.sleep(5000);
			driver.findElement(By.xpath(prop.getProperty("online"))).click();
			Thread.sleep(5000);
			//String st= driver.findElement(By.xpath(prop.getProperty("fpid"))).getText();
            //Verify.verify(st.equals(fp_id)); 
            Constants.getscreenshot();
            driver.findElement(By.xpath(prop.getProperty("fidaccept"))).click();
			Thread.sleep(3000);
			WebDriverWait wait = new WebDriverWait(driver, 60);
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(prop.getProperty("marincompbut"))));
			element.click();
			//driver.findElement(By.xpath(prop.getProperty("marincompbut"))).click();
			Thread.sleep(1000);			
			driver.findElement(By.xpath(prop.getProperty("calldiscon"))).click();
			driver.findElement(By.xpath(prop.getProperty("pendconincomplesubpopup"))).click();
			
			
			String query3 = "Select status from consultation_request order by created_date desc limit 1;";
			ResultSet rsObject3 = DBConnection.DBConnect(query3);
			while (rsObject3.next()) {
				incomp_stat_fp = rsObject3.getString("status");
			}
			Verify.verify(incomp_stat_fp.equals("11"));

			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC21_PendingConsultationV001 : TC to  make a follow up into a incomplete flow.");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC21_PendingConsultationV001 : TC to  make a follow up into a incomplete flow.");
		}
	

	
			
			
}
	
	
	
	
	/**
	 * BO_TC16_PendingConsultationV001 : TC to create and make call incomplete for pending consultation .
	 * Applicable for
	 */

	public static void BO_TC16_PendingConsultationV001() throws Exception {
		try {
			Properties prop = Constants.loadProperties();
			Thread.sleep(1000);
			driver.findElement(By.xpath(prop.getProperty("callentry_link"))).click();
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
			Thread.sleep(2000);
			driver.findElement(By.xpath(prop.getProperty("marincompbut"))).click();;

			Thread.sleep(1000);			
			driver.findElement(By.xpath(prop.getProperty("calldiscon"))).click();
			driver.findElement(By.xpath(prop.getProperty("pendconincomplesubpopup"))).click();			
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC16_PendingConsultationV001 : TC to create and make call incomplete for pending consultation .");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC16_PendingConsultationV001 : TC to create and make call incomplete for pending consultation .");
		}
	}

	

	/**
	 * BO_TC17_PendingConsultationV001 : TC to check call incomplete flow with DB.
	 * Applicable for
	 */

	public static void BO_TC17_PendingConsultationV001() throws Exception {
		try {

			String query3 = "Select status from consultation_request order by created_date desc limit 1;";
			ResultSet rsObject3 = DBConnection.DBConnect(query3);
			while (rsObject3.next()) {
				incomp_stat = rsObject3.getString("status");
			}
			Verify.verify(incomp_stat.equals("7"));
			Constants.TestReports.log(LogStatus.PASS,
					"BO_TC17_PendingConsultationV001 : TC to check call incomplete flow with DB");
		} catch (Exception E) {
			System.out.println(E);
			Constants.TestReports.log(LogStatus.FAIL,
					"BO_TC17_PendingConsultationV001 : TC to check call incomplete flow with DB ");
		}
	}
}

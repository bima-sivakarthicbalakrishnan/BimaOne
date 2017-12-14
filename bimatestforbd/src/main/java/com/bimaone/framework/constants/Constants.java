package com.bimaone.framework.constants;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.Properties;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class Constants {

	public static WebDriver driver = null;

	public static ExtentReports ExecutionReports = new ExtentReports(
			"C:\\Users\\admin\\Desktop\\MIPReports\\ATR_GH\\ExecutionReports.html");

	public static ExtentTest TestReports;

	public static Properties loadProperties() throws Exception {
		File file = new File("src/main/resources/Bimaone.properties");
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		Properties prop = new Properties();
		try {
			prop.load(fileInput);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void getscreenshot() throws Exception {
		int random = (int )(Math.random() * 50 + 1);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive 
		FileUtils.copyFile(scrFile,
				new File("D:\\Users\\admin\\workspace\\bimaonetesting\\test-output\\screenshot\\" + random + ".jpg"));
	}

}

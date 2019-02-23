package week5.day1;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class AdvancedReports {
	static ExtentHtmlReporter html;
	static ExtentReports extent;
	ExtentTest test;

	
	public void startReport() {
		html = new ExtentHtmlReporter("./report/extentreport.html");
		extent = new ExtentReports();
		extent.attachReporter(html);
	}

	public void initializeTest(String name, String desc, String author, String category) {
		test = extent.createTest(name, desc);
		test.assignAuthor(author);
		test.assignCategory(category);
	}

	public void logSteps(String tdesc, String status) {
		if (status.equalsIgnoreCase("pass")) {
			test.pass(tdesc);
		} else if (status.equalsIgnoreCase("fail")) {
			test.fail(tdesc);
		}
	}

	public void endReport() {
		extent.flush();
	}
}

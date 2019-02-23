package week5.day1;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BasicReports {
	ExtentHtmlReporter html;
	ExtentReports extent;
	ExtentTest test;
	//@Test
	public void runReport(){
		html= new ExtentHtmlReporter("./report/extentreport.html");
		extent= new ExtentReports();
		extent.attachReporter(html);
		test = extent.createTest("TC001_Login");
		test.assignAuthor("Pooja");
		test.assignCategory("Sanity");
		try {
			test.pass("TestCase Passed", MediaEntityBuilder.createScreenCaptureFromPath("./../snaps/img1.png").build());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extent.flush();
	}

}

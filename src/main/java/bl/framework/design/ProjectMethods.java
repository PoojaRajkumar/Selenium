package bl.framework.design;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import bl.framework.api.SeleniumBase;
import week6.day2.ReadExcel1;

public class ProjectMethods extends SeleniumBase{

	public String testcaseName, testDec, author, category,dataSheetName;
	@BeforeSuite
	public void beforeSuite() {
		startReport();
	}
	@AfterSuite
	public void afterSuite() {
		endReport();
	}
	@BeforeClass
	public void beforeClass() {
		initializeTest(testcaseName, testDec, author, category);
	}
	@BeforeMethod
	public void login() {
		startApp("chrome", "http://leaftaps.com/opentaps");
		WebElement eleUsername = locateElement("id", "username");
		clearAndType(eleUsername, "DemoSalesManager"); 
		WebElement elePassword = locateElement("id", "password");
		clearAndType(elePassword, "crmsfa"); 
		WebElement eleLogin = locateElement("class", "decorativeSubmit");
		click(eleLogin);
		click(locateElement("xpath","//a[contains(text(),'CRM/SFA')]"));
	}
	
	@AfterMethod
	public void closeApp() {
		close();
	}
	@DataProvider(name="fetchdata")
	public Object[][] getdata1() {
		return ReadExcel1.readData(dataSheetName);
	}
}


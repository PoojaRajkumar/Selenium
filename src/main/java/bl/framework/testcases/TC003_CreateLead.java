package bl.framework.testcases;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import bl.framework.design.ProjectMethods;

public class TC003_CreateLead extends ProjectMethods{
	
	@BeforeTest
	public void setData() {
		testcaseName = "TC001_CreateLead";
		testDec = "Create a new Lead in leaftaps";
		author = "Pooja";
		category = "Smoke";
		dataSheetName="New Microsoft Excel Worksheet";
	} 
	//Attributes for @Test
	//@Test(invocationCount=2, invocationTimeOut=30000)
	@Test(dataProvider="getdata1")
	public void createLead(String cname, String fname,String lname) {
		click(locateElement("xpath", "//a[contains(text(),'Leads')]"));
		click(locateElement("xpath", "//a[contains(text(),'Create Lead')]"));
		clearAndType(locateElement("id", "createLeadForm_companyName"), cname);
		clearAndType(locateElement("id", "createLeadForm_firstName"), fname);
		clearAndType(locateElement("id", "createLeadForm_lastName"), lname);
		click(locateElement("name", "submitButton")); 
	}
	
}

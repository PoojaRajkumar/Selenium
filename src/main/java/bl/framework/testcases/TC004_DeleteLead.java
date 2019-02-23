package bl.framework.testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bl.framework.design.ProjectMethods;

public class TC004_DeleteLead extends ProjectMethods{
	
		
		@Test
		public void deleteLead() throws InterruptedException {
			click(locateElement("link", "CRM/SFA"));
			click(locateElement("link", "Leads"));
			click(locateElement("link", "Find Leads"));
			click(locateElement("xpath","//span[text()='Phone']"));
			clearAndType(locateElement("name", "phoneNumber"), "99"); 
		    click(locateElement("xpath","//button[text()='Find Leads']"));
		    Thread.sleep(1000);
		    String text = locateElement("xpath", "//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a").getText();
		    click(locateElement("xpath", "//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a"));
		    click(locateElement("link", "Delete"));
		    click(locateElement("link", "Find Leads"));
		    clearAndType(locateElement("xpath", "//input[@name='id']"), text);
		    click(locateElement("xpath","//button[text()='Find Leads']"));
		    verifyExactText(locateElement("class","x-paging-info"), "No records to display");
		}
	
  
}

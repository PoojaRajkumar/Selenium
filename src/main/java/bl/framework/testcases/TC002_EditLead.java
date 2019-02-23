package bl.framework.testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bl.framework.design.ProjectMethods;

public class TC002_EditLead extends ProjectMethods{
	@Test(dependsOnMethods= {"bl.framework.testcases.TC003_CreateLead.createLead()"})
	public void editLead() {
		
		click(locateElement("class", "subMenuButton"));
		clearAndType(locateElement("id", "createLeadForm_companyName"), "CG1");
		clearAndType(locateElement("id", "createLeadForm_firstName"), "Pooja1");
		clearAndType(locateElement("id", "createLeadForm_lastName"), "Raj1");
		click(locateElement("class", "smallSubmit"));

	}
	
  
}

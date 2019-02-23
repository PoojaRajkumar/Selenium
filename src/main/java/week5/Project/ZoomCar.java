package week5.Project;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bl.framework.api.SeleniumBase;
import bl.framework.design.ProjectMethods;

public class ZoomCar extends ProjectMethods {
	@BeforeTest
	public void setData() {
		testcaseName = "ZoomCar";
		testDec = "Start your journey";
		author = "pooja";
		category = "smoke";
	}

	@SuppressWarnings("unchecked")
	@Test
	public void zoomCar() throws InterruptedException {

		startApp("chrome", "https://www.zoomcar.com/chennai/");
		WebElement locateElement = locateElement("xpath", "//a[text()='Start your wonderful journey']");
		click(locateElement);
		click(locateElement("xpath"," //div[contains(text(),'Thuraipakkam')]"));
		click(locateElement("class", "proceed"));
		
//		WebElement dte = locateElement("xpath", "//div[text()=11]");
//		click(dte);
//		
		
		Date date= new Date();
		DateFormat sdf= new SimpleDateFormat("dd");
		String today= sdf.format(date);
		int tom= Integer.parseInt(today)+1;
		
		WebElement dte = locateElement("xpath", "//div[contains(text(),'" + tom+ "')]");
		click(dte);
		String td= dte.getText();
		click(locateElement("xpath", "//button[text()='Next']"));
		
		//pssing variable to xpath
		WebElement dte1 = locateElement("xpath", "//div[contains(text(),'" + tom+ "')]");
		String tomd= dte1.getText();
		if(td.equalsIgnoreCase(tomd)){
			System.out.println("date matched");
		}
		else {
			System.out.println("date not matched");
		}
		
		click(locateElement("xpath", "//button[text()='Done']"));
		Thread.sleep(3000);

//		List<WebElement> count = locateElements("class", "car-listing");
//		System.out.println(count.size());
////		WebElement webElement = count.get(0);
////		locateElement("xpath", "//div[@class='price']");
////		System.out.println(webElement);
//		List<WebElement> price= locateElements("xpath", "//div[@class='price']");
//		List<Integer> pri= new ArrayList<>();
//		for (WebElement ele : price) {
//			int pr= Integer.parseInt(ele.getText().replaceAll("\\D", ""));
//			pri.add(pr);
//		}
//		
//		Integer max= Collections.max(pri);
//		System.out.println(max);
		List<WebElement> list=locateElements("class","car-listing");
		System.out.println(list.size());
		
		click(locateElement("xpath","//div[text()=' Price: High to Low ']"));
		
		List<WebElement> details = locateElements("xpath","//div[@class='details']");
		
		WebElement locateElement2 = locateElement("xpath","//div/h3");
		System.out.println(getElementText(locateElement2));
		
		
		List<WebElement> price1 = locateElements("xpath","//div[@class='price']");
		List<Integer> pri= new ArrayList<>();
		for (WebElement ele : price1) {
			int pri1= Integer.parseInt(ele.getText().replaceAll("\\D", ""));
			pri.add(pri1);
		}
		Integer max = Collections.max(pri);
		System.out.println(max);
		
		
		
	
		
		

}
}

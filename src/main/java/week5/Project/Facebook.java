package week5.Project;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import bl.framework.design.ProjectMethods;

public class Facebook extends ProjectMethods {
	@BeforeTest
	public void setData() {
		testcaseName = "Facebook";
		testDec = "Like a page";
		author = "pooja";
		category = "smoke";
	}

	@Test
	public void facebook() throws InterruptedException {
		ChromeOptions op = new ChromeOptions();
		op.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver(op);

		driver.get( "https://www.facebook.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);
		clearAndType(locateElement("id", "email"), "poojar351@gmail.com");
		clearAndType(locateElement("id", "pass"), "bookofpslams");
		click(locateElement("xpath", "//input[@value='Log In']"));


	}

}

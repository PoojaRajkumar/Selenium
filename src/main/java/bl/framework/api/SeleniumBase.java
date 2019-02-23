package bl.framework.api;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.commons.math3.analysis.solvers.IllinoisSolver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import bl.framework.base.Browser;
import bl.framework.base.Element;
import week5.day1.AdvancedReports;

public class SeleniumBase extends AdvancedReports implements Browser, Element {

	public RemoteWebDriver driver = null;
	public int i = 1;

	@Override
	public void startApp(String url) {
		try {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(3000, TimeUnit.SECONDS);

			driver.get(url);
			logSteps("Application started sucessfully", "pass");
		} catch (Exception e) {
			System.err.println("Error in getting url");
			logSteps("Application could not be started", "fail");
		} finally {
			takeSnap();
		}

	}

	@Override
	public void startApp(String browser, String url) {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
				driver = new FirefoxDriver();
			}
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			logSteps("the browser " + browser + " launched successfully", "Pass");
		} catch (IllegalStateException e) {
			System.err.println("Eror in opening the browser");
			logSteps("the browser " + browser + " not launched ", "fail");
		} finally {
			takeSnap();
		}

	}

	@Override
	public WebElement locateElement(String locatorType, String value) {
		try {
			switch (locatorType) {
			case "id":
				return driver.findElementById(value);
			case "name":
				return driver.findElementByName(value);
			case "class":
				return driver.findElementByClassName(value);
			case "xpath":
				return driver.findElementByXPath(value);
			case "LinkText":
				return driver.findElementByLinkText(value);
			case "tagname":
				return driver.findElementByTagName(value);
			default:

				logSteps("Element located successfully", "pass");
				break;
			}
		} catch (NoSuchElementException e) {
			System.err.println("element not located");
			logSteps("Element not located", "fail");
		}
		return null;
	}

	@Override
	public WebElement locateElement(String value) {
		try {
			driver.findElementById(value);
			logSteps("Element located successfully", "pass");
		} catch (NoSuchElementException e) {
			System.err.println("element not located");
			logSteps("Element not located", "fail");
		}
		return null;
	}

	@Override
	public List<WebElement> locateElements(String type, String value) {
		try {
			switch (type) {
			case "id":
				return driver.findElementsById(value);
			case "name":
				return driver.findElementsByName(value);
			case "class":
				return driver.findElementsByClassName(value);
			case "xpath":
				return driver.findElementsByXPath(value);
			case "LinkText":
				return driver.findElementsByLinkText(value);
			case "tagname":
				return driver.findElementsByTagName(value);
			}
		} catch (NoSuchElementException e) {
			System.err.println("element not located");
			logSteps("Element not located", "fail");
		}
		return null;
	}

	@Override
	public void switchToAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			logSteps("Focus switched to alert", "pass");
		} catch (NoAlertPresentException e) {
			System.err.println("Alert not present");
			logSteps("Alert not present", "fail");
		}

	}

	@Override
	public void acceptAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
			logSteps("Alert is accepted", "pass");
		} catch (NoAlertPresentException e) {
			System.err.println("Alert not present");
			logSteps("Alert not present", "fail");
		}

	}

	@Override
	public void dismissAlert() {
		try {
			Alert alert = driver.switchTo().alert();
			alert.dismiss();
			logSteps("Alert is cancelled", "pass");
		} catch (NoAlertPresentException e) {
			System.err.println("Alert not present");
			logSteps("Alert not present", "fail");
		}

	}

	@Override
	public String getAlertText() {
		String text = " ";
		try {
			Alert alert = driver.switchTo().alert();
			text = alert.getText();
			logSteps("Alert text retrived successfully", "pass");
		} catch (NoAlertPresentException e) {
			System.err.println("Alert not present");
			logSteps("Alert not present", "fail");
		}
		return text;
	}

	@Override
	public void typeAlert(String data) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.sendKeys(data);
			logSteps("Value passed to the alert", "pass");
		} catch (NoAlertPresentException e) {
			System.err.println("Alert not present");
			logSteps("Alert not present", "fail");
		}

	}

	@Override
	public void switchToWindow(int index) {
		try {
			Set<String> allwindows = driver.getWindowHandles();
			List<String> ls = new ArrayList<>();
			ls.addAll(allwindows);
			driver.switchTo().window(ls.get(index));
			logSteps("Switched to" + index + " window", "pass");
		} catch (NoSuchWindowException e) {
			System.err.println("Window not present");
			logSteps("Window not present", "fail");
		}

	}

	@Override
	public void switchToWindow(String title) {
		try {
			Set<String> allWindows = driver.getWindowHandles();
			for (String eachWindow : allWindows) {
				driver.switchTo().window(eachWindow);
				if (driver.getTitle().equals(title)) {
					break;
				}
			}
			logSteps("The Window With Title: " + title + "is switched ", "pass");
		} catch (NoSuchWindowException e) {
			System.err.println("The Window With Title: " + title + " not found");
			logSteps("The Window With Title: " + title + "could not be switched ", "fail");
		} finally {
			takeSnap();
		}

	}

	@Override
	public void switchToFrame(int index) {
		try {
			driver.switchTo().frame(index);
			logSteps("The frame with the index: " + index + "switched successfully", "pass");
		} catch (NoSuchFrameException e) {
			System.err.println("The frame With index: " + index + " not found");
			logSteps("The frame With index: " + index + "could not be switched ", "fail");
		} finally {
			takeSnap();
		}

	}

	@Override
	public void switchToFrame(WebElement ele) {
		try {
			driver.switchTo().frame(ele);
			logSteps("The frame with the index: " + ele + "switched successfully", "pass");
		} catch (NoSuchFrameException e) {
			System.err.println("The frame With index: " + ele + " not found");
			logSteps("The frame With index: " + ele + "could not be switched ", "fail");
		} catch (StaleElementReferenceException e) {
			System.err.println("The frame With index: " + ele + " not found");
			logSteps("The frame With index: " + ele + "could not be switched ", "fail");
		} finally {
			takeSnap();
		}

	}

	@Override
	public void switchToFrame(String idOrName) {
		try {
			driver.switchTo().frame(idOrName);
			logSteps("The frame with the idOrName: " + idOrName + "switched successfully", "pass");
		} catch (NoSuchFrameException e) {
			System.err.println("The frame With idOrName: " + idOrName + " not found");
			logSteps("The frame With idOrName: " + idOrName + "could not be switched ", "fail");
		} finally {
			takeSnap();
		}

	}

	@Override
	public void defaultContent() {
		try {
			driver.switchTo().defaultContent();
			logSteps("Switched to first frame on the page", "pass");
		} catch (Exception e) {
			logSteps("Could not be Switched to first frame on the page", "fail");
		}
	}

	@Override
	public boolean verifyUrl(String url) {
		if (driver.getCurrentUrl().equalsIgnoreCase(url)) {
			logSteps("URL matches", "pass");
			return true;
		} else {
			logSteps("URL mismatches", "fail");
			return false;
		}

	}

	@Override
	public boolean verifyTitle(String title) {
		if (driver.getTitle().equalsIgnoreCase(title)) {
			logSteps("Title matches", "pass");
			return true;
		} else {
			logSteps("Title mismatches", "fail");
			return false;
		}
	}

	@Override
	public void takeSnap() {
		File src = driver.getScreenshotAs(OutputType.FILE);
		File des = new File("./snaps/img" + i + ".png");
		try {
			FileUtils.copyFile(src, des);
			logSteps("Snaps taken successfully", "pass");
		} catch (IOException e) {
			logSteps("Snaps could not be taken", "fail");
		}
		i++;
	}

	@Override
	public void close() {
		try {
			driver.close();
			logSteps("Browser closed sucessfully", "pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logSteps("Browser couldnot be closed", "fail");
		}

	}

	@Override
	public void quit() {
		try {
			driver.quit();
			logSteps("All Browsers closed sucessfully", "pass");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logSteps("Browsers couldnot be closed", "fail");
		}

	}

	@Override
	public void click(WebElement ele) throws StaleElementReferenceException {
		try {
			ele.click();
			System.out.println("The element " + ele + " clicked successfully");
			logSteps("Element clicked successfully", "pass");
		} catch (ElementNotInteractableException e) {
			System.err.println("The element \"+ele+\" not clicked");
			logSteps("Element could not be clicked", "fail");
		} catch (WebDriverException e) {
			System.err.println("The element \"+ele+\" not clicked");
			logSteps("Element could not be clicked", "fail");
		} finally {
			takeSnap();
		}

	}

	@Override
	public void append(WebElement ele, String data) {
		try {
			ele.sendKeys(data);
			logSteps("Appended" + data + "to the element" + ele, "pass");
		} catch (ElementNotInteractableException e) {
			System.err.println("Could not be communicated with th element");
			logSteps("Append Failed", "fail");
		} catch (IllegalArgumentException e) {
			System.err.println("Could not be communicated with th element");
			logSteps("Append Failed", "fail");
		} finally {
			takeSnap();
		}

	}

	@Override
	public void clear(WebElement ele) {
		try {
			ele.clear();
			logSteps("The field is cleared Successfully", "pass");
		} catch (ElementNotInteractableException e) {
			logSteps("The field is not Interactable", "fail");
			throw new RuntimeException();
		} catch (IllegalArgumentException e) {
			System.err.println("Could not be communicated with th element");
			logSteps("Append Failed", "fail");
		} finally {
			takeSnap();
		}
	}

	@Override
	public void clearAndType(WebElement ele, String data) {
		try {
			ele.clear();
			ele.sendKeys(data);
			logSteps("The data " + data + " entered successfully", "pass");
		} catch (ElementNotInteractableException e) {
			logSteps("The field is not Interactable", "fail");
			throw new RuntimeException();
		} catch (IllegalArgumentException e) {
			System.err.println("Could not be communicated with the element");
			logSteps("Append Failed", "fail");
		} finally {
			takeSnap();
		}
	}

	@Override
	public String getElementText(WebElement ele) {
		String text = "";
		try {
			text = ele.getText();
			logSteps("Visible text of the element fetched", "pass");
		} catch (Exception e) {
			logSteps("Visible text of the element could not be fetched", "fail");
		} finally {
			takeSnap();
		}
		return text;

	}

	@Override
	public String getBackgroundColor(WebElement ele) {
		String cssValue = null;
		try {
			cssValue = ele.getCssValue("color");
			logSteps("Color of the element fetched", "pass");
		} catch (Exception e) {
			logSteps("Color of the element could not be fetched", "fail");
		} finally {
			takeSnap();
		}
		return cssValue;
	}

	@Override
	public String getTypedText(WebElement ele) {

		String attributeValue = null;
		try {
			attributeValue = ele.getCssValue("color");
			logSteps("Text of the element fetched", "pass");
		} catch (Exception e) {
			logSteps("Text of the element could not be fetched", "fail");
		} finally {
			takeSnap();
		}
		return attributeValue;
	}

	@Override
	public void selectDropDownUsingText(WebElement ele, String value) {
		try {
			Select sel = new Select(ele);
			sel.selectByVisibleText(value);
			logSteps("Selected the value by text", "pass");
		} catch (Exception e) {
			logSteps("Could not be selected the value by text", "fail");
		} finally {
			takeSnap();
	}

	}

	@Override
	public void selectDropDownUsingIndex(WebElement ele, int index) {
		Select sel = new Select(ele);
		sel.selectByIndex(index);
	}

	@Override
	public void selectDropDownUsingValue(WebElement ele, String value) {
		Select sel = new Select(ele);
		sel.selectByValue(value);
	}

	@Override
	public boolean verifyExactText(WebElement ele, String expectedText) {
		if (ele.getText().equals(expectedText)) {
			System.err.println("text matched");
			return true;
		} else {
			System.err.println("text not matched");
		}
		return false;
	}

	@Override
	public boolean verifyPartialText(WebElement ele, String expectedText) {
		try {
			if (ele.getText().contains(expectedText)) {
				System.out.println("The expected text contains the actual " + expectedText);
				return true;
			} else {
				System.out.println("The expected text doesn't contain the actual " + expectedText);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Text");
		}

		return false;
	}

	@Override
	public boolean verifyExactAttribute(WebElement ele, String attribute, String value) {
		try {
			if (ele.getAttribute(attribute).equals(value)) {
				System.out.println("The expected attribute :" + attribute + " value contains the actual " + value);
				return true;
			} else {
				System.out.println(
						"The expected attribute :" + attribute + " value does not contains the actual " + value);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}
		return false;
	}

	@Override
	public void verifyPartialAttribute(WebElement ele, String attribute, String value) {
		try {
			if (ele.getAttribute(attribute).contains(value)) {
				System.out.println("The expected attribute :" + attribute + " value contains the actual " + value);
			} else {
				System.out.println(
						"The expected attribute :" + attribute + " value does not contains the actual " + value);
			}
		} catch (WebDriverException e) {
			System.out.println("Unknown exception occured while verifying the Attribute Text");
		}

	}

	@Override
	public boolean verifyDisplayed(WebElement ele) {
		try {
			if (ele.isDisplayed()) {
				System.out.println("The element " + ele + " is visible");
				return true;
			} else {
				System.out.println("The element " + ele + " is not visible");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : " + e.getMessage());
		}
		return false;

	}

	@Override
	public boolean verifyDisappeared(WebElement ele) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean verifyEnabled(WebElement ele) {
		try {
			if (ele.isEnabled()) {
				System.out.println("The element " + ele + " is Enabled");
				return true;
			} else {
				System.out.println("The element " + ele + " is not Enabled");
			}
		} catch (WebDriverException e) {
			System.out.println("WebDriverException : " + e.getMessage());
		}
		return false;
	}

	@Override
	public boolean verifySelected(WebElement ele) {
		{
			try {
				if (ele.isSelected()) {
					System.out.println("The element " + ele + " is selected");
					return true;
				} else {
					System.out.println("The element " + ele + " is not selected");
				}
			} catch (WebDriverException e) {
				System.out.println("WebDriverException : " + e.getMessage());
			}
			return false;

		}
	}

}

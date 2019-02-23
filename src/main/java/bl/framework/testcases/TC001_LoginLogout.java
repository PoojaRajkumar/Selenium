package bl.framework.testcases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import bl.framework.api.SeleniumBase;

public class TC001_LoginLogout extends SeleniumBase{
	@Parameters({"url","username","password"})
	@BeforeMethod
	public void login(String url,String uname,String pwd) {
		startApp("chrome", url);
		WebElement eleUsername = locateElement("id", "username");
		clearAndType(eleUsername, uname); 
		WebElement elePassword = locateElement("id", "password");
		clearAndType(elePassword, pwd); 
		WebElement eleLogin = locateElement("class", "decorativeSubmit");
		click(eleLogin); 
		WebElement eleLogout = locateElement("class", "decorativeSubmit");
		click(eleLogout);
	}
}


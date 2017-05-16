package functions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class adminLogin {
	
	WebDriver driver;
	By username = By.id("username");
	By password = By.id("password");
	By btn = By.name("loginForm");
	
	public adminLogin(WebDriver driver) {
		this.driver = driver;
	}
	
	public void setUsername(String strUsername){
		driver.findElement(username).sendKeys(strUsername);
	}
	
	public void setPassword(String strPassword) {
		driver.findElement(password).sendKeys(strPassword);
	}
	
	public void submit() {
		driver.findElement(btn).click();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}
	
	public void loginToAdmin(String strUsername, String strPassword) {
		this.setUsername(strUsername);
		this.setPassword(strPassword);
		this.submit();
	}

}
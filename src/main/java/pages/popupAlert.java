package pages;



import com.github.javafaker.Faker;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public class popupAlert {
	
	WebDriver driver;
	
	public popupAlert(WebDriver driver) {
		this.driver = driver;	
	}
	
    public void geolocation() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String loginError = "Invalid username or password";
		
        Random r = new Random();
		
		Faker faker = new Faker();
		
		driver.findElement(By.xpath("//*[@id='tab-basic']/div[16]/div[1]/div[1]/a")).click();
		//wait.until(ExpectedConditions.alertIsPresent());
		//driver.switchTo().alert().accept();
		while(wait.until(ExpectedConditions.alertIsPresent())!=null){
			driver.switchTo().alert().accept();
			//driver.switchTo().alert().accept();
			//driver.switchTo().defaultContent();
			String geoLocation = faker.address().city();
			driver.findElement(By.xpath("//*[@id='tab-basic']/div[16]/div[1]/div[1]/input")).clear();
			driver.findElement(By.xpath("//*[@id='tab-basic']/div[16]/div[1]/div[1]/input")).sendKeys(geoLocation);
			driver.findElement(By.xpath("//*[@id='tab-basic']/div[16]/div[1]/div[1]/a")).click();
		}
	}
}
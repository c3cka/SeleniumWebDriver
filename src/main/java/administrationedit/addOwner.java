package administrationedit;



import com.github.javafaker.Faker;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import functions.adminLogin;
import java.util.concurrent.TimeUnit;

public class addOwner {
	
	static WebDriver driver;
	//static String title;
	
    public static void main(String[] args){
		
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		Random r = new Random();
		
		Faker faker = new Faker();
				
        driver.get("https://goldfish.dev.hexis.hr/demo/en/administration/accommodation/ownersAdd");
        
		adminLogin login = new adminLogin(driver);
		login.loginToAdmin("demo", "demo");
		
		// Input firstname
		String firstname = faker.name().firstName();
		driver.findElement(By.id("firstName")).sendKeys(firstname);
		
		// Input lastname
		String lastname = faker.name().lastName();
		driver.findElement(By.id("lastName")).sendKeys(lastname);
		
		String username = firstname.toLowerCase() + "." + lastname.toLowerCase();
		String password = faker.internet().password(20, 40, true, true);

		
		// Birth date - datepicker
		driver.findElement(By.xpath("//input[@name='birthDate']")).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='ui-datepicker-year']")));
		Select year = new Select(driver.findElement(By.xpath("//*[@class='ui-datepicker-year']")));
		int yearInt = r.nextInt(year.getOptions().size());
		year.selectByIndex(yearInt);
		Select month = new Select(driver.findElement(By.xpath("//*[@class='ui-datepicker-month']")));
		int monthInt = r.nextInt(month.getOptions().size());
		month.selectByIndex(monthInt);
		int weeks = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr")).size();
		int weekNum = r.nextInt(weeks)+1;
		int day = r.nextInt(7)+1;
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr["+weekNum+"]/td["+day+"]")));
		driver.findElement(By.xpath("//table[@class='ui-datepicker-calendar']/tbody/tr["+weekNum+"]/td["+day+"]")).click();
		
		// Input email
		String email = faker.internet().emailAddress();
		driver.findElement(By.id("contactEmail")).sendKeys(email);
		
		// Input address
		String address = faker.address().fullAddress();
		driver.findElement(By.id("contactAddress")).sendKeys(address);
		
		// Input phone
		String phone = faker.phoneNumber().phoneNumber().replaceAll("\\D", "");
		driver.findElement(By.id("contactPhone")).sendKeys(phone);
		
		// Input mobile
		String mobile = faker.phoneNumber().cellPhone().replaceAll("\\D", "");
		driver.findElement(By.id("contactMobile")).sendKeys(mobile);
		
		// Check partner area		//*[@id="page-wrapper"]/div[2]/form/div[2]/div/div/div/div[2]/div[8]/div[1]/div/ins
		driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/form/div[2]/div/div/div/div[2]/div[8]/div[1]/div/ins")).click();
		if(driver.findElement(By.id("isPartner")).isSelected()){
			driver.findElement(By.id("username")).sendKeys(username);
			driver.findElement(By.id("password")).sendKeys(password);
			driver.findElement(By.id("password_confirm")).sendKeys(password);
		}
		
		// SAVE		//*[@id="page-wrapper"]/div[2]/div[1]
		driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/form/div[2]/div/div/div/div[2]/div[11]/div/button")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='page-wrapper']/div[2]/div[1]")));
		if(driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/div[1]")).isDisplayed()){
			System.out.println("Owner created");
			System.out.println("First name : " + firstname + "\n" + "Last name : " + lastname + "\n" + "Email : " + email + "\n" + "Username : " + username + "\n" + "Password : " + password);
		}
	}
}
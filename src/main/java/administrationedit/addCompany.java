/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrationedit;

import com.github.javafaker.Faker;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import functions.adminLogin;
import java.util.concurrent.TimeUnit;

public class addCompany {
	
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

        driver.get("https://goldfish.dev.hexis.hr/demo/en/administration/user/updateCompanySettings");
		
		adminLogin login = new adminLogin(driver);
		login.loginToAdmin("demo", "demo");
		
		// Input name
		String nameComp = faker.company().name();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys(nameComp);
		
		// Input address
		String addressComp = faker.address().fullAddress();
		driver.findElement(By.id("address")).clear();
		driver.findElement(By.id("address")).sendKeys(addressComp);
		
		// Input city
		String cityComp = faker.address().city();
		driver.findElement(By.id("city")).clear();
		driver.findElement(By.id("city")).sendKeys(cityComp);

		// Input postal code
		String postalCodeComp = faker.address().zipCode();
		driver.findElement(By.id("postalCode")).clear();
		driver.findElement(By.id("postalCode")).sendKeys(postalCodeComp);
		
		// Country
		Select countryComp = new Select(driver.findElement(By.id("country")));
		int coutryCompInt = r.nextInt(countryComp.getOptions().size())+1;
		countryComp.selectByIndex(coutryCompInt);
		
		// Email
		String emailComp = faker.internet().emailAddress();
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(emailComp);
		
		// Phone
		String phoneComp = faker.phoneNumber().phoneNumber().replaceAll("\\D", "");
		driver.findElement(By.id("phone")).clear();
		driver.findElement(By.id("phone")).sendKeys(phoneComp);
		
		// Mobile
		String mobileComp = faker.phoneNumber().cellPhone().replaceAll("\\D", "");
		driver.findElement(By.id("mobilePhone")).clear();
		driver.findElement(By.id("mobilePhone")).sendKeys(mobileComp);
		
		// Fax
		String faxComp = faker.phoneNumber().phoneNumber().replaceAll("\\D", "");
		driver.findElement(By.id("fax")).clear();
		driver.findElement(By.id("fax")).sendKeys(faxComp);
		
		// OIB
		String oibComp = faker.idNumber().validSvSeSsn();
		driver.findElement(By.id("oib")).clear();
		driver.findElement(By.id("oib")).sendKeys(oibComp);
		
		// VatID
		String vatComp = faker.idNumber().validSvSeSsn();
		driver.findElement(By.id("vatId")).clear();
		driver.findElement(By.id("vatId")).sendKeys(vatComp);
		
		// Id code
		String idCodeComp = faker.idNumber().valid();
		driver.findElement(By.id("idCode")).clear();
		driver.findElement(By.id("idCode")).sendKeys(idCodeComp);
		
		// MBS
		String mbsComp = faker.number().digits(15);
		driver.findElement(By.id("mbs")).clear();
		driver.findElement(By.id("mbs")).sendKeys(mbsComp);
		
		// ID number
		String idNumComp = faker.number().digits(15);
		driver.findElement(By.id("identificationNumber")).clear();
		driver.findElement(By.id("identificationNumber")).sendKeys(idNumComp);
		
		// IBAN
		String ibanComp = faker.finance().iban();
		driver.findElement(By.id("iban")).clear();
		driver.findElement(By.id("iban")).sendKeys(ibanComp);
		
		// Swift 
		String swiftComp = faker.finance().bic();
		driver.findElement(By.id("swift")).clear();
		driver.findElement(By.id("swift")).sendKeys(swiftComp);
		
		//Bank name
		String bankNameComp = faker.name().name();
		driver.findElement(By.id("bankName")).clear();
		driver.findElement(By.id("bankName")).sendKeys(bankNameComp);
		
		// Bank address
		String bankAddressComp = faker.address().fullAddress();
		driver.findElement(By.id("bankAddress")).clear();
		driver.findElement(By.id("bankAddress")).sendKeys(bankAddressComp);
		
		// Bank city
		String bankCityComp = faker.address().city();
		driver.findElement(By.id("bankCity")).clear();
		driver.findElement(By.id("bankCity")).sendKeys(bankCityComp);
		
		// Bank postal code
		String bankPostalCodeComp = faker.address().zipCode();
		driver.findElement(By.id("bankPostalCode")).clear();
		driver.findElement(By.id("bankPostalCode")).sendKeys(bankPostalCodeComp);
		
		// Bank country
		Select bankCountryComp = new Select(driver.findElement(By.id("bankCountry")));
		int bankCoutryCompInt = r.nextInt(bankCountryComp.getOptions().size())+1;
		bankCountryComp.selectByIndex(bankCoutryCompInt);
		
		// Social 
		String socialTW = faker.internet().url();
		String socialFB = faker.internet().url();
		driver.findElement(By.id("twitterUsername")).clear();
		driver.findElement(By.id("facebookID")).clear();
		driver.findElement(By.id("twitterUsername")).sendKeys(socialTW);
		driver.findElement(By.id("facebookID")).sendKeys(socialFB);
		
		// SAVE
		driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/form/div[2]/div/div/div/div[2]/div[26]/div/button")).click();
		
	}
}
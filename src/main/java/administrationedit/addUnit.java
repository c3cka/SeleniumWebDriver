/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrationedit;

import com.github.javafaker.Faker;
import functions.adminLogin;
import functions.imageUpload;
import java.awt.AWTException;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Igor
 */
public class addUnit {
	
	static WebDriver driver;
	//static String title;
	
    public static void main(String[] args) throws AWTException, InterruptedException{
		
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
	
		String[] tags = {"featured", "homepage", "promo", "special", "recommended", "luxury"};
		
		Random r = new Random();
		
		Faker faker = new Faker();
				
        driver.get("https://goldfish.dev.hexis.hr/demo/en/administration/accommodation/administrate");
        
		adminLogin login = new adminLogin(driver);
		login.loginToAdmin("demo", "demo");
		
		int acc = driver.findElements(By.xpath("//*[@id='page-wrapper']/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr")).size();
		//int selAcc = r.nextInt(acc)+1;
		for(int i=1; i<acc; i++){
			driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr["+i+"]/td[9]/a[2]")).click();
			if(driver.findElements(By.xpath("//*[@id='page-wrapper']/div[2]/div[2]/div/div/div/div[2]/table/tbody/tr")).size() <= 3){
				driver.findElement(By.linkText("Add new one")).click();
				
		        //Enter titles and descriptions in different languages
				int titleInt = r.nextInt(1000000);
				//title = driver.findElement(By.xpath("//*[@id='title__en']")).sendKeys("AccommodationEN(" + titleInt + ")");
				driver.findElement(By.xpath("//*[@id='title__en']")).sendKeys("AccommodationEN(" + titleInt + ")");
				//Switch to HR
				driver.findElement(By.linkText("Hrvatski")).click();
				driver.findElement(By.xpath("//*[@id='title__hr']")).sendKeys("AccommodationHR(" + titleInt + ")");
				//Switch to DE
				driver.findElement(By.linkText("Deutsch")).click();
				driver.findElement(By.xpath("//*[@id='title__de']")).sendKeys("AccommodationDE(" + titleInt + ")");
				//Switch to IT
				driver.findElement(By.linkText("Italiano")).click();
				driver.findElement(By.xpath("//*[@id='title__it']")).sendKeys("AccommodationIT(" + titleInt + ")");
				
				driver.findElement(By.xpath("//*[@id='tab-basic']/div[9]/div[1]/div/ins")).click();
				
				Select unitType = new Select(driver.findElement(By.xpath("//*[@id='type']")));
				int rn = r.nextInt(unitType.getOptions().size());
				unitType.selectByIndex(rn);
				
				Select unitSubType = new Select(driver.findElement(By.xpath("//*[@id='subType']")));
				int rn1 = r.nextInt(unitSubType.getOptions().size());
				unitSubType.selectByIndex(rn1);
				
				int numRoomInt = r.nextInt(12)+1;
				String numRoom = Integer.toString(numRoomInt);
				driver.findElement(By.id("numberOfRooms")).sendKeys(numRoom);

				int numBathInt = r.nextInt(4)+1;
				String numBath = Integer.toString(numBathInt);
				driver.findElement(By.id("numberOfBathrooms")).sendKeys(numBath);

				int numBedInt = r.nextInt(10)+1;
				String numBed = Integer.toString(numBedInt);
				driver.findElement(By.id("numberOfBedrooms")).sendKeys(numBed);
				
				int surface = r.nextInt(100)+50;
				String numSurface = Integer.toString(surface);
				driver.findElement(By.id("surfaceArea")).sendKeys(numSurface);
				
				Select rating = new Select(driver.findElement(By.xpath("//*[@id='rating']")));
				int rat = r.nextInt(rating.getOptions().size());
				rating.selectByIndex(rat);

				int commision = r.nextInt(15)+5;
				String numCommision = Integer.toString(commision);
				driver.findElement(By.id("commision")).sendKeys(numCommision);
				
				int pets = r.nextInt(2);
				if(pets==0 && driver.findElement(By.id("petsAllowed")).isSelected()){
					driver.findElement(By.xpath("//*[@id='tab-basic']/div[19]/div[1]/div/ins")).click();
				}
				
				driver.findElement(By.xpath("//*[@id='featuredImage.choose']")).click();
				imageUpload.uploadUnit(driver);

				driver.findElement(By.xpath("//*[@id='imageList.choose']")).click();
				imageUpload.uploadUnitList(driver);

				Select notice = new Select(driver.findElement(By.xpath("//*[@id='additionalNotice']")));
				int noticeInt = r.nextInt(notice.getOptions().size());
				notice.selectByIndex(noticeInt);

				Select voucher = new Select(driver.findElement(By.xpath("//*[@id='voucher']")));
				int voucherInt = r.nextInt(voucher.getOptions().size());
				voucher.selectByIndex(voucherInt);
				
				Select arrivalInstructions = new Select(driver.findElement(By.xpath("//*[@id='arrivalInstructions']")));
				int arrivalInstructionsInt = r.nextInt(arrivalInstructions.getOptions().size());
				arrivalInstructions.selectByIndex(arrivalInstructionsInt);
				
				Select pickUpInstructions = new Select(driver.findElement(By.xpath("//*[@id='pickUpInstructions']")));
				int pickUpInstructionsInt = r.nextInt(pickUpInstructions.getOptions().size());
				pickUpInstructions.selectByIndex(pickUpInstructionsInt);
				
				int tag1 = r.nextInt(tags.length);	
				int tag2 = r.nextInt(tags.length);
				driver.findElement(By.xpath("//*[@id='tab-basic']/div[22]/div[1]/ul/li/input")).sendKeys(tags[tag1], Keys.TAB, tags[tag2], Keys.TAB);
				
				driver.findElement(By.linkText("Properties")).click();
				
				

				
			}
			//driver.navigate().back();
		}
		
		
		
	}
}	

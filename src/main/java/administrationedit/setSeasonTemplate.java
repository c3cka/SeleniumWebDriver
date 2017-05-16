/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package administrationedit;

import com.github.javafaker.Faker;
import functions.adminLogin;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Igor
 */
public class setSeasonTemplate {
	
	static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException{
		
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		driver.manage().window().maximize();
        //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		
		Random r = new Random();
		
		Faker faker = new Faker();
				
        driver.get("https://goldfish.dev.hexis.hr/demo/en/administration/accommodation/seasonTemplateAdd");
        
		adminLogin login = new adminLogin(driver);
		login.loginToAdmin("demo", "demo");
		
		// Input name of template
		int season = r.nextInt(10000)+1;
		driver.findElement(By.id("name")).sendKeys("Season["+season+"]");
		
		// Choose year
		Select year = new Select(driver.findElement(By.id("year")));
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		String curYear = String.valueOf(currentYear);
		
		// Now select seasons
		WebElement tableId = driver.findElement(By.xpath("//*[@class='ui-calendar hasDatepicker']"));
		if(year.getFirstSelectedOption().getText().equals(curYear)){
			String id = tableId.getAttribute("id");
			
			for(int i=1; i<12; i++)
			{
				Thread.sleep(1000);
				for(int j=1; j<=7; j++){
					if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr[1]/td["+j+"]/a")).isEmpty()){
						driver.findElement(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr[1]/td["+j+"]/a")).click();
						break;
					}
				}
				i++;
				int weeks = driver.findElements(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr")).size();
				for(int j=7; j>=1; j--){
					if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr["+weeks+"]/td["+j+"]/a")).isEmpty()){
						int season1int = 0;
						driver.findElement(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr["+weeks+"]/td["+j+"]/a")).click();
						Select season1 = new Select(driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/select")));
						season1int = r.nextInt(season1.getOptions().size())+1;
						season1.selectByIndex(season1int);
						driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[4]/div/a[2]")).click();
						break;
					}
					else
					{
						break;
					}
				}
				for(int j=7; j>=1; j--){
					if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr["+(weeks-1)+"]/td["+j+"]/a")).isEmpty()){
						int season1int = 0;
						driver.findElement(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr["+(weeks-1)+"]/td["+j+"]/a")).click();
						Select season1 = new Select(driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/select")));
						season1int = r.nextInt(season1.getOptions().size())+1;
						System.out.println(season1.getOptions().size() + "		" + season1int);
						season1.selectByIndex(season1int);
						driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[4]/div/a[2]")).click();
						break;
					}
				}
			}
			/*
			Thread.sleep(1000);
			// Select period from 1.3.-30.4.
			for(int i=1; i<=7; i++){
				if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div[3]/table/tbody/tr[1]/td["+i+"]/a")).isEmpty()){
					driver.findElement(By.xpath("//*[@id='"+id+"']/div/div[3]/table/tbody/tr[1]/td["+i+"]/a")).click();
					break;
				}
			}
			int weeks2 = driver.findElements(By.xpath("//*[@id='"+id+"']/div/div[4]/table/tbody/tr")).size();
			for(int i=7; i>=1; i--){
				if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div[4]/table/tbody/tr["+weeks2+"]/td["+i+"]/a")).isEmpty()){
					driver.findElement(By.xpath("//*[@id='"+id+"']/div/div[4]/table/tbody/tr["+weeks2+"]/td["+i+"]/a")).click();
				}
				else if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div[4]/table/tbody/tr["+(weeks2-1)+"]/td["+i+"]/a")).isEmpty()){
					driver.findElement(By.xpath("//*[@id='"+id+"']/div/div[4]/table/tbody/tr["+(weeks2-1)+"]/td["+i+"]/a")).click();
					Select season1 = new Select(driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/select")));
					int season1int = r.nextInt(season1.getOptions().size());
					season1.selectByIndex(season1int);
					driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[4]/div/a[2]")).click();
					break;
				}
			}
			
			Thread.sleep(1000);
			// Select period from 1.5.-30.6.
			for(int i=1; i<=7; i++){
				if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div[5]/table/tbody/tr[1]/td["+i+"]/a")).isEmpty()){
					driver.findElement(By.xpath("//*[@id='"+id+"']/div/div[5]/table/tbody/tr[1]/td["+i+"]/a")).click();
					break;
				}
			}
			int weeks3 = driver.findElements(By.xpath("//*[@id='"+id+"']/div/div[6]/table/tbody/tr")).size();
			for(int i=7; i>=1; i--){
				if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div[6]/table/tbody/tr["+weeks3+"]/td["+i+"]/a")).isEmpty()){
					driver.findElement(By.xpath("//*[@id='"+id+"']/div/div[6]/table/tbody/tr["+weeks3+"]/td["+i+"]/a")).click();
				}
				else if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div[6]/table/tbody/tr["+(weeks3-1)+"]/td["+i+"]/a")).isEmpty()){
					driver.findElement(By.xpath("//*[@id='"+id+"']/div/div[6]/table/tbody/tr["+(weeks3-1)+"]/td["+i+"]/a")).click();
					Select season1 = new Select(driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/select")));
					int season1int = r.nextInt(season1.getOptions().size());
					season1.selectByIndex(season1int);
					driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[4]/div/a[2]")).click();
					break;
				}
			}*/
			
		}		
	}
}

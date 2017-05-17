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
		
		for(int y=0; y<2; y++){

			System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
			driver = new ChromeDriver();
			WebDriverWait wait = new WebDriverWait(driver, 3);
			driver.manage().window().maximize();

			Random r = new Random();

			Faker faker = new Faker();

			driver.get("https://goldfish.dev.hexis.hr/demo/en/administration/accommodation/seasonTemplateAdd");

			adminLogin login = new adminLogin(driver);
			login.loginToAdmin("demo", "demo");


			int currentYear = Calendar.getInstance().get(Calendar.YEAR) + y;
			String curYear = String.valueOf(currentYear);

			// Input name of template
			driver.findElement(By.id("name")).sendKeys("Season-"+curYear);

			// Choose year
			Select year = new Select(driver.findElement(By.id("year")));
			year.selectByVisibleText(curYear);

			// Now select seasons
			WebElement tableId = driver.findElement(By.xpath("//*[@class='ui-calendar hasDatepicker']"));
			if(year.getFirstSelectedOption().getText().equals(curYear)){
				String id = tableId.getAttribute("id");

				for(int i=1; i<=12; i++){
					Thread.sleep(1000);
					for(int j=1; j<=7; j++){
						if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr[1]/td["+j+"]/a")).isEmpty()){
							driver.findElement(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr[1]/td["+j+"]/a")).click();
							break;
						}
					}
					i++;
					int weeks = driver.findElements(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr")).size();

					int test = 0;
					for(int j=7; j>=1; j--){
						if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr["+weeks+"]/td["+j+"]/a")).isEmpty()){
							test = 1;
							break;
						}
						else test = 2;
					}
					switch (test){
						case 1:
							for(int j=7; j>=1; j--){
								if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr["+weeks+"]/td["+j+"]/a")).isEmpty()){
									driver.findElement(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr["+weeks+"]/td["+j+"]/a")).click();
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/select")));
									Select season1 = new Select(driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/select")));
									int season1int = r.nextInt(season1.getOptions().size()-1)+1;
									season1.selectByIndex(season1int);
									driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[4]/div/a[2]")).click();
									break;
								}
							}
							break;
						case 2:	
							for(int j=7; j>=1; j--){
								if(!driver.findElements(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr["+(weeks-1)+"]/td["+j+"]/a")).isEmpty()){
									driver.findElement(By.xpath("//*[@id='"+id+"']/div/div["+i+"]/table/tbody/tr["+(weeks-1)+"]/td["+j+"]/a")).click();
									wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/select")));
									Select season2 = new Select(driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[3]/div/select")));
									int season2int = r.nextInt(season2.getOptions().size()-1)+1;
									season2.selectByIndex(season2int);
									driver.findElement(By.xpath("//*[@id='tab-basic']/div[3]/div[1]/div/div[2]/div/div/div/div/div/div[4]/div/a[2]")).click();
									break;
								}
							}
					}
				}
			}

			// SAVE
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='page-wrapper']/div[2]/form/div[2]/div/div/div/div[3]/div/input")));
			driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/form/div[2]/div/div/div/div[3]/div/input")).click();
			if(driver.getTitle().equals("Accommodation season templates administration")){
				driver.close();
			}
		}
	}
}

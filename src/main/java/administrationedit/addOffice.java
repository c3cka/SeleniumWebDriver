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

public class addOffice {
	
	static WebDriver driver;
	//static String title;
	
    public static void main(String[] args) throws InterruptedException{
		
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		String[] city = {"Rijeka", "Pula", "Poreč", "Rovinj", "Umag", "Novigrad", "Vrsar", "Pazin", "Motovun", "Medulin", "Fažana", "Vodnjan", "Opatija", "Krk", "Punat", "Baška", "Njivice", "Labin", "Rabac", "Plomin", 
			"Ičići", "Ika", "Malinska", "Njivice", "Omišalj", "Dramalj", "Crikvenica", "Selce", "Cres", "Rab", "Novi Vinodolski", "Povile", "Čižići", "Vodice", "Pinezići", "Lovran", "Medveja", "shanghai","sao paulo",
			"kinshasa","cairo","peking","london","bogota","dhaka","rio de janeiro","santiago","toronto","sydney","wuhan","chongqing","xian","chengdu","alexandria","tianjin","melbourne","abidjan","shenyang","berlin","montreal",
			"harbin","guangzhou","madrid","nanjing","kabul","luanda","addis abeba","taiyuan","salvador","changchun","gizeh","fortaleza","cali","belo horizonte","brasilia","santo domingo","paris","jinan","tangshan","dalian",
			"medellin","algiers","accra","guayaquil","jilin","hangzhou","nanchang","conakry","brisbane","vancouver","minsk","hamburg","curitiba","qingdao","manaus","xinyang","barcelona","vienna","urumqi","recife","kumasi",
			"perth","cordoba","lanzhou","belem","fushun","quito","luoyang","hefei","barranquilla","lubumbashi","porto alegre","handan","suzhou","khulna","douala","yaounde","munich","rosario","anshan","xuzhou","fuzhou","guiyang",
			"goiania","guarulhos","prague","dubai","baku","brazzaville","wuxi","yerevan","copenhagen","adelaide","sofia","datong","tbilisi","xianyang","campinas","ouagadougou","huainan","kunming","brussels","shenzhen","nova iguacu",
			"rongcheng","baoding","benxi","birmingham","mendoza","cologne","calgary","maceio","cartagena","changzhou","sao goncalo","sao luis","huaibei","cochabamba","pingdingshan","qiqihar","mbuji-mayi","ottawa","wenzhou","tucuman",
			"edmonton","duque de caxias","la paz","nanning","marseille","anyang","hohhot","valencia","xining","liuzhou","natal","qinhuangdao","hengyang","taian","teresina","xinxiang","sao bernardo do campo","hegang","campo grande",
			"athens","cucuta","langfang","ningbo","yantai","zhuzhou","jaboatao","rajshahi","sarajevo","zhangjiakou","cotonou","zigong","fuxin","liaoyang","sevilla","la plata","bangui","osasco","zhangdian","puyang","nantong",
			"mudanjiang","santo andre","hamilton","joao pessoa","shaoyang","guilin","frankfurt","wahran","mar del plata","quebec","zhanjiang","zaragoza","zhenjiang","winnipeg","dandong","shaoguan","yancheng","foshan","contagem",
			"jibuti","haikou","sao jose dos campos","taizhou","xingtai","glasgow","jinzhou","abu dhabi","luancheng","dortmund","stuttgart","yingkou","zhangzhou","belfast"};
		
		Random r = new Random();
		
		Faker faker = new Faker();

        driver.get("https://goldfish.dev.hexis.hr/demo/en/administration/office/add");
		
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
		
		// Geolocation
		int cityN = r.nextInt(city.length);
		driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/form/div[2]/div/div/div/div[2]/div[10]/div[1]/div[1]/input")).sendKeys(city[cityN]);
		driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/form/div[2]/div/div/div/div[2]/div[10]/div[1]/div[1]/a")).click();
		Thread.sleep(3000);
		// SAVE
		driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/form/div[2]/div/div/div/div[2]/div[12]/div/button")).click();
		
	}
}
package administrationedit;



import com.github.javafaker.Faker;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import functions.adminLogin;
import functions.autoCompleteSelect;
import functions.imageUpload;
import java.awt.AWTException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class addAccommodation {
	
	static WebDriver driver;
	//static String title;
	
    public static void main(String[] args) throws AWTException, InterruptedException{
		
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 3);
		driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		
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
        
		String[] tags = {"featured", "homepage", "promo", "special", "recommended", "luxury"};
		
		Random r = new Random();
		
		Faker faker = new Faker();
				
        driver.get("https://goldfish.dev.hexis.hr/demo/mvc/en/accommodation/add");
        
		adminLogin login = new adminLogin(driver);
		login.loginToAdmin("demo", "demo");


        //Input data in all required fields	
        //Enter titles and descriptions in different languages
		int titleInt = r.nextInt(1000000);
		//title = driver.findElement(By.xpath("//*[@id='title__en']")).sendKeys("AccommodationEN(" + titleInt + ")");
        driver.findElement(By.xpath("//*[@id='title__en']")).sendKeys("AccommodationEN(" + titleInt + ")");
        driver.findElement(By.xpath("//*[@id='redactor-uuid-0']")).sendKeys("Description EN [" + titleInt + "]");
		//Switch to HR
        driver.findElement(By.linkText("Hrvatski")).click();
        driver.findElement(By.xpath("//*[@id='title__hr']")).sendKeys("AccommodationHR(" + titleInt + ")");
        driver.findElement(By.xpath("//*[@id='redactor-uuid-1']")).sendKeys("Description HR [" + titleInt + "]");
		//Switch to DE
        driver.findElement(By.linkText("Deutsch")).click();
        driver.findElement(By.xpath("//*[@id='title__de']")).sendKeys("AccommodationDE(" + titleInt + ")");
        driver.findElement(By.xpath("//*[@id='redactor-uuid-2']")).sendKeys("Description DE [" + titleInt + "]");
		//Switch to IT
        driver.findElement(By.linkText("Italiano")).click();
        driver.findElement(By.xpath("//*[@id='title__it']")).sendKeys("AccommodationIT(" + titleInt + ")");
        driver.findElement(By.xpath("//*[@id='redactor-uuid-3']")).sendKeys("Description IT [" + titleInt + "]");

        //driver.findElement(By.xpath("//*[@id='tab-basic']/div[9]/div[1]/div/ins")).click();
        //Choose type of accommodation
        Select accType = new Select(driver.findElement(By.xpath("//*[@id='type']")));
		int rn = r.nextInt(accType.getOptions().size());
        accType.selectByIndex(rn);
		
        //Check advanced payment
		int advPay = r.nextInt(2);
		if(advPay==0 && driver.findElement(By.id("remainingPayment")).isSelected()){
			driver.findElement(By.xpath("//*[@id='tab-basic']/div[12]/div[1]/div/ins")).click();
		}

        //Enter address
		String address = faker.address().streetAddress();
        driver.findElement(By.xpath("//*[@id='address']")).sendKeys(address);

        //Select location
		autoCompleteSelect location = new autoCompleteSelect(driver);
		location.selectOption("location", "//*[@id='ui-id-1']/li");
				
		//Geocoding
		int cityN = r.nextInt(city.length);
		driver.findElement(By.xpath("//*[@id='tab-basic']/div[16]/div[1]/div[1]/input")).sendKeys(city[cityN]);
		driver.findElement(By.xpath("//*[@id='tab-basic']/div[16]/div[1]/div[1]/a")).click();
				
		//Rating
		Select rating = new Select(driver.findElement(By.xpath("//*[@id='rating']")));
		int rat = r.nextInt(rating.getOptions().size());
		rating.selectByIndex(rat);
				
		//Select Office
		Select office = new Select(driver.findElement(By.xpath("//*[@id='office']")));
		int ofcn = r.nextInt(office.getOptions().size());
		office.selectByIndex(ofcn);

        //Select owner
		autoCompleteSelect owner = new autoCompleteSelect(driver);
		owner.selectOption("ownerId", "//*[@id='ui-id-2']/li");
				
		//Picture upload
		driver.findElement(By.xpath("//*[@id='featuredImage.choose']")).click();
		imageUpload.uploadAccommodation(driver);
		
		// TAG
		int tag1 = r.nextInt(tags.length);	
		int tag2 = r.nextInt(tags.length);
		driver.findElement(By.xpath("//*[@id='tab-basic']/div[22]/div[1]/ul/li/input")).sendKeys(tags[tag1], Keys.TAB);
		driver.findElement(By.xpath("//*[@id='tab-basic']/div[22]/div[1]/ul/li/input")).sendKeys(tags[tag2], Keys.TAB);

		//Switch to Propeties tab
		driver.findElement(By.linkText("Properties")).click();
				
		//Enter distances for object(radnom from 0-1000 m/km)

		//First distance
		
		List<WebElement> id = driver.findElements(By.xpath("//*[@class='tbl_cell'] //input"));
		for(WebElement e : id){
			String elID = e.getAttribute("id");
			int distanceInt = r.nextInt(1000);
			String distanceStr = Integer.toString(distanceInt);
			driver.findElement(By.id(elID)).sendKeys(distanceStr);
			Select distance1 = new Select(driver.findElement(By.id(elID+"_measure")));
			int distanse1m = r.nextInt(distance1.getOptions().size());
			distance1.selectByIndex(distanse1m);
		}
		
		//Select season
		driver.findElement(By.linkText("Seasons")).click();
		
		String year = driver.findElement(By.xpath("//*[@id='tab-seasons']/ul/li[1]/a")).getText();
		driver.findElement(By.linkText("Season-"+year)).click();
		driver.findElement(By.xpath("//*[@id='tab-seasons']/ul/li[2]/a")).click();
		String year1 = driver.findElement(By.xpath("//*[@id='tab-seasons']/ul/li[2]/a")).getText();
		driver.findElement(By.linkText("Season-"+year1)).click();

		//Save
        //driver.findElement(By.xpath("//*[@id='accForm']/div[2]/div/div/div/div[3]/div/button[1]")).click();
        if (driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/div[1]")).getText().contains("created")) {
            System.out.println("Accommodation created!!");
			driver.close();
		}
        //driver.close();
    }
}
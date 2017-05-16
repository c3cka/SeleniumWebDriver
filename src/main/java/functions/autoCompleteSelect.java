package functions;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 *
 * @author IgorKos
 * script for autocomplete field 
 */
public class autoCompleteSelect {
	
	WebDriver driver;

	public autoCompleteSelect(WebDriver driver) {
		this.driver = driver;	
	}
	
	public void selectOption(String elemId, String xpath){
		WebDriverWait wait = new WebDriverWait(driver, 3);
		Random r = new Random();
		driver.findElement(By.id(elemId)).click();
		WebElement autocomplete = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

		List<WebElement> lista = driver.findElements(By.xpath(xpath));
		int rn = r.nextInt(lista.size())+1;
		driver.findElement(By.xpath(xpath+"["+rn+"]")).click();
	}
}

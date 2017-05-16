/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Random;
import org.openqa.selenium.WebDriver;

/**
 *
 * @author Igor
 */
public class imageUpload {

	static Random r = new Random();
	static String pictureFolder = "C:\\Users\\Igor\\Documents\\NetBeansProjects\\mavenproject1\\src\\main\\java\\images\\";
	
	public static void uploadAccommodation(WebDriver driver) throws AWTException {
		int acc = r.nextInt(9)+1;
		StringSelection ss = new StringSelection(pictureFolder+"apartman"+acc+".jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(50);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public static void uploadUnit(WebDriver driver) throws AWTException {
		int unit = r.nextInt(6)+1;
		StringSelection ss = new StringSelection(pictureFolder+"unit"+unit+".jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(50);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	public static void uploadUnitList(WebDriver driver) throws AWTException {
		StringSelection ss = new StringSelection(pictureFolder+"interijer.jpg"+"\n"+pictureFolder+"interijer1.jpg"+"\n"+pictureFolder+"interijer2.jpg"+"\n"+ 
				pictureFolder+"interijer3.jpg"+"\n"+pictureFolder+"interijer4.jpg"+"\n"+pictureFolder+"interijer5.jpg");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot robot = new Robot();
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(50);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
}

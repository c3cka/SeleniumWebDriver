import com.github.javafaker.Faker;
import functions.adminLogin;
import functions.autoCompleteSelect;
import functions.imageUpload;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Test extends JPanel {

    private static JComboBox comboBox;
    private static JTextField textField;
	static WebDriver driver;
	static String instance;

    // Create a form with the fields
    public Test() {
        super(new BorderLayout());
        // Panel for the labels
        JPanel labelPanel = new JPanel(new GridLayout(1, 1)); // 2 rows 1 column
        add(labelPanel, BorderLayout.WEST);

        // Panel for the fields
        JPanel fieldPanel = new JPanel(new GridLayout(1, 1)); // 2 rows 1 column
        add(fieldPanel, BorderLayout.CENTER);

        // Combobox
        JLabel labelCombo = new JLabel("Instanca");

        // Options in the combobox
        String[] options = {"cappelli","demo","demo-hotel","drago-tours","el-pi","info-medulin","istra-epert","ka-reise","klub-tours","krevatin","losinia","marco-polo","mare-tours","rist","riviera","ulli","ura","villa-margaret","zlatni-trag"};
        comboBox = new JComboBox(options);
        comboBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
				//JComboBox jcmbInstance = (JComboBox) e.getSource();
				//String instance = (String) jcmbInstance.getSelectedItem();
            }
        });

        // Textfield
        JLabel labelTextField = new JLabel("Choose instance");
        textField = new JTextField();

        // Add labels
        labelPanel.add(labelCombo);
        labelPanel.add(labelTextField);

        // Add fields
        fieldPanel.add(comboBox);
        fieldPanel.add(textField);
    }

    public static void main(String[] args) throws AWTException {
        final Test form = new Test();

        // Button submit
        JButton submit = new JButton("Select");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //createIban((String) comboBox.getSelectedItem(), textField.getText());
				JComboBox jcmbInstance = (JComboBox) e.getSource();
				instance = (String) jcmbInstance.getSelectedItem();
				System.out.println(instance);
				
				System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
				driver = new ChromeDriver();
				WebDriverWait wait = new WebDriverWait(driver, 3);
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				String[] city = {"Rijeka", "Pula", "Poreč", "Rovinj", "Umag", "Novigrad", "Vrsar", "Pazin", "Motovun", "Medulin", "Fažana", "Vodnjan", "Opatija", "Krk", "Punat", "Baška", "Njivice", "Labin", "Rabac", "Plomin", 
					"Ičići", "Ika", "Malinska", "Njivice", "Omišalj", "Dramalj", "Crikvenica", "Selce", "Cres", "Rab", "Novi Vinodolski", "Povile", "Čižići", "Vodice", "Pinezići", "Lovran", "Medveja"};

				Random r = new Random();

				Faker faker = new Faker();

				driver.get("https://goldfish.dev.hexis.hr/"+instance+"/en/administration/");

				adminLogin login = new adminLogin(driver);
				login.loginToAdmin("demo", "demo");

				//Select "Accommodations" in menu
				driver.findElement(By.xpath("//*[@id='side-menu']/li[7]/a/span")).click(); ////*[@id="side-menu"]/li[7]/a
				if (driver.getTitle().equals("Accommodation administration")) {

					//Click on button "Add new accommodation"
					driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/div[2]/div/div/div/div[1]/div/div/a")).click();
					if (driver.getTitle().equals("Accommodation add")) {

						//Input data in all required fields	
						//Enter titles and descriptions in different languages
						int titleInt = r.nextInt(1000000);
						//title = driver.findElement(By.xpath("//*[@id='title__en']")).sendKeys("TitleEN(" + titleInt + ")");
						driver.findElement(By.xpath("//*[@id='title__en']")).sendKeys("TitleEN(" + titleInt + ")");
						driver.findElement(By.xpath("//*[@id='redactor-uuid-0']")).sendKeys("Description EN [" + titleInt + "]");
						//Switch to HR
						driver.findElement(By.linkText("Hrvatski")).click();
						driver.findElement(By.xpath("//*[@id='title__hr']")).sendKeys("TitleHR(" + titleInt + ")");
						driver.findElement(By.xpath("//*[@id='redactor-uuid-1']")).sendKeys("Description HR [" + titleInt + "]");
						//Switch to DE
						driver.findElement(By.linkText("Deutsch")).click();
						driver.findElement(By.xpath("//*[@id='title__de']")).sendKeys("TitleDE(" + titleInt + ")");
						driver.findElement(By.xpath("//*[@id='redactor-uuid-2']")).sendKeys("Description DE [" + titleInt + "]");
						//Switch to IT
						driver.findElement(By.linkText("Italiano")).click();
						driver.findElement(By.xpath("//*[@id='title__it']")).sendKeys("TitleIT(" + titleInt + ")");
						driver.findElement(By.xpath("//*[@id='redactor-uuid-3']")).sendKeys("Description IT [" + titleInt + "]");

						//driver.findElement(By.xpath("//*[@id='tab-basic']/div[9]/div[1]/div/ins")).click();
						//Choose type of accommodation
						Select accType = new Select(driver.findElement(By.xpath("//*[@id='type']")));
						int rn = r.nextInt(accType.getOptions().size());
						accType.selectByIndex(rn);

						//Check advanced payment
						if(!driver.findElement(By.id("remainingPayment")).isSelected()){
							driver.findElement(By.id("remainingPayment")).click();
						}

						//Enter address
						String address = faker.address().streetAddress();
						driver.findElement(By.xpath("//*[@id='address']")).sendKeys(address);

						//Select location
						autoCompleteSelect location = new autoCompleteSelect(driver);
						location.selectOption("location", "//*[@id='ui-id-1']/li");

						//Geocoding
						//String geoLocation = faker.address().city();
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
						try {
							imageUpload.uploadAccommodation(driver);
						} catch (AWTException ex) {
							Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
						}

						//Switch to Propeties tab
						driver.findElement(By.linkText("Properties")).click();

						//Enter distances for object(radnom from 0-1000 m/km)
						int distanceInt = r.nextInt(1000);
						String distanceStr = Integer.toString(distanceInt);
						//First distance
						driver.findElement(By.id("BTq0OoDrIiYjJmlyf5ibKwzT")).sendKeys(distanceStr);
						Select distance1 = new Select(driver.findElement(By.id("BTq0OoDrIiYjJmlyf5ibKwzT_measure")));
						int distanse1m = r.nextInt(distance1.getOptions().size());
						distance1.selectByIndex(distanse1m);
						//Second distance
						int distanceInt2 = r.nextInt(1000);
						String distanceStr2 = Integer.toString(distanceInt2);
						driver.findElement(By.id("0HiQbqa56TEWP6XeZT76fu6w")).sendKeys(distanceStr2);
						Select distance2 = new Select(driver.findElement(By.id("0HiQbqa56TEWP6XeZT76fu6w_measure")));
						int distanse2m = r.nextInt(distance2.getOptions().size());
						distance2.selectByIndex(distanse2m);
						//Third distance
						int distanceInt3 = r.nextInt(1000);
						String distanceStr3 = Integer.toString(distanceInt3);
						driver.findElement(By.id("tVzcnXrQF7ALeXEUMsMpERhi")).sendKeys(distanceStr3);
						Select distance3 = new Select(driver.findElement(By.id("tVzcnXrQF7ALeXEUMsMpERhi_measure")));
						int distanse3m = r.nextInt(distance3.getOptions().size());
						distance3.selectByIndex(distanse3m);

						//Select season
						driver.findElement(By.linkText("Seasons")).click();
						driver.findElement(By.xpath("//*[@id='tab-seasons']/div[1]/div[1]/div/div[2]/a[3]")).click();

						//Save
						driver.findElement(By.xpath("//*[@id='accForm']/div[2]/div/div/div/div[3]/div/button[1]")).click();
						if (driver.findElement(By.xpath("//*[@id='page-wrapper']/div[2]/div[1]")).getText().contains("created")) {
							System.out.println("Accommodation created!!");
							//driver.close();
						}
					}
					else {
						System.out.println("No adding accommodation");
					}
				} 
				else {
					System.out.println("No accommodation administration!");
				}
				//driver.close();
				
            }
        });

        // Frame for our test
        JFrame f = new JFrame("Text Form Example");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(form, BorderLayout.NORTH);

        // Panel with the button
        JPanel p = new JPanel();
        p.add(submit);
        f.getContentPane().add(p, BorderLayout.SOUTH);

        // Show the frame
        f.pack();
        f.setVisible(true);
		
		
    }

    private static void createIban(String selectedItem, String text) {
        // Do stuff with your data
        System.out.println("Im in createIban with the values: " + selectedItem + " and " + text);
    }
}
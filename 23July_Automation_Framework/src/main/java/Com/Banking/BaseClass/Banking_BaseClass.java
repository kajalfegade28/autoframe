package Com.Banking.BaseClass;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Com.Banking.Configurations.ReadConfiguration;
import Com.Banking.Utilities.Banking_TestUtils;

public class Banking_BaseClass {

	ReadConfiguration readconfig =new ReadConfiguration();
	
	public String BaseURL = readconfig.getApplicationURL();
	public String username =readconfig.getUsername();
	public String Password =readconfig.getPassword();

	public static WebDriver driver;
	public static Logger logger;
	
	@BeforeMethod
	public void initialization() throws InterruptedException {
		
		logger = logger.getLogger("23JulyAutomation Framework");
		PropertyConfigurator.configure("Log4j.properties");
		
		System.setProperty("webdriver.chrome.driver", readconfig.getChromepath());

	    driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Banking_TestUtils.IMPLICIT_WAIT,TimeUnit.SECONDS);
		
		driver.manage().timeouts().pageLoadTimeout(Banking_TestUtils.PAGE_LOAD_TIMEOUT,TimeUnit.SECONDS);
		
		driver.get(BaseURL);

		Thread.sleep(3000);
	}
	
	@AfterMethod
	public void TearDwon() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
		
	}
}

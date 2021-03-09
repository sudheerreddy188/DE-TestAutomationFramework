package coreComponents;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;



/* To understand the driver requirements and create one. 
 * 
 * 
 * 
 * 
 */

public class WebDriverFactory extends TestngToolBox{
	
	private WebDriver core_driver; 
	
	
	public void setCoreDriver(String browserName) {
		
		if(browserName.equalsIgnoreCase("chrome")) { 
			//browserVersion("88.0.4324.27") - it auto picks rite version-based on local-chrome
		    WebDriverManager.chromedriver().setup();
		    core_driver = new ChromeDriver();
		}else if (browserName.equalsIgnoreCase("firefox")) {
		    WebDriverManager.firefoxdriver().browserVersion("0.29.0").setup();
		    core_driver = new FirefoxDriver();
		}else if (browserName.equalsIgnoreCase("ie")) {
		    WebDriverManager.iedriver().browserVersion("3.9.0").setup();
		    core_driver = new InternetExplorerDriver();		
		}
		//Additional configuration calls
		core_driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		setLog4j2();

	}
	
	public WebDriver getCoreDriver() {
		return core_driver;
	}

	
	
	
}

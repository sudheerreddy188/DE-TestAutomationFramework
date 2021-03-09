package web_api_automation_fw;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;



public class SeleniumDemoTest {
	
	@Test
	public void seleniumDemo1() throws InterruptedException {
	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver();       
	    driver.get("https://www.google.com/");
	    System.out.println("I ran selenium_chrome(pom) demo tc .!!! :)");
	    Thread.sleep(3000);
	    driver.quit();
	}

	@Test
	public void seleniumDemo2() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+ 
		"\\custom\\external-dependencies\\selenium-browser-drivers\\chromedriver_win32_87.0.4280.20.exe");
		WebDriver driver = new ChromeDriver();
	    driver.get("https://www.google.com/");
	    System.out.println("I ran selenium_chrome(local) demo tc .!!! :)");
	    Thread.sleep(3000);
	    driver.quit();

	}
}

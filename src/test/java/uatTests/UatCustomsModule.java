package uatTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import coreComponents.WebDriverFactory;



public class UatCustomsModule extends WebDriverFactory{
	 
	private ThreadLocal<WebDriver> loc_driver = new ThreadLocal<WebDriver>();
	WebDriverFactory DF = new WebDriverFactory();
	
	@BeforeClass
	@Parameters({"browserName"})
	public void setUpBrowser(String browserName) {
		DF.setCoreDriver(browserName);
		loc_driver.set(DF.getCoreDriver());
	}
	
	@AfterClass
	public void tearDown() {
		loc_driver.get().close();
		loc_driver=null;
	}
	
	/*
	@BeforeMethod
	public void setUpBrowser() {
		DF.setCoreDriver("ie");
		loc_driver.set(DF.getCoreDriver());
	}
	
	@AfterMethod
	public void tearDown() {
		loc_driver.get().close();
	}
	*/

	
	@Test
	public void uatCustSelTc1() throws InterruptedException { 
	    loc_driver.get().get("https://www.google.com/");
	    System.out.println("I ran uatCustSelTc1 .! ");
	    Thread.sleep(3000);
	}
	
	@Test
	public void uatCustSelTc2() throws InterruptedException { 
	    loc_driver.get().get("https://www.amazon.co.uk/");
	    loc_driver.get().findElement(By.id("dlfsjiwejlj")).click();
	    System.out.println("I ran uatCustSelTc2 .! ");
	    Thread.sleep(3000);
	}
	
	@Test
	public void uatCustSelTc3() throws InterruptedException { 
	    loc_driver.get().get("https://github.com/");
	    System.out.println("I ran uatCustSelTc3 .! ");
	    Thread.sleep(3000);
	}


}

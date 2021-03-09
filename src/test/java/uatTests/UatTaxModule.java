package uatTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import coreComponents.WebDriverFactory;



public class UatTaxModule extends WebDriverFactory{
	
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
	public void uatTaxSelTc1() throws InterruptedException { 
	    loc_driver.get().get("https://www.google.com/");
	    System.out.println("I ran uatTaxSelTc1 .! ");
	    Thread.sleep(3000);
	}
	
	@Test
	public void uatTaxSelTc2() throws InterruptedException { 
	    loc_driver.get().get("https://www.amazon.co.uk/");
	    System.out.println("I ran uatTaxSelTc2 .! ");
	    Thread.sleep(3000);
	}
	
	@Test
	public void uatTaxSelTc3() throws InterruptedException { 
	    loc_driver.get().get("https://github.com/");
	    System.out.println("I ran uatTaxSelTc3 .! ");
	    Thread.sleep(3000);
	}


}

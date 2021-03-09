package prodTests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import coreComponents.WebDriverFactory;


 
public class ProdTaxModule extends WebDriverFactory{
	
	private ThreadLocal<WebDriver> loc_driver = new ThreadLocal<WebDriver>();
	WebDriverFactory DF = new WebDriverFactory();

	@BeforeClass(alwaysRun = true)
	@Parameters({"browserName"})
	public void setUpBrowser(String browserName) {
		DF.setCoreDriver(browserName);
		loc_driver.set(DF.getCoreDriver());
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		loc_driver.get().close();
		loc_driver.get().quit();
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
	public void prodTaxSelTc1() throws InterruptedException { 
	    loc_driver.get().get("https://www.google.com/");
	    System.out.println("I ran prodTaxSelTc1 .! ");
	    Thread.sleep(3000);
	    //System.out.println(Thread.currentThread().getId());
	}
	
	@Test
	public void prodTaxSelTc2() throws InterruptedException { 
	    loc_driver.get().get("https://www.amazon.co.uk/");
	    System.out.println("I ran prodTaxSelTc2 .! ");
	    Thread.sleep(3000);
	}
	
	@Test
	public void prodTaxSelTc3() throws InterruptedException { 
	    loc_driver.get().get("https://github.com/");
	    System.out.println("I ran prodTaxSelTc3 .! ");
	    Thread.sleep(3000);
	}
	



}

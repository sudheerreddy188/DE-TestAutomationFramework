package prodTests;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import coreComponents.WebDriverFactory;
import webPageObjects.HomePage;


 
public class ProdCustomsModule extends WebDriverFactory{
	//Common class level-block
	private static final Logger log = LogManager.getLogger(ProdCustomsModule.class.getName());
	private String currSuiteRepFold;
	private String currTestMeth;
	public ThreadLocal<WebDriver> loc_driver = new ThreadLocal<WebDriver>();
	WebDriverFactory DF = new WebDriverFactory();

	
	@BeforeClass(alwaysRun = true)
	@Parameters({"browserName"})
	public void setUpBrowser(String browserName, ITestContext iTestContex) {
		if(!browserName.equalsIgnoreCase(System.getProperty("mvnBrowserVal",browserName))) {
				System.out.println("switched to mvn browser config @class level !");
				browserName = System.getProperty("mvnBrowserVal");
		}
		DF.setCoreDriver(browserName);
		loc_driver.set(DF.getCoreDriver());
		iTestContex.setAttribute("WebDriver", loc_driver.get());
		currSuiteRepFold = currGlobReportFoldPath + "/" +
							iTestContex.getSuite().getName();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		loc_driver.get().close();
		loc_driver.get().quit();
	}
	
	 @BeforeMethod(alwaysRun = true)
	 public void beforeTestMethod(Method testMethod){
		 currTestMeth = testMethod.getName();       
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
	@Test(groups={"Smoke"})
	public void prodDepCaseForCustTc1() {
		System.out.println("I ran _prodDepCaseForCustTc1_");
		loc_driver.get().get("https://www.google.com/");
	}
	
	@Test(priority=1, groups={"Smoke"}, dependsOnMethods = { "prodDepCaseForCustTc1" })
	public void prodCustSelTc1() throws InterruptedException { 
	    loc_driver.get().get("https://www.google.com/");
	    System.out.println("I ran prodCustSelTc1 .! ");
	    Thread.sleep(3000);
	    //System.out.println(Thread.currentThread().getId());
	}
	
	@Test(groups={"other"}, invocationCount = 1)
	public void prodCustSelTc2() throws InterruptedException { 
	    loc_driver.get().get("https://www.amazon.co.uk/");
	    //loc_driver.get().findElement(By.id("dlfsjiwejlj")).click();
	    System.out.println("I ran prodCustSelTc2 .! ");
	    Thread.sleep(3000);
	}
	
	@Test(priority=2, groups={"Smoke"})
	public void prodCustSelTc3() throws InterruptedException { 
	    loc_driver.get().get(configProperties.getProperty("prodUrl"));
	    System.out.println("I ran prodCustSelTc3 .! ");
	    Thread.sleep(3000);
	}
	
	
	//This method will provide data to any test method that declares that its Data Provider
	//is named "test1"
	@DataProvider(name = "test1")
	public Object[][] createData1() {
	 return new Object[][] {
	   { "Cedric", new Integer(36) },
	   { "Anne", new Integer(37)},
	 };
	}
	 
	//This test method declares that its data should be supplied by the Data Provider
	//named "test1"
	@Test(dataProvider = "test1", groups={"other"})
	public void prodCustverifyDataTc(String n1, Integer n2) {
	 loc_driver.get().get("https://www.google.com/");
	 HomePage hP = new HomePage( loc_driver.get());
	 log.info("Navigated to homepage!");
	 hP.getGoogleSearch().sendKeys(n1 + " " + n2);
	 log.info("Performed search with " + n1 + " " + n2);
	 System.out.println(n1 + " " + n2);
	 System.out.println("I ran prodCustverifyDataTc .! ");
	}

	
	
	@Test(priority=1,groups="Regression")
	public void passTest(ITestContext iTesCon) throws Exception {
		loc_driver.get().get("https://www.github.com/");
		log.info("Navigated to homepage!");
		System.out.println("passTest!!");
		takeSnapShot(loc_driver.get(), getCurTesMethFold("homePage",iTesCon, currGlobReportFoldPath, currTestMeth)); 
		assertEquals(true, true);
	}
	
	@Test(priority=2,groups="Regression")
	public void failTest(ITestContext iTesCon) throws Exception {
		loc_driver.get().get("https://google.com/");
		log.info("Navigated to ladingPage!");
		System.out.println("failTest!!");
		takeSnapShot(loc_driver.get(), getCurTesMethFold("ladingPage",iTesCon, currGlobReportFoldPath, currTestMeth)); 
		assertEquals(false, true);
	}

	
	@Test(priority=3,groups="Regression")
	public void skipTest() {
		System.out.println("skipTest!!");
		throw new SkipException("Skipping skipTest!!");
	}


}

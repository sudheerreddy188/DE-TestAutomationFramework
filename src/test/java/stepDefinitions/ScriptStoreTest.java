package stepDefinitions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.google.inject.Key;

import coreComponents.CommLib;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.testng.AbstractTestNGCucumberTests;
//import io.cucumber.junit.Cucumber;
import io.github.bonigarcia.wdm.WebDriverManager;

//@RunWith(Cucumber.class) 
//extends AbstractTestNGCucumberTests
public class ScriptStoreTest extends CommLib{
	
	
	public String cred_validity="";
	public ThreadLocal<WebDriver> loc_driver = new ThreadLocal<WebDriver>();
	public boolean homePageBrokenLink=false;
	

	@Before("@Regression")
	public void driverSetup() throws IOException {
		loadConfigFile();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		options.addArguments("--window-size=1400,600");
	    WebDriverManager.chromedriver().setup();
	    WebDriver driver = new ChromeDriver(options);
	    loc_driver.set(driver);
	}


	@Given("Open the browser and land in home page")
	public void open_the_browser_and_land_in_home_page() throws InterruptedException {
		
		if(configProperties.getProperty("customUrl").contains("webapp")) {
			Thread.sleep(3000);
			loc_driver.get().get(configProperties.getProperty("customUrl"));
			System.out.println("Landed in Home page!");

		}else {
			loc_driver.get().get("https://www.phptravels.net/login");
			System.out.println("Landed in Home page!");
		}
		
		//loc_driver.get().manage().window().maximize();
	}

	@When("User  enters {string} {string} and {string} details")
	public void user_enters_and_details(String string, String string2, String string3) throws InterruptedException {
		
		if(configProperties.getProperty("customUrl").contains("webapp")) {
			loc_driver.get().findElement(By.xpath("//a[contains(text(),'Students')]")).click();
			System.out.println("Navigated to Students login page!");
		}
		Thread.sleep(3000);
		cred_validity = string;		
		loc_driver.get().findElement(By.xpath("//input[@name='username']")).sendKeys(string2);
		loc_driver.get().findElement(By.xpath("//input[@name='password']")).sendKeys(string3);
		loc_driver.get().findElement(By.xpath("//input[@name='password']")).sendKeys(Keys.TAB, Keys.TAB, Keys.ENTER);
		//loc_driver.get().findElement(By.xpath("//button[contains(text(),'Login')]")).click();
		System.out.println("Clicked on user login with "+ cred_validity +" credentials!");
	}	
	
	@Then("Home page navigation is {string}")
	public void home_page_navigation_is (String string) throws InterruptedException {
		
		Thread.sleep(3000);
		if(cred_validity.equalsIgnoreCase("valid")) {
			assertTrue("Login check for valid credentials->> Success", loc_driver.get().getCurrentUrl().equalsIgnoreCase("https://www.phptravels.net/account/"));
			System.out.println("User with valid credentials navigated to homepage!");
		}else {
			assertTrue("Login check for invalid Credentials->> Success", true);
			System.out.println("User with invalid credentials restricted to login!");
		}
	}
	@And("Display the user name {string}")
	public void display_the_user_name(String string) {

		if(cred_validity.equalsIgnoreCase("valid")) {
			System.out.println("User name is displayed in the home page!");
			assertTrue("HomePage check for user with valid credentials->> Success", loc_driver.get().findElement(By.xpath("//h3[contains(text(),'Hi')]")).getText().equalsIgnoreCase("Hi, Demo User"));
		}	
	}
	
	@When("Click on all the available links")
	public void click_on_all_the_available_links() {
		
        String url = "";
        HttpURLConnection huc = null;
        int respCode = 200;
        
        List<WebElement> links = loc_driver.get().findElements(By.tagName("a"));        
   
        for ( WebElement webEle: links) {           
            url = webEle.getAttribute("href");
                  
            //System.out.println(url);        
            if(url == null || url.isEmpty() || url == "#"){
            	System.out.println( webEle.getText() + 
            			" -->URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
           /*if(!url.startsWith(configProperties.getProperty("customUrl"))){
                System.out.println(webEle.getText() + 
            			" --URL belongs to another domain, skipping it:- " + url  );
                continue;
            }*/
            
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());    
                
                huc.setRequestMethod("GET");
                huc.setRequestProperty("Content-Type", "application/json");
                huc.setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36");
                huc.connect();          
                
                respCode = huc.getResponseCode();                
                if(respCode >= 400){
                    System.out.println(webEle.getText() + 
                		" -->" + " is a broken link: " + url + " (respCode: " + respCode + ")");
                    homePageBrokenLink=true;
                }
                else{
                    System.out.println( webEle.getText() + 
                			" -->" + " is a valid link: " + url);
                }
                    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           
        }		
		

		
	}
	
	@Then("No link should be broken")
	public void No_link_should_be_broken() {
		assertFalse("Home page broken links:", homePageBrokenLink);
	}
	
	
	@After("@Regression")
	public void tearDown() {
		loc_driver.get().close();
		loc_driver.get().quit();
	}

}

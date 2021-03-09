package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Hooks {

	@Before("@Sanity")
	public void demoBeforeHook() {
		System.out.println("demoBeforeHook triggered for scenario with tag- @Sanity!");
	}
	
	@After("@Sanity")
	public void demoAfterHook() {
		System.out.println("demoAfterHook triggered for scenario with tag- @Sanity!");
	}
	
}

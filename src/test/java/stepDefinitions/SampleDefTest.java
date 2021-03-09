package stepDefinitions;

import org.junit.runner.RunWith;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
//import io.cucumber.junit.Cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;

//@RunWith(Cucumber.class) 
//extends AbstractTestNGCucumberTests
public class SampleDefTest {

	
	@Given("fine day")
	public void fine_day() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("cucumber demo: start");
	}

	@When("Hi how are you")
	public void hi_how_are_you() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("hello");
	}
	@Then("doing good")
	public void doing_good() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("hello");
	}
	@And("how about you")
	public void how_about_you() {
	    // Write code here that turns the phrase above into concrete actions
		System.out.println("cucumber demo: end");
	}

}

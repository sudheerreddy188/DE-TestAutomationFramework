package cucumberOptions;

import org.junit.runner.RunWith;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

//import io.cucumber.junit.Cucumber;
//import io.cucumber.junit.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.TestNGCucumberRunner;

//@RunWith(Cucumber.class)
@io.cucumber.testng.CucumberOptions(
					monochrome=true,
					strict = true,
					//tags = "not @Regression and @Sanity",					
					tags = "@Regression",
					features="src/test/java/features/",
					glue= "stepDefinitions",
					plugin = { 
							 //Default cucumber-reports
							 "pretty", 
							 "json:target/cucumber-reports/Cucumber.json",
							 "junit:target/cucumber-reports/Cucumber.xml",
							 "html:target/cucumber-reports/Cucumber.html",
							 //maven-cucumber reports
							 "json:target/jsonReports/cucumber-report.json"
							 }
				)

public class RunnerTest extends AbstractTestNGCucumberTests{
	

}

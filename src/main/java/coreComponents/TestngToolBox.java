package coreComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IClass;
import org.testng.IExecutionListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class TestngToolBox extends CommLib 
implements IExecutionListener,ISuiteListener,ITestListener,ITestResult {


	public static String currGlobReportFoldPath;
	WebDriver loc_driver = null;

	
	//global-execution level methods****************
	@Override
	public void onExecutionStart() {
		createReportFoldCurrDate();
		currGlobReportFoldPath= createReportFoldCurrDateTime();
		try {loadConfigFile();} catch (IOException e) {e.printStackTrace();}
		setLog4j2();
	}


	@Override
	public void onExecutionFinish() {
		
	}	
	
	
	
	
	//suite-level methods****************
	@Override
	public void onStart(ISuite suite) {
		createSuiteFold(currGlobReportFoldPath + "/" + suite.getName());
	}


	@Override
	public void onFinish(ISuite suite) {

	}
	
	
	@BeforeSuite(alwaysRun = true)
	public void initializeRunEssentials() throws IOException  {

	}

	
	//Test-level methods*******************
	@Override
	public void onTestStart(ITestResult result) {
		//put up a TC-fold-to store screenshots
		createTestCaseFold(currGlobReportFoldPath + "/" + 
				result.getTestContext().getSuite().getName().toString()+ "/"
				+ result.getName());
	}
	

	@Override
	public void onTestSuccess(ITestResult result) {
		//update reports
		takeSanpShotInListener("TestSuccess",result,loc_driver);		
	}


	@Override
	public void onTestFailure(ITestResult result) {
		//update reports
		takeSanpShotInListener("TestFailure",result,loc_driver);
	}


	@Override
	public void onTestSkipped(ITestResult result) {
		//update reports

		
	}

	//To capture web-screenshots in listeners class
	public void takeSanpShotInListener(String userMsg, ITestResult result,WebDriver webdriver ) {
		ITestContext  context = result.getTestContext();
		webdriver = (WebDriver) context.getAttribute("WebDriver");
	    try {
			takeSnapShot(webdriver, currGlobReportFoldPath + "/" + 
					result.getTestContext().getSuite().getName().toString()+ "/"
					+ result.getName() + 
					"/"+ userMsg + "_" + getCustomDate("HH-mm-ss")+ ".png");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	
	
	
	
	
	
	
	//**** unused listener methods ****************************************************
	
	@Override
	public Object getAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setAttribute(String name, Object value) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Set<String> getAttributeNames() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object removeAttribute(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public int compareTo(ITestResult arg0) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setStatus(int status) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public ITestNGMethod getMethod() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] getParameters() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setParameters(Object[] parameters) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public IClass getTestClass() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Throwable getThrowable() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setThrowable(Throwable throwable) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public long getStartMillis() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public long getEndMillis() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void setEndMillis(long millis) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean isSuccess() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public String getHost() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object getInstance() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Object[] getFactoryParameters() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getTestName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String getInstanceName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ITestContext getTestContext() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setTestName(String name) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean wasRetried() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public void setWasRetried(boolean wasRetried) {
		// TODO Auto-generated method stub
		
	}





	
	
	
	
}

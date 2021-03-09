package coreComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;


public class CommLib {

	public Properties configProperties= null;
	public FileInputStream fIpStrm;

	
	
	//******Framework level generic functions web/api********
	//*******************************************************
	
	
	
	//load configuration file 
	public void loadConfigFile() throws IOException {
	configProperties = new Properties();
		fIpStrm =new FileInputStream(System.getProperty("user.dir")+
				"\\src\\main\\java\\resources\\config.properties");
		configProperties.load(fIpStrm);
	} 
	
	
	//Configure log4j2
	public void setLog4j2() {
		 LoggerContext context = (LoggerContext) LogManager.getContext(false);
	     File file = new File(System.getProperty("user.dir"), "\\src\\main\\java\\resources\\log4j2.xml");
	     context.setConfigLocation(file.toURI());

	}
	

	//create a report fold with current date
	public void createReportFoldCurrDate() {
		createDir(System.getProperty("user.dir")+"/custom/reports/"+getCustomDate("dd-MM-yyyy"));
	}
	
	//create a report fold with current date & time
	public String createReportFoldCurrDateTime() {
		String path = System.getProperty("user.dir")+"/custom/reports/"
				+getCustomDate("dd-MM-yyyy")+"/"+getCustomDate("dd-MM-yyyy  HH-mm-ss");
		createDir(path);
		return path;
	}
	
	//create a fold for each suite
	public void createSuiteFold(String foldPath) {
		createDir(foldPath);
	}	
	
	//create a fold for each test-cases
	public void createTestCaseFold(String foldPath) {
		createDir(foldPath);
	}		
	
	
	//To get current date and time in desired format (dd/MM/yyyy HH:mm:ss)
	public String getCustomDate(String reqiredFormat) {
		SimpleDateFormat formatter = new SimpleDateFormat(reqiredFormat);  
	    Date date = new Date();  
	    //System.out.println(formatter.format(date));
	    return formatter.format(date).toString();
	}

	//check and create a directory in a path
	public void createDir(String dirWithPath) {
		File theDir = new File(dirWithPath);
		if (!theDir.exists()){
		    theDir.mkdirs();
		}
	}
	
	//To capture web-screenshots
	public void takeSnapShot(WebDriver webdriver,String fileWithPath) throws Exception{
		TakesScreenshot scrShot =((TakesScreenshot)webdriver);
		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		File DestFile=new File(fileWithPath);
		FileUtils.copyFile(SrcFile, DestFile);
	}
	
	//To build file path for current test method
	public String getCurTesMethFold(String userMsg, ITestContext iTesCon, String globFoldPath, String currTestMeth) {
		String path = globFoldPath + "/" + 
				iTesCon.getSuite().getName().toString() + "/" + 
				currTestMeth + "/" +
				userMsg + "_" + getCustomDate("HH-mm-ss") + ".png";
		return path;
	}
	
}

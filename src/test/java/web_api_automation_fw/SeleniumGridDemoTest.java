package web_api_automation_fw;

import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SeleniumGridDemoTest {
	
	//To setup the selenium grid hub and node in local machine
	@BeforeTest
	public void setUpGridHubAndNode() throws IOException, InterruptedException {
		
		//write Hub-start commands
		File file=new File(System.getProperty("user.dir")+
				"\\custom\\triggers\\batch-files\\selenium-grid-hub-start.bat");
		FileOutputStream fos=new FileOutputStream(file);
		DataOutputStream dos=new DataOutputStream(fos);
		dos.writeBytes("CD "+System.getProperty("user.dir").charAt(0)+":");
		dos.writeBytes("\n");
		dos.writeBytes("CD " + System.getProperty("user.dir")+
				"\\custom\\external-dependencies\\selenium-grid");
		dos.writeBytes("\n");
		String selenium_grid_server_hub =
				getFirstFileNameMatchInDir("selenium-server-standalone*.jar", System.getProperty("user.dir")+
				"\\custom\\external-dependencies\\selenium-grid");
		dos.writeBytes("java -jar "+selenium_grid_server_hub+" -role hub -log log.txt ");
		
		
		//start hub
		runBatchFile("selenium-grid-hub-start.bat", System.getProperty("user.dir")+
				"\\custom\\triggers\\batch-files");
		
		//write node-start commands
		File file2=new File(System.getProperty("user.dir")+
				"\\custom\\triggers\\batch-files\\selenium-grid-node-start.bat");
		FileOutputStream fos2=new FileOutputStream(file2);
		DataOutputStream dos2=new DataOutputStream(fos2);
		dos2.writeBytes("CD "+System.getProperty("user.dir").charAt(0)+":");
		dos2.writeBytes("\n");
		dos2.writeBytes("CD " + System.getProperty("user.dir")+
				"\\custom\\external-dependencies\\selenium-node");
		dos2.writeBytes("\n");
		dos2.writeBytes("java -Dwebdriver.chrome.driver=\"chromedriver_win32_87.0.4280.20.exe\" "
				+ "-jar \"selenium-server-standalone-3.141.59.jar\" "
				+ "-role node -hub http://192.168.0.9:4444/grid/register/ -port 5566 -log log.txt");

		
		//start node
		Thread.sleep(10000);
		runBatchFile("selenium-grid-node-start.bat", System.getProperty("user.dir")+
				"\\custom\\triggers\\batch-files");
		
		Thread.sleep(15000);
		
		//write hub kill commands

		//write node kill commands

		//kill node
		
		//kill hub
	
	}
	
	//To run a sample tc using selenium grid
	@Test
	public void seleniumGridDemoTc() throws MalformedURLException {
		//Define desired capabilities
				DesiredCapabilities cap=new DesiredCapabilities();
				cap.setBrowserName("chrome");
				cap.setPlatform(Platform.WINDOWS);
				
				//Chrome option  
				ChromeOptions options = new ChromeOptions();
				options.merge(cap);
				options.setHeadless(false);
				
				//Hub URL
				String huburl ="http://192.168.0.9:4444/wd/hub";
				 
				// Create driver with hub address and capability
				WebDriver driver=new RemoteWebDriver(new URL(huburl), options);

				//Test case
				driver.get("https://www.google.com/");
				 
				System.out.println("Title is "+driver.getTitle());
				 
				driver.quit();
				
	}
	
	//To kill hub and node of selenium grid
	@AfterTest
	public void killHubNode() throws IOException, InterruptedException {
		runBatchFile("selenium-grid-node-stop.bat", System.getProperty("user.dir")+
				"\\custom\\triggers\\batch-files");
		Thread.sleep(5000);
		runBatchFile("selenium-grid-hub-stop.bat", System.getProperty("user.dir")+
				"\\custom\\triggers\\batch-files");	
	}
	
	//To get the first match file name in a directory using in-string of required file name
	public String getFirstFileNameMatchInDir(String fileName_Instring, String dir_Path) {
		File dir = new File(dir_Path);
		FileFilter fileFilter = new WildcardFileFilter(fileName_Instring);
		File[] files = dir.listFiles(fileFilter);
		if (files.length >= 1) {
			System.out.println("getFirstFileNameMatchInDir for " + fileName_Instring +
					" is " + files[0].getName());
			return files[0].getName(); 
		}else {
			System.out.println("Excep: couldn't getFirstFileNameMatchInDir for " + fileName_Instring + 
					" in " + dir_Path);
			return "";
		}
		
	}
	
	//To run any batch file through command line
	public void runBatchFile(String batFileName, String batFilePath) throws IOException {
		
		Path path = Paths.get(batFilePath);
		Process p =  Runtime.getRuntime().exec("cmd /c " + batFileName, null, 
				new File(path.toString()));
		System.out.println("Triggered: " + batFileName);

	}
	

	
	

}

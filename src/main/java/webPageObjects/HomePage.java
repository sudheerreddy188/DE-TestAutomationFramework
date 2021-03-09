package webPageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public WebDriver driver;
	//Constructor
	public HomePage(WebDriver driver) {
		//POM design approach (way-1)
		this.driver=driver;	
		//Page factory class to implement POM design-approach (way-2)
		PageFactory.initElements(driver, this);
	}
	
	
	By googleSearch = By.xpath("//input[@name='q']");
	By userName=By.cssSelector("[id='user_email']");
	@FindBy(xpath = "") WebElement password;
	@FindBy(xpath = "") WebElement signInBut;

	
	public WebElement getGoogleSearch()
	{
		return driver.findElement(googleSearch);
	}

	public WebElement getUserName()
	{
		return driver.findElement(userName);
	}
	
	public WebElement getPassword()
	{
		return password;
	}
	
	public WebElement getSignInBut()
	{
		return signInBut;
	}
	

}

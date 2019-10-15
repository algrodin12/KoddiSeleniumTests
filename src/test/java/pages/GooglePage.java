package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class GooglePage {

    WebDriver driver;

    public GooglePage(WebDriver driver)
    {
        this.driver = driver;
    }
    
    //Wait for Google Page to Load
    public void waitForPageToLoad(String pageId)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pageId)));
    }

    //Set input value of google search & enter
    public void setInputValue(String input)
    {
        driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(input);
        driver.findElement(By.xpath("//input[@title='Search']")).sendKeys(Keys.ENTER);
    }
    
    //verify Google Home Page
    public void verifyGoogleHomePage()
    {
	    //Verify Google Logo is displayed
        Assert.assertTrue(driver.findElement(By.id("hplogo")).isDisplayed());
        
        //Verify Google Logo
	    Assert.assertEquals(driver.findElement(By.tagName("img")).getAttribute("alt"), "Google");
	    
	    //Verify Google Search Bar is Displayed
	    Assert.assertTrue(driver.findElement(By.xpath("//input[@title='Search']")).isDisplayed());
    }

    //Loop through 1st page of results to find matching link
    public void selectGoogleResult(String googleResultLink)
    {
    	//Google Results
    	WebElement googleResults = driver.findElement(By.cssSelector("div#rso > div:nth-child(1)"));
    	List<WebElement> results = googleResults.findElements(By.className("g"));
    	
    	//For loop through Google Results
    	for (WebElement result : results) 
    	{
    		//If a result has a matching link, click on link
			if (result.findElement(By.cssSelector("div > div.rc > div.r > a")).getAttribute("href").contains(googleResultLink)) 
			{
				result.findElement(By.cssSelector("div > div.rc > div.r > a > h3")).click();
				break;
			}
			//If a result does not have a matching link, return message
			else 
			{
				System.out.println("This link does not exist");
			}    	
    	}
    }
    
}
package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class KoddiPage {

    WebDriver driver;

    public KoddiPage(WebDriver driver)
    {
        this.driver = driver;
    }
    
    //Wait for Koddi page to load
    public void waitForPageToLoad(String pageClassName)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(pageClassName)));
    }
    
    //Wait for first job to load
    public void waitForJobsToLoad()
    {
        WebDriverWait wait = new WebDriverWait(driver, 2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#jobs-container > div:nth-child(1) > div:nth-child(2) > div > div:nth-child(1) > div > div > div")));
    }
    
    //Verify Koddi home page logo
    public void verifyKoddiHomePageLogo()
    {
    	//Verify Koddi Logo is displayed
        Assert.assertTrue(driver.findElement(By.className("x-brand")).isDisplayed());
        
        //Verify Koddi logo
	    Assert.assertTrue(driver.findElement(By.tagName("img")).getAttribute("src").contains("Koddi-Logo.png"));
    }
    
    //Verify Career Page
    public void verifyKoddiCareerPage()
    {
    	//Verify Page Title is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("div.d-flex > h1")).isDisplayed());
		
		//Verify Page Title is Careers
		Assert.assertTrue(driver.findElement(By.cssSelector("div.d-flex > h1")).getText().equals("CAREERS"));
		
		//Verify Job Section
		Assert.assertTrue(driver.findElement(By.id("jobs")).isDisplayed());
    }

    //Hover tab on nav bar & select link
    public void hoverOverTabOnNavBarAndSelectLink(String tabName, String linkName)
    {
    	WebElement navBar = driver.findElement(By.className("navbar-nav"));
    	List<WebElement> tabs = navBar.findElements(By.className("nav-item"));
    	for (WebElement tab : tabs) 
    	{
    		if (tab.findElement(By.tagName("a")).getAttribute("href").contains(tabName))
			{
    			Actions hover = new Actions(driver);
    			hover.moveToElement(tab).build().perform();
    			WebElement subNav = tab.findElement(By.className("dropdown-menu"));
    	    	List<WebElement> links = subNav.findElements(By.className("dropdown-item"));
    	    	for (WebElement link : links) 
    	    	{
    	    		if (link.getAttribute("href").contains(linkName))
    				{
    					link.click();
    					break;
    				}
    	    	}
    	    	break;
			}
    	}
    }
    
    //Set job filters of Koddi job search
    public void setJobFilters(String location, String jobType, String commitment)
    {
    	//Set location filter
       	driver.findElement(By.id("location")).click();
    	Select locations = new Select(driver.findElement(By.id("location")));
    	locations.selectByVisibleText(location);
    	
    	//Set type filter
    	driver.findElement(By.id("type")).click();
    	Select jobTypes = new Select(driver.findElement(By.id("type")));
    	jobTypes.selectByVisibleText(jobType);
    	
    	//Set commitment filter
    	driver.findElement(By.id("commitment")).click();
    	Select commitments = new Select(driver.findElement(By.id("commitment")));
    	commitments.selectByVisibleText(commitment);
    	
    	//scroll into jobs view
    	((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.id("jobs")));   	
    }
    

    //Verify Job Opening Results
    public void verifyJobOpenings(String jobName, String jobType, String jobLocation)
    {
    	WebElement jobOpenings = driver.findElement(By.cssSelector("#jobs-container > div > div:nth-child(2) > div"));
    	List<WebElement> jobs = jobOpenings.findElements(By.className("mb-4"));
    	for (WebElement job : jobs) 
    	{
    		//Verify Job Title contains job type selected
    		Assert.assertTrue(job.findElement(By.cssSelector("div.job-item > div > div.job-item__box > div > h4")).getText().contains(jobName));
    		//Verify Job Type matches Job Type Selected
    		Assert.assertTrue(job.findElement(By.cssSelector("div.job-item > div > div.job-item__box > div > div.row > span:nth-child(1)")).getText().equals(jobType));
    		//Verify Job Location matches Location Selected
    		Assert.assertTrue(job.findElement(By.cssSelector("div.job-item > div > div.job-item__box > div > div.row > span:nth-child(2)")).getText().equals(jobLocation));
    	}
    }
    
}
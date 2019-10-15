package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.KoddiPage;

public class KoddiTest {		
	    WebDriver driver;
	    KoddiPage objKoddi;
	    
		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
		}		
		
		/**
	     * Navigate to https://koddi.com/
	     * Verify Koddi Home Page & Logo
	     * Loop through nav bar to hover over ABOUT
	     * Look through sub nav bar to select CAREERS
	     * Go to Career Center
	     * Search for Jobs
	     * Verify Job Opening Results
	     */
		@Test				
		public void koddiTest()
		{	
			//Create Koddi Page object
			objKoddi = new KoddiPage(driver);
	
			//Navigate to Koddi
	        driver.get("https://koddi.com/");

	        //Wait for Koddi Page to Load
	        objKoddi.waitForPageToLoad("site");
	   
	        //Verify Koddi Logo
	        objKoddi.verifyKoddiHomePageLogo();

	        //Select About Us on Navbar
			objKoddi.hoverOverTabOnNavBarAndSelectLink("about", "career");
			
	        //Wait for First Koddi Job to load
	        objKoddi.waitForJobsToLoad();
	        
	        //Verify Career Page
	        objKoddi.verifyKoddiCareerPage();
	     
	        //Set Job Search Filters
			objKoddi.setJobFilters("Ann Arbor, Michigan", "Engineering", "Full-time");

	        //Verify job openings with filters
	        objKoddi.verifyJobOpenings("Engineer", "Engineering", "Ann Arbor, Michigan");
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}
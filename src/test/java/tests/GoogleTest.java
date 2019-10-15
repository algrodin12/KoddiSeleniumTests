package tests;

import org.testng.annotations.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;		

import pages.GooglePage;
import pages.KoddiPage;

public class GoogleTest {		
	    WebDriver driver;
	    GooglePage objGoogleSearch;
	    KoddiPage objKoddi;

		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.google.com
	     * Verify Google Home Page & Logo
	     * Search for 'Koddi'
	     * Loop through 1st page of results to find https://koddi.com/
	     * Click on https://koddi.com/
	     * Verify Koddi Home Page & Logo
	     */
		@Test				
		public void googleTest()
		{	
			//Create Google Page object
			objGoogleSearch = new GooglePage(driver);
	
			//Navigate to Google
	        driver.get("https://www.google.com");

	        //Wait for Google Page to Load
	        objGoogleSearch.waitForPageToLoad("viewport");
	        
	        //Verify Google Home Page
	        objGoogleSearch.verifyGoogleHomePage();
	        
		    //Set input value for Search & Enter
		    objGoogleSearch.setInputValue("Koddi");

		    //Wait for results to load
	        objGoogleSearch.waitForPageToLoad("rhscol");
	        
	        //loop through results on first page for https://koddi.com/ and click it
	        objGoogleSearch.selectGoogleResult("https://koddi.com/"); 
	        
	        //Create Koddi Page object
	        objKoddi = new KoddiPage(driver);
			
	        //Wait for Koddi Page to Load
	        objKoddi.waitForPageToLoad("site");
	        
	        //Verify Koddi Logo
	        objKoddi.verifyKoddiHomePageLogo();
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}
package tests;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pages.CalculatorPage;

public class CalculatorTest {		
	    WebDriver driver;
	    CalculatorPage objCalculator;
	    
		@BeforeMethod
		public void beforeTest() 
		{	
	        driver = new ChromeDriver();
		}		
		
		/**
	     * Navigate to https://www.calculator.net
	     * Verify Calculator is displayed
	     * Loop through calculator numbers
	     * Select numbers on calculator
	     * Verify input and output of calculator
	     */
		@Test				
		public void calculatorTest()
		{	
			//Create Calculator Page object
			objCalculator = new CalculatorPage(driver);
	
			//Navigate to Calculator
	        driver.get("https://www.calculator.net");

	        //Wait for Calculator to Load
	        objCalculator.waitForPageToLoad("sciout");
	        
	        //Click on 5 + 6 = on calculator
	        objCalculator.selectNumber("5");
	        objCalculator.selectNumber("+");
	        objCalculator.selectNumber("6");
	        objCalculator.selectNumber("=");

	        //Verify the calculator input is 5 + 6 =
	        Assert.assertTrue(driver.findElement(By.id("sciInPut")).getText().equals(" 5 + 6 ="));
	        
	        //Verify the calculator output is 11
	        Assert.assertTrue(driver.findElement(By.id("sciOutPut")).getText().equals(" 11"));
		}	
		
		@AfterMethod
		public void afterTest() 
		{
			driver.quit();			
		}		
}
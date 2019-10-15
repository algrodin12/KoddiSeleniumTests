package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalculatorPage {

    WebDriver driver;

    public CalculatorPage(WebDriver driver)
    {
        this.driver = driver;
    }
    
    //Wait for Calculator to load
    public void waitForPageToLoad(String pageId)
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(pageId)));
    }

    //Loop through numbers on calculator
    public void selectNumber(String number)
    {
		WebElement calculator = driver.findElement(By.cssSelector("#sciout > tbody > tr:nth-child(2) > td:nth-child(2) > div"));
		List<WebElement> numberRows = calculator.findElements(By.tagName("div"));
		
		//Loop through each row of numbers
		for (WebElement numberRow : numberRows) 
    	{
			List<WebElement> numberCells = numberRow.findElements(By.tagName("span"));
			
			//Loop through each cell of each row
			for (WebElement numberCell : numberCells) 
	    	{
				if (numberCell.getText().equals(number))
				{
					numberCell.click();
				}
	    	}
		}
	}

    
    
}
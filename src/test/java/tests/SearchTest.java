package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTest {
	
	@Test
	public void SearchItem() {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.findElement(By.cssSelector("input[name='q']")).sendKeys(Keys.chord("Selenium",Keys.ENTER));
		Assert.assertTrue(driver.getPageSource().contains("Selenium"), "Item is not present");
		
		
		
	}

}

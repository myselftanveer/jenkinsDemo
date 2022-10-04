package tests;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SearchTest {

	WebDriver driver;

	@BeforeTest
	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("./src/main/java/resources/GlobalData.properties");

		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
//			driver.manage().window().setSize(new Dimension(1440, 900));// full screen

		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			// Firefox
		} else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;

	}

	@Test(priority=0)
	public void SearchItem() {

//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com/");

		System.out.println("Image is visible: " + driver.findElement(By.xpath("//img[@alt='Google']")).isDisplayed());
		System.out.println("QA Branch");
		System.out.println("The current title is:" + driver.getTitle());
		System.out.println("The current url is:" + driver.getCurrentUrl());

		driver.findElement(By.cssSelector("input[name='q']")).sendKeys(Keys.chord("Selenium", Keys.ENTER));
		Assert.assertTrue(driver.getPageSource().contains("Selenium"), "Item is not present");
	}


	@AfterMethod(alwaysRun = true)

	public void tearDown() {
		driver.close();
	}

}

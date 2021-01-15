package pe.edu.galaxy;

import org.testng.annotations.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumGrid {

	WebDriver driver;
	String baseURL = "https://www.google.com.pe/";
	String nodeURL = "http://localhost:4444/wd/hub";

	
	@BeforeTest
	public void Setup() throws MalformedURLException {
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.LINUX);
		driver = new RemoteWebDriver(new URL(nodeURL), capability);
	}

	@Test
	public void sampleTest() {
		driver.get(baseURL);

		if (driver.getTitle().contains("Google")) {
			Assert.assertTrue(true, "Pass: Test has been completed");
			System.out.println("Test Completed");
		} else {
			Assert.assertTrue(false, "Failed: Test failed");
			System.out.println("Test Failed");
		}
	}

	@AfterTest
	public void TearDown() {
		driver.quit();
	}

}

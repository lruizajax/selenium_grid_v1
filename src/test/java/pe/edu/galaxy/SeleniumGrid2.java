package pe.edu.galaxy;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumGrid2 {

	WebDriver driver = null	;
	String nodeURL = "http://localhost:4444/wd/hub";
	
	DesiredCapabilities caps = new DesiredCapabilities();


	@SuppressWarnings("deprecation")
	@Parameters({ "platformName", "browserName", "browserVersion" })	
	@BeforeTest(alwaysRun = true)
	public void Setup(String platformName, String browserName, String browserVersion) throws MalformedURLException {
		
		//setup platformName
		if (platformName.equalsIgnoreCase("Windows")) {
		    caps.setPlatform(Platform.WINDOWS);
		}else if(platformName.equalsIgnoreCase("MAC")) {
		    caps.setPlatform(Platform.MAC);
		}else if(platformName.equalsIgnoreCase("LINUX")) {
		    caps.setPlatform(Platform.LINUX);
		}
		
		//setup browserName
		if (browserName.equalsIgnoreCase("chrome")) {
		    caps = DesiredCapabilities.chrome();
		}else if (browserName.equalsIgnoreCase("firefox")) {
		    caps = DesiredCapabilities.firefox();
		}else if(browserName.equalsIgnoreCase("opera")){
		    caps = DesiredCapabilities.opera();
		}

		//version browser
		caps.setVersion(browserVersion);
	}

	@Parameters({ "url" })
	@Test
	public void sampleTest(String url) throws MalformedURLException {
		
		driver = new RemoteWebDriver(new URL(nodeURL), caps);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		
		driver.findElement(By.name("q")).sendKeys("Selenium Dev");
		driver.findElement(By.name("btnK")).click();
		
		if (driver.getTitle().contains("Google")) {
			Assert.assertTrue(true, "Pass: Test has been completed");
			System.out.println("Test Completed");
		} else {
			Assert.assertFalse(false, "Failed: Test failed");
			System.out.println("Test Failed");
		}
		
	}

	@AfterTest
	public void TearDown() {
		driver.quit();
	}

}

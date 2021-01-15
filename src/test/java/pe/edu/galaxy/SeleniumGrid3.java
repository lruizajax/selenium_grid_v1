package pe.edu.galaxy;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumGrid3 {

	WebDriver driver = null	;
	String nodeURL = "http://localhost:4444/wd/hub";
	String url = "http://the-internet.herokuapp.com/secure";
	
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
	public void doLoginSuccessfully() throws MalformedURLException, InterruptedException {
		
		String message = "Secure Area";
		String actual = "";
		
		driver = new RemoteWebDriver(new URL(nodeURL), caps);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
		
		try {
			driver.findElement(By.id("username")).sendKeys("tomsmith");
			driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
			driver.findElement(By.xpath("//*[@id=\"login\"]/button")).submit();
		}finally{
			Thread.sleep(1000);
			actual = driver.findElement(By.xpath("//*[@id=\"content\"]/div/h2")).getText();
			Assert.assertEquals(actual, message);
		}
		
	}

	@AfterTest
	public void TearDown() {
		driver.quit();
	}

}

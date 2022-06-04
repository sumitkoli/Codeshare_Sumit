package com.project.Base_class;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base_Class {

	 protected static WebDriver driver=null;
	 protected static ChromeOptions options;
		
		@BeforeMethod
		public void applicationSetup()
		{
			/*
			 * System.setProperty("webdriver.chrome.driver",
			 * System.getProperty("user.dir")+"//Drivers/chromedriver.exe");
			 * 
			 * ChromeOptions options=new ChromeOptions();
			 * 
			 * options.addArguments("--incognito");
			 */
			
			WebDriverManager.chromedriver().setup();
			
			options = new ChromeOptions();
			
			options.addArguments("incognito");
			
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(capabilities);
			
			driver=new ChromeDriver(options);
			
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			
			driver.get("https://codeshare.io/");
			
		}
		
		@AfterMethod
		public void applicationClose()
		{
			driver.quit();
			
			
		}
}

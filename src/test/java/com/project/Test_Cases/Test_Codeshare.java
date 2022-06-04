package com.project.Test_Cases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.project.Base_class.Base_Class;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test_Codeshare extends Base_Class{

	@Test
	public void Test01_codeshare() throws InterruptedException, AWTException {
		
		String parentWindowHandle = driver.getWindowHandle();

		driver.findElement(By.xpath("//a[contains(text(),'Share Code Now')]")).click();

		Thread.sleep(2000);

		WebElement ele_textarea = driver.findElement(By.tagName("textarea"));
		// # focusing

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].focus();", ele_textarea);

		// Execute js statement

		js.executeScript("arguments[0].value='#include <stdio.h>'", ele_textarea);

		Thread.sleep(2000);

		String url1 = driver.getCurrentUrl();

		openBrowser(url1);

		Thread.sleep(2000);

		String actual =driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[6]/div[1]/div/div/div/div[5]")).getText().trim().substring(1).trim();
		System.out.println(actual);
		
		String expected="#include <stdio.h>";
		
		Assert.assertEquals(actual, expected);
		
		WebElement ele_textareaa = driver.findElement(By.tagName("textarea"));
		
		Actions as=new Actions(driver);
		
		as.moveToElement(ele_textareaa).click();
		as.moveByOffset(10, 20).perform();
		as.click().perform();
		
		
		  Thread.sleep(2000);
		  
		  Robot robot = new Robot(); robot.keyPress(KeyEvent.VK_ENTER);
		  robot.keyRelease(KeyEvent.VK_ENTER);
		 
		
		
		// # focusing

		
		  JavascriptExecutor js2 = (JavascriptExecutor) driver;
		  js2.executeScript("arguments[0].focus();", ele_textareaa);
		  
		  // Execute js statement
		  
		  js2.executeScript("\narguments[0].value='#include <conio.h>'",
		  ele_textareaa);
		 

		  String actual1 =driver.findElement(By.xpath("//*[@id=\"editor\"]/div/div/div[6]/div[1]/div/div/div/div[5]")).getText();
			System.out.println(actual1);
			
			Thread.sleep(2000);
			
			//as.keyDown(Keys.SHIFT).keyDown(Keys.CONTROL).sendKeys("n").build().perform();
			
			driver.close();
			
			//driver.switchTo().window(parentWindowHandle);
		
			
	}

	public void openBrowser(String url) {
		WebDriverManager.chromedriver().setup();

		options = new ChromeOptions();

		options.addArguments("incognito");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		options.merge(capabilities);

		driver = new ChromeDriver(options);

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);
	}
}

/**
 * 
 */
package com.mystore.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.mystore.actiondriver.Action;
import com.mystore.utility.ExtentManager;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.remote.RemoteWebDriver;




/**
 * @author jagad
 *
 */
public class BaseClass {

	
	public static Properties prop;
	//public static WebDriver driver;
	public static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<>();
	
	@BeforeSuite(groups = { "Smoke", "Sanity", "Regression" })
	public void loadConfig() {
		
		ExtentManager.setExtent();
		DOMConfigurator.configure("log4j.xml");
		
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\Configuration\\config.properties");
			prop.load(ip);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
//    @BeforeTest(groups = { "Smoke", "Sanity", "Regression" })
//    public void loadConfig() {
//    	
//    	ExtentManager.setExtent();
//		DOMConfigurator.configure("log4j.xml");
//    	
//    	try {
//    		prop=new Properties();
//    		System.out.println("Super Constructer invoked");
//    		FileInputStream ip=new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\config.properties");
//    		prop.load(ip);
//    		System.out.println("driver:" +driver);
//    		
//    	} catch(FileNotFoundException e) {
//    		e.printStackTrace();
//    	} catch(IOException e) {
//    		e.printStackTrace();
//    	}
//    	
//    }
    
    public static WebDriver getDriver() {
		// Get Driver from threadLocalmap
		return driver.get();
	}
    
    //@Parameters("browser")
    public static void LaunchApp(String browserName) throws InterruptedException {
    	
    	//WebDriverManager.chromedriver().setup();
    	//String browserName=prop.getProperty("browser");
    	
    	
    	if(browserName.equalsIgnoreCase("Chrome")) {
    		WebDriverManager.chromedriver().setup();
    		driver.set(new ChromeDriver());
    	}else if(browserName.equalsIgnoreCase("FireFox")) {
    		//driver = new FirefoxDriver();
    		WebDriverManager.firefoxdriver().setup();
    		driver.set(new FirefoxDriver());
    	}else if(browserName.equalsIgnoreCase("IE")) {
    		//driver = new InternetExplorerDriver();
    		WebDriverManager.iedriver().setup();
    		driver.set(new InternetExplorerDriver());
    	}
    	else if(browserName.equalsIgnoreCase("Edge")) {
    		//driver = new InternetExplorerDriver();
    		WebDriverManager.iedriver().setup();
    		driver.set(new EdgeDriver());
    	}
    	getDriver().manage().window().maximize();
		//Delete all the cookies
		//getDriver().manage().deleteAllCookies();
		
    	//Action.implicitWait(driver,10);
    	//Action.pageLoadTimeOut(driver,30);
    	Thread.sleep(5000);
    	getDriver().get(prop.getProperty("url"));
    	
    }
    
//public static void LaunchApp(String browserName) throws InterruptedException {
//    	
//	        //String browserName = prop.getProperty("browser");
//			if (browserName.equalsIgnoreCase("Chrome")) {
//				WebDriverManager.chromedriver().setup();
//				// Set Browser to ThreadLocalMap
//				driver.set(new ChromeDriver());
//			} else if (browserName.equalsIgnoreCase("FireFox")) {
//				WebDriverManager.firefoxdriver().setup();
//				driver.set(new FirefoxDriver());
//			} else if (browserName.equalsIgnoreCase("IE")) {
//				WebDriverManager.iedriver().setup();
//				driver.set(new InternetExplorerDriver());
//			}
//			//Maximize the screen
//			getDriver().manage().window().maximize();
//			//Delete all the cookies
//			getDriver().manage().deleteAllCookies();
//			//Implicit TimeOuts
////			getDriver().manage().timeouts().implicitlyWait
////			(Integer.parseInt(prop.getProperty("implicitWait")),TimeUnit.SECONDS);
////			//PageLoad TimeOuts
////			getDriver().manage().timeouts().pageLoadTimeout
////			(Integer.parseInt(prop.getProperty("pageLoadTimeOut")),TimeUnit.SECONDS);
//			//Launching the URL
//			getDriver().get(prop.getProperty("url"));
//			Thread.sleep(10000);
//		}
    
    @AfterSuite(groups = { "Smoke", "Regression","Sanity" })
	public void afterSuite() {
		ExtentManager.endReport();
	}
    	
    }
   
    
	
	


/**
 * 
 */
package com.mystore.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.pageObjects.IndexPage;
import com.mystore.utility.Log;

/**
 * @author jagad
 *
 */
public class IndexPageTest extends BaseClass{
	IndexPage indexPage;
	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) throws InterruptedException {
		LaunchApp(browser);
		
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Smoke")
	public void verifyTitle() {
		Log.startTestCase("verifyTitle");
		String actTitle = indexPage.getMyStoreTitle();
		Assert.assertEquals(actTitle, "My Shop");
		System.out.println(actTitle);
		Log.startTestCase("verifyLogo");
	}
	
	@Test(groups = "Smoke")
	public void verifyLogo() throws Throwable {
		Log.startTestCase("verifyLogo");
		indexPage= new IndexPage();
		boolean result=indexPage.validateLogo();
		Assert.assertTrue(result);
		System.out.println(result);
		Log.endTestCase("verifyLogo");
	}
	
	

}

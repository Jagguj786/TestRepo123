package com.mystore.testcases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageObjects.HomePage;
import com.mystore.pageObjects.IndexPage;
import com.mystore.pageObjects.LoginPage;
import com.mystore.utility.Log;



public class HomePageTest extends BaseClass{
	
    private IndexPage indexPage;
	private LoginPage loginPage;
	private HomePage homePage;

	
	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) throws InterruptedException {
		LaunchApp(browser);
		
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Smoke",dataProvider="credentials", dataProviderClass=DataProviders.class)
	public void wishListTest(String uname, String pswd) throws Throwable {
		Log.startTestCase("wishListTest");
		indexPage= new IndexPage();
		loginPage=indexPage.clickOnSignIn();
		homePage=loginPage.login(uname,pswd,homePage);
		//homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"),homePage);
		
		String ExpText="Welcome to your account. Here you can manage all of your personal information and orders.";
		String ActText=homePage.getText();
		Assert.assertEquals(ExpText, ActText);
//		boolean result=homePage.validateMyWishList();
//		Assert.assertTrue(result);
		Log.endTestCase("wishListTest");
	}
	
	
	@Test(groups = "Smoke",dataProvider="credentials", dataProviderClass=DataProviders.class)
	public void orderHistoryandDetailsTest(String uname, String pswd) throws Throwable {
		Log.startTestCase("orderHistoryandDetailsTest");
		indexPage= new IndexPage();
		loginPage=indexPage.clickOnSignIn();
		homePage=loginPage.login(uname,pswd,homePage);
		//homePage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"),homePage);
		boolean result=homePage.validateOrderHistory();
		Assert.assertTrue(result);
//		Action act = new Action();
//		String TestName = null;
//	    act.screenShot(getDriver(), TestName);
	    Log.endTestCase("orderHistoryandDetailsTest");
	    
	}

}

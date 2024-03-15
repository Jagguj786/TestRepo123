package com.mystore.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageObjects.AddToCartPage;
import com.mystore.pageObjects.IndexPage;
import com.mystore.pageObjects.OrderPage;
import com.mystore.pageObjects.SearchResultPage;
import com.mystore.utility.Log;




public class OrderPageTest extends BaseClass{
	
	private IndexPage index;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private OrderPage orderPage;

	@Parameters("browser")
	@BeforeMethod(groups = {"Smoke","Sanity","Regression"})
	public void setup(String browser) throws InterruptedException {
		LaunchApp(browser);
		
	}
	
	@AfterMethod(groups = {"Smoke","Sanity","Regression"})
	public void tearDown() {
		getDriver().quit();
	}
	
	@Test(groups = "Regression",dataProvider = "getProduct", dataProviderClass = DataProviders.class)
	public void verifyTotalPrice(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("verifyTotalPrice");
		index= new IndexPage();
		searchResultPage=index.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		addToCartPage.selectSize(size);
		Thread.sleep(2000);
		addToCartPage.enterQuantity(qty);
		//addToCartPage.selectSize(size);
		//Thread.sleep(2000);
		addToCartPage.clickOnAddToCart();
		orderPage=addToCartPage.clickOnCheckOut();
		Double unitPrice=orderPage.getUnitPrice();
		Double totalPrice=orderPage.getTotalPrice();
		Double totalExpectedPrice=(unitPrice*(Double.parseDouble(qty)))+7;
		System.out.println(unitPrice);
		System.out.println(totalPrice);
		System.out.println(totalExpectedPrice);
		
		Thread.sleep(3000);
		Assert.assertEquals(totalPrice, totalExpectedPrice);
		Log.endTestCase("verifyTotalPrice");
	}

}

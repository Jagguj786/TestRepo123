package com.mystore.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageObjects.AddToCartPage;
import com.mystore.pageObjects.AddressPage;
import com.mystore.pageObjects.IndexPage;
import com.mystore.pageObjects.LoginPage;
import com.mystore.pageObjects.OrderConfirmationPage;
import com.mystore.pageObjects.OrderPage;
import com.mystore.pageObjects.OrderSummary;
import com.mystore.pageObjects.PaymentPage;
import com.mystore.pageObjects.SearchResultPage;
import com.mystore.pageObjects.ShippingPage;
import com.mystore.utility.Log;




public class EndToEndTest extends BaseClass{
	private IndexPage index;
	private SearchResultPage searchResultPage;
	private AddToCartPage addToCartPage;
	private OrderPage orderPage;
	private LoginPage loginPage;
	private AddressPage addressPage;
	private ShippingPage shippingPage;
	private PaymentPage paymentPage;
	private OrderSummary orderSummary;
	private OrderConfirmationPage orderConfirmationPage;

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
	public void endToEndTest(String productName, String qty, String size) throws Throwable {
		Log.startTestCase("endToEndTest");
		index= new IndexPage();
		searchResultPage=index.searchProduct(productName);
		addToCartPage=searchResultPage.clickOnProduct();
		addToCartPage.enterQuantity(qty);
		addToCartPage.selectSize(size);
		Thread.sleep(3000);
		addToCartPage.clickOnAddToCart();
		orderPage=addToCartPage.clickOnCheckOut();
		loginPage=orderPage.clickOnCheckOut();
		addressPage=loginPage.login(prop.getProperty("username"), prop.getProperty("password"),addressPage);
		shippingPage=addressPage.clickOnCheckOut();
		shippingPage.checkTheTerms();
		paymentPage=shippingPage.clickOnProceedToCheckOut();
		orderSummary=paymentPage.clickOnPaymentMethod();
		orderConfirmationPage=orderSummary.clickOnconfirmOrderBtn();
		String actualMessage=orderConfirmationPage.validateConfirmMessage();
		Thread.sleep(5000);
		String expectedMsg="Your order on My Shop is complete.";
		Assert.assertEquals(actualMessage, expectedMsg);
		Log.endTestCase("endToEndTest");
	}

}

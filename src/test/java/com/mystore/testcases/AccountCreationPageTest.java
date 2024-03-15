package com.mystore.testcases;
import java.util.HashMap;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.mystore.base.BaseClass;
import com.mystore.dataprovider.DataProviders;
import com.mystore.pageObjects.AccountCreationPage;
import com.mystore.pageObjects.HomePage;
import com.mystore.pageObjects.IndexPage;
import com.mystore.pageObjects.LoginPage;
import com.mystore.utility.Log;






public class AccountCreationPageTest extends BaseClass{
	private IndexPage indexPage;
	private LoginPage loginPage;
	private AccountCreationPage acountCreationPage;
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
	
	
	@Test(groups = "Sanity",dataProvider = "email", dataProviderClass = DataProviders.class)
	public void verifyCreateAccountPageTest(String email) throws Throwable {
		Log.startTestCase("verifyCreateAccountPageTest");
		indexPage= new IndexPage();
		loginPage=indexPage.clickOnSignIn();
		Thread.sleep(3000);
		acountCreationPage=loginPage.createNewAccount(email);
		Thread.sleep(3000);
		boolean result=acountCreationPage.validateAcountCreatePage();
		Thread.sleep(3000);
		System.out.println(result);
		Assert.assertTrue(result);
		Log.endTestCase("verifyCreateAccountPageTest");
	}
	
	@Test(groups = "Regression")
	public void createAccountTest1() throws Throwable {
		Log.startTestCase("createAccountTest1");
		indexPage= new IndexPage();
		loginPage=indexPage.clickOnSignIn();
		acountCreationPage=loginPage.createNewAccount("tt567654f8767.sa@gmail.com");
		Thread.sleep(3000);
		acountCreationPage.createAccount("Male", "Jagadeesh", "Malla", "Jaggu786@", "12", "12", "1995", "TCS", "Test Address", "YLM", "Andhra", "531055", "India", "8347597");
		Thread.sleep(5000);
		homePage=acountCreationPage.validateRegistration();
		System.out.println(getDriver().getCurrentUrl());
		Assert.assertEquals("http://www.automationpractice.pl/index.php?controller=my-account", homePage.getCurrURL());
		Thread.sleep(5000);
		Log.endTestCase("createAccountTest1");
	}
	
	//Maybe this is experimental Test
//	@Test(dataProvider = "newAcountDetailsData",dataProviderClass = DataProviders.class)
//	public void createAccountTest(HashMap<String,String> hashMapValue) throws Throwable {
//		Log.startTestCase("createAccountTest");
//		indexPage= new IndexPage();
//		loginPage=indexPage.clickOnSignIn();
//		acountCreationPage=loginPage.createNewAccount(hashMapValue.get("Email"));
//		acountCreationPage.createAccount(
//				hashMapValue.get("Gender"),
//				hashMapValue.get("FirstName"),
//				hashMapValue.get("LastName"),
//				hashMapValue.get("SetPassword"),
//				hashMapValue.get("Day"),
//				hashMapValue.get("Month"),
//				hashMapValue.get("Year"),
//				hashMapValue.get("Company"),
//				hashMapValue.get("Address"),
//				hashMapValue.get("City"),
//				hashMapValue.get("State"),
//				hashMapValue.get("Zipcode"),
//				hashMapValue.get("Country"),
//				hashMapValue.get("MobilePhone"));
//		homePage=acountCreationPage.validateRegistration();
//		Assert.assertEquals("http://automationpractice.com/index.php?controller=my-account", homePage.getCurrURL());
//		Log.endTestCase("createAccountTest");
//	}
	

}

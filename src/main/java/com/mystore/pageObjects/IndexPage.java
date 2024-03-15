/**
 * 
 */
package com.mystore.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

/**
 * @author jagad
 *
 */
public class IndexPage extends BaseClass{
	
	Action action= new Action();
	
	@FindBy(xpath = "//a[@class='login']") 
	private WebElement signInBtn; 
	
	@FindBy(xpath = "//img[@class='logo img-responsive']")
	private WebElement myStoreLogo;
	
	@FindBy(id="search_query_top")
	private WebElement searchProductBox;
	
	@FindBy(name="submit_search")
	private WebElement searchButton;
	
	
	public IndexPage() {
		
		PageFactory.initElements(getDriver(), this);
	}
	
	public String getMyStoreTitle() {
		String myStoreTitle=getDriver().getTitle();
		return myStoreTitle;
	}
	
	
	public SearchResultPage searchProduct(String productName) {
		action.type(searchProductBox, productName);
		action.click(getDriver(), searchButton);
		return new SearchResultPage();
		
	}
	public LoginPage clickOnSignIn() throws Throwable {
		action.fluentWait(getDriver(), signInBtn, 10);
		action.click(getDriver(), signInBtn);
		return new LoginPage();
	}
	
	public boolean validateLogo() throws Throwable {
		return action.isDisplayed(getDriver(), myStoreLogo);
	}
	
	
	
	

}



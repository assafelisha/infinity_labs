package com.ilad.pageobjecttopq;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends AbstractPageObject{
	
	
	@FindBy(id="userLogin")
	WebElement userLogin;
	@FindBy(id="password")
	WebElement password;
	@FindBy(id="ordLoginSubmitBtn")
	WebElement submitBtn;
	
	public LoginPage( WebDriver driver) {
		super(driver);
	}
	
	public void sendKeyToUserNameTb(String strUserName){
		userLogin.clear();
		userLogin.sendKeys(strUserName);
	}
	
	public void sendKeyToPasswordTb(String strPassword){
		password.clear();
		password.sendKeys(strPassword);
	}
	
	public OverviewPage clickOnSubmitBtn(){
		submitBtn.click();
		new WebDriverWait(driver, 15).until(ExpectedConditions.urlToBe("https://topq.teamwork.com/projects/145467/overview"));
		return new OverviewPage(driver);
	}
	
	public OverviewPage loginAndGoToOverviewPage(String strUserName, String strPassword){
		sendKeyToUserNameTb(strUserName);
		sendKeyToPasswordTb(strPassword);
		return clickOnSubmitBtn();
		
	}
	
}

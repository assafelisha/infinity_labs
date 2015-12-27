package com.ilad.pageobjecttopq;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class NewTaskModule extends AbstractPageObject{

	
	@FindBy(xpath="//form[@id='newTaskForm']//following::span/input[@placeholder]")
	WebElement newFirstTaskNameTextBox;
	
	@FindBy(xpath="//select[@name='taskAssignedToUserId']")
	WebElement dropDownPermissionFirstTask;
	
	@FindBy(xpath="//div[@id='editTaskFormFooter0']/input")
	WebElement saveMyChangesBtn;
	public NewTaskModule(WebDriver driver) {
		super(driver);
	}
	public void sendKeyToTaskName(String strFirstTaskName) throws InterruptedException{
		Thread.sleep(1000);
		newFirstTaskNameTextBox.sendKeys(strFirstTaskName);
	}
	
	public void chooseWhoShouldSeeThis(String strPermission){
		Select selectDropDownPermissionFirstTask = new Select(dropDownPermissionFirstTask);
		
		selectDropDownPermissionFirstTask.selectByVisibleText(strPermission);
	}
	
	public void clickOnSaveMyChangesBtn() throws InterruptedException{
		
		saveMyChangesBtn.click();
		Thread.sleep(1000);
	}
}

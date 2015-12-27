package com.ilad.pageobjecttopq;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewMileStoneModule extends AbstractPageObject{
	
	String listName;
	@FindBy(id="milestoneName")
	WebElement milestoneName;
	@FindBy(xpath="//input[@value='Add this milestone']")
	WebElement addThisMilestoneBtn;
	
	public AddNewMileStoneModule(WebDriver driver) {
		super(driver);
		
	}

	public void sendKeyToMilestormDescription(String strMilstoneName) {
		
		milestoneName.sendKeys(strMilstoneName);
	}

	public MilestonesPage clickOnAddThisMilestone() {
		addThisMilestoneBtn.click();
		return new MilestonesPage(driver,listName);
		
	}
	
}

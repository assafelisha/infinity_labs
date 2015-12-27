package com.ilad.pageobjecttopq;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class MilestonesPage extends AbstaractTeamWorkPage{

	String listName;
	String milstoneName;
	@FindBy(xpath="//*[@id='tab_tasks']//a")
	WebElement tabsSubMenuItem;
	@FindBy(xpath="//span[@class='btn-addIcon']/..")
	WebElement addAMilestoneBtn;
	@FindBy(id="milestoneName")
	WebElement milestoneName;
	@FindBy(xpath="//input[@value='Add this milestone']")
	WebElement addThisMilestoneBtn;
	@FindBy(linkText="Attach task list")
	WebElement attachTasklink;
	@FindBy(tagName="select")
	WebElement chooseATaskList;
	
	public MilestonesPage(WebDriver driver) {
		super(driver);
	}
	
	public MilestonesPage(WebDriver driver, String listName) {
		super(driver);
		this.listName = listName;
	}
	
	public void hoverOvertheMilstone(String strMilstoneName){
		
		WebElement linkToTheMilstone = driver.findElement(By.linkText(strMilstoneName));
		Actions builder = new Actions(driver);
		Actions hoverOverMilstone = builder.moveToElement(linkToTheMilstone);
		hoverOverMilstone.perform();
	}
	
	public void clickOnAttachTasklink(){
		attachTasklink.click();
	}
	
	public void clickOnChooseElement(String taskListName){
		chooseATaskList.click();
		Select selectItem = new Select(chooseATaskList);
		selectItem.selectByVisibleText(taskListName);
	}

	public void selectListToAttachToTheMilestone(String strMilstoneName, String taskListName) {
		listName = taskListName;
		hoverOvertheMilstone(strMilstoneName);
		clickOnAttachTasklink();
		clickOnChooseElement(taskListName);
	}
	
	public void clickOnListTagAndGoToTaskList(){
		WebElement myListTag = driver.findElement(By.xpath("//span[contains(.,'"+ listName +"')]"));
		myListTag.click();
		
	}

	public AddNewMileStoneModule clickOnAddMilestoneBtnAndAddNewMilestone() {
		addAMilestoneBtn.click();
		return new AddNewMileStoneModule(driver);
	}
	

}

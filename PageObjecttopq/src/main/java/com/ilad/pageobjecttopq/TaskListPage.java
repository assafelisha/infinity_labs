package com.ilad.pageobjecttopq;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TaskListPage extends AbstaractTeamWorkPage{

	String listName; 
	String milestoneName;
	
	@FindBy(xpath="//button[@class='btn btn-success js-add-task btn-lg']")
	WebElement btnAddTheFirstTask;
	
	@FindBy(xpath="//form[@id='newTaskForm']//following::span/input[@placeholder]")
	WebElement newFirstTaskNameTextBox;
	
	@FindBy(xpath="//select[@name='taskAssignedToUserId']")
	WebElement dropDownPermissionFirstTask;
	
	@FindBy(xpath="//div[@id='editTaskFormFooter0']/input")
	WebElement saveMyChangesBtn;
	
	
	@FindBy(xpath="//a[@class='cb']/img")
	WebElement taskVerify;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-hasIcon tipped']")
	WebElement optionsButton;
	
	@FindBy(xpath="//i[@class='ico-trash icon-large']/..")
	WebElement deleteButton;
	
	@FindBy(xpath="//p[@class='tasklistMilestone today']/a")
	WebElement linkOfMilestoneName;
	
	public TaskListPage(WebDriver driver) {
		super(driver);
	}
	
	public TaskListPage(WebDriver driver,String listName) {
		super(driver);
		this.listName = listName;
	}

	public void clickOnAddTaskBtn(){
		btnAddTheFirstTask.click();
	}
	
	public void sendKeyToTaskName(String strFirstTaskName){
		newFirstTaskNameTextBox.sendKeys(strFirstTaskName);
	}
	
	public void chooseWhoShouldSeeThis(String strPermission){
		Select selectDropDownPermissionFirstTask = new Select(dropDownPermissionFirstTask);
		selectDropDownPermissionFirstTask.deselectByVisibleText(strPermission);
	}
	
	public void clickOnSaveMyChangesBtn(){
		saveMyChangesBtn.click();
	}
	
	
	public void clickOnMyListTag(){
		WebElement myListTag = driver.findElement(By.xpath("//span[contains(.,'"+ listName +"')]"));
		myListTag.click();
	}
	
	public void addTaskToTheList(String strFirstTaskName,String strPermission) throws InterruptedException{
		Thread.sleep(1500);
		sendKeyToTaskName(strFirstTaskName);
		chooseWhoShouldSeeThis(strPermission);
		clickOnSaveMyChangesBtn();
		Thread.sleep(1500);
		
	}
	

	public boolean verifyThatTheListContainsTasks() {
		return taskVerify.isEnabled();
		
	}

	public void deleteTheTaskList() {
		optionsButton.click();
		deleteButton.click();
	}

	public boolean verifyTheTaskListIsAttachedToTheMilestone() {
		return linkOfMilestoneName.isEnabled();
	}

	public NewTaskModule clickOnAddTaskBtnAndGoToNewTaskModule() {
		btnAddTheFirstTask.click();
		return new NewTaskModule(driver);
	}
	
}

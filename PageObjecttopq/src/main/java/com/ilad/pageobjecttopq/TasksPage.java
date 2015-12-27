package com.ilad.pageobjecttopq;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;


public class TasksPage extends AbstaractTeamWorkPage{

	@FindBy(xpath = "//*[@id='liBFOATL']//button")
	WebElement addTasklistButton;
	@FindBy(id = "newTaskListName")
	WebElement newTaslListName;
	@FindBy(id = "btnCreateTaskList")
	WebElement btnCreateTasklist;
	

	public TasksPage(WebDriver driver) {
		super(driver);
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://topq.teamwork.com/projects/145467-webdriver-training/tasks");
	}

	public AddNewTaskListModule clickOnAddTaskListBtnAndAddNewTaskList() throws InterruptedException {
		Thread.sleep(1000);
		addTasklistButton.click();
		Thread.sleep(1000);
		return new AddNewTaskListModule(driver);
	}

	public TaskListPage clickOnTaskListWithNameAndGoToTaskListPage(String taskListName) throws InterruptedException {
		Thread.sleep(3000);
		WebElement myListTag = driver.findElement(By.linkText(taskListName));
		myListTag.click();
		return new TaskListPage(driver);
	}

	public int getNumberOfTasks(){
		
		List<WebElement> taskVerify = driver.findElements(By.xpath("//a[@class='cb']/img"));
		return taskVerify.size();
	}

}

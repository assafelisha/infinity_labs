package com.ilad.testpageobject;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ilad.pageobjecttopq.OverviewPage;
import com.ilad.pageobjecttopq.AddNewMileStoneModule;
import com.ilad.pageobjecttopq.AddNewTaskListModule;
import com.ilad.pageobjecttopq.LoginPage;
import com.ilad.pageobjecttopq.MilestonesPage;
import com.ilad.pageobjecttopq.NewTaskModule;
import com.ilad.pageobjecttopq.TaskListPage;
import com.ilad.pageobjecttopq.TasksPage;


public class TestPageObject {
	
	private WebDriver driver;
	private String userName = "fake02@fake.com";
	private String password = "fake";
	
	private String taskListNamePrefix = "My List";
	
	@BeforeMethod
	public void setUp() {
	//	System.setProperty("webdriver.chrome.driver", "/home/developer/drivers/chromedriver");
	//	driver = new ChromeDriver();
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://topq.teamwork.com/");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
	}
	
	@Test
	public void testPageObjectTopq() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		OverviewPage overviewPage = loginPage.loginAndGoToOverviewPage(userName, password);
		
		Reporter.log("And we have a task list In the system", true);
		
		TasksPage tasksPage = overviewPage.clickOnTasksSubMenuItemAndGoToTasksPage();
		
		AddNewTaskListModule addNewTaskListmodule = tasksPage.clickOnAddTaskListBtnAndAddNewTaskList();
		final String taskListName = taskListNamePrefix +" "+ System.currentTimeMillis();
		addNewTaskListmodule.sendKeyToTaskListName(taskListName);
		tasksPage = addNewTaskListmodule.clickOnAddThisTaskListBtnAndGoToTasksPage();
		
		Reporter.log("when the user add task to the system", true);
		TaskListPage taskListPage = tasksPage.clickOnTaskListWithNameAndGoToTaskListPage(taskListName);
		NewTaskModule newTaskmodule = taskListPage.clickOnAddTaskBtnAndGoToNewTaskModule();
		newTaskmodule.sendKeyToTaskName("Log In To topqteamwork.com");
		newTaskmodule.chooseWhoShouldSeeThis("Anyone");
		newTaskmodule.clickOnSaveMyChangesBtn();
		newTaskmodule.sendKeyToTaskName("Add New Task List");
		newTaskmodule.chooseWhoShouldSeeThis("Anyone");
		newTaskmodule.clickOnSaveMyChangesBtn();
		
		
		Reporter.log("Then the tasks are added successfully to the system", true);
        MilestonesPage milestonesPage = tasksPage.clickOnMilestonesubMenuItemAndGoToMilestonesPage();
        tasksPage = milestonesPage.clickOnTasksSubMenuItemAndGoToTasksPage();
        taskListPage = tasksPage.clickOnTaskListWithNameAndGoToTaskListPage(taskListName);
        Assert.assertEquals(tasksPage.getNumberOfTasks(), 2);
       
        taskListPage.deleteTheTaskList();
	}
	
	@Test(dependsOnMethods={"testPageObjectTopq"}, alwaysRun = true)
	public void testAddTaskListToMilstone() throws InterruptedException{
		LoginPage loginPage = new LoginPage(driver);
		OverviewPage overviewPage = loginPage.loginAndGoToOverviewPage(userName, password);
		
		Reporter.log("And we have a task list In the system", true);
		
		TasksPage tasksPage = overviewPage.clickOnTasksSubMenuItemAndGoToTasksPage();
		
		AddNewTaskListModule addNewTaskListmodule = tasksPage.clickOnAddTaskListBtnAndAddNewTaskList();
		final String taskListName = taskListNamePrefix +" "+ System.currentTimeMillis();
		addNewTaskListmodule.sendKeyToTaskListName(taskListName);
		tasksPage = addNewTaskListmodule.clickOnAddThisTaskListBtnAndGoToTasksPage();
		
		MilestonesPage mileStonePage = tasksPage.clickOnMilestonesubMenuItemAndGoToMilestonesPage();
		
		AddNewMileStoneModule addNewMileStoneModule = mileStonePage.clickOnAddMilestoneBtnAndAddNewMilestone();
		addNewMileStoneModule.sendKeyToMilestormDescription("This is a milestone");
		mileStonePage = addNewMileStoneModule.clickOnAddThisMilestone();
		mileStonePage.selectListToAttachToTheMilestone("This is a milestone",taskListName);
		mileStonePage.clickOnTasksSubMenuItemAndGoToTasksPage();
		
		
		TaskListPage taskListPage = tasksPage.clickOnTaskListWithNameAndGoToTaskListPage(taskListName);
		Assert.assertTrue(taskListPage.verifyTheTaskListIsAttachedToTheMilestone());
		
		taskListPage.deleteTheTaskList();
	}
}

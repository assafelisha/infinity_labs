package com.ilad.pageobjecttopq;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class AbstaractTeamWorkPage extends AbstractPageObject{

	@FindBy(xpath="//*[@id='tab_tasks']//a")
	WebElement tabsSubMenuItem;
	
	@FindBy(xpath="//li[@id='tab_milestones']/a")
	WebElement milstonesPage;
	
	public AbstaractTeamWorkPage(WebDriver driver) {
		super(driver);
	
	}
	
	public TasksPage clickOnTasksSubMenuItemAndGoToTasksPage() {
		tabsSubMenuItem.click();
		//TODO: Change the url to something that is more robust. Without the domain and the specific project name
		new WebDriverWait(driver, 15).until(ExpectedConditions.urlToBe("https://topq.teamwork.com/projects/145467-webdriver-training/tasks"));
		return new TasksPage(driver);
	}
	
	public MilestonesPage clickOnMilestonesubMenuItemAndGoToMilestonesPage() throws InterruptedException{
			Thread.sleep(1500);
			milstonesPage.click();
			return new MilestonesPage(driver);
		
	}
		
	
	
	

}

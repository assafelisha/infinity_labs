package com.ilad.pageobjecttopq;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class OverviewPage extends AbstaractTeamWorkPage{
	
	@FindBy(xpath="//*[@id='tab_tasks']//a")
	WebElement tabsSubMenuItem;
	
	public OverviewPage(WebDriver driver) {
		super(driver);
		String url = driver.getCurrentUrl();
		Assert.assertEquals(url, "https://topq.teamwork.com/projects/145467/overview");
	}
	
	
}

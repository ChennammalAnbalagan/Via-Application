package com.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.utility.DataUtility;

public class BasePage {
	
	WebDriver driver;
	
	@FindBy(id = "Flights")
	private WebElement flightsLink;
	
	@FindBy(id = "Hotels")
	private WebElement hotelsLink;
	
	@FindBy(id = "Holidays")
	private WebElement holidaysLink;
	
	@FindBy(id = "Bus")
	private WebElement busLink;
	
	@FindBy(id = "Cruise")
	private WebElement cruiseLink;
	
	@FindBy(id = "wzrk-cancel")
	private WebElement noThanksPopUp;
	
	public void handleNoThanksPopUpMsg() throws InterruptedException {
		noThanksPopUp.click();
		Thread.sleep(3000);
	}
	
	//constructor
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//creating object for DataUtility class
	DataUtility data_Utility = new DataUtility();
	
	Select select;
	Actions action;
	JavascriptExecutor javaScript;
	
	public void selectDropDownByValue(WebElement dropdown, String value) {
		select = new Select(dropdown);
		select.selectByValue(value);
	}

	public void selectDropDownByVisibleText(WebElement dropdown, String visibleText) {
		select = new Select(dropdown);
		select.selectByVisibleText(visibleText);
	}
	
	public void waitUntilElementIsClickAble(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void jsClick(WebDriver driver, WebElement element) {
		javaScript = (JavascriptExecutor) driver;
		javaScript.executeScript("arguments[0].click();", element);
	}
	
	public String captureScreenshotForReport(WebDriver driver) {

		TakesScreenshot ts = (TakesScreenshot) driver;

		return ts.getScreenshotAs(OutputType.BASE64);

	}
}

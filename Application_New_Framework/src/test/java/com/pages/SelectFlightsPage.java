package com.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelectFlightsPage extends BasePage{

	@FindBy(xpath = "(//div[@class='onwardResults']//button[@class='bookCTA u_marB5'])[1]")
	private WebElement depatureFlightSelectButton;
	
	@FindBy(xpath = "(//div[@class='returnResults']//button[@class='bookCTA u_marB5'])[1]")
	private WebElement returnFlightSelectButton;
	
	@FindBy(xpath = "//div[text()='Book Flights']")
	private WebElement bookFlightsButton;
	
	@FindBy(xpath = "(//div[@class='u_inlineblk u_width35 u_vertAlignMiddle']/button)[1]")
	private WebElement oneWayDepartureFlight;
	
	@FindBy(xpath = "//button[text()='Selected']")
	private WebElement selected;
	
	public String validateSelected() {
		return selected.getText();
	}
	
	public String validateSelect() {
		return depatureFlightSelectButton.getText();
	}
	
	//Constructors
	public SelectFlightsPage(WebDriver driver) {
		super(driver);
	}
	
	//create Explicit wait
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	//create object basePage class
	BasePage base_Page = new BasePage(driver);
	
	public void selectFlights() throws InterruptedException {
		//click on select button for departure flight
		Thread.sleep(3000);
		depatureFlightSelectButton.click();
		Thread.sleep(3000);
		
		//click on select for return flight
		wait.until(ExpectedConditions.elementToBeClickable(returnFlightSelectButton));
		returnFlightSelectButton.click();
		Thread.sleep(3000);
	}
	
	public void ClickOnBookFlights() throws InterruptedException {
		//click on book flights button
		bookFlightsButton.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.urlContains("https://in.via.com/flight/review/"));
	}
	
}

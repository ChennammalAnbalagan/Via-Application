package com.pages;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

public class FlightsPage extends BasePage{
	
	@FindBy(className = "one-way")
	private WebElement oneWayButton;
	
	@FindBy(xpath = "//label[contains(text(),'Round trip')]")
	private WebElement roundTripButton;
	
	@FindBy(id = "source")
	private WebElement fromTextField;
	
	@FindBy(xpath = "//span[text()='Bangalore,Bangalore - India']")
	private WebElement bangloreOption;

	@FindBy(id = "destination")
	private WebElement toTextField;
	
	@FindBy(xpath = "//span[text()='Chennai,Chennai - India']")
	private WebElement chennaiOption;
	
	@FindBy(id = "departure")
	private WebElement departureOptions;
	
	@FindBy(id = "return")
	private WebElement returnOptions;
	
	@FindBy(xpath = "//div[@data-for='adult']//div[@class='plus']")
	private WebElement adultsAddButton;
	
	@FindBy(xpath = "//div[@data-for='adult']//div[@class='minus']")
	private WebElement adultsMinusButton;
	
	@FindBy(xpath = "/div[@data-for='child']//div[@class='plus'")
	private WebElement childrenAddButton;
	
	@FindBy(xpath = "//div[@data-for='child']//div[@class='minus']")
	private WebElement childrenMinusButton;
	
	@FindBy(xpath = "//div[@data-for='infant']//div[@class='plus']")
	private WebElement infantsAddButton;
	
	@FindBy(xpath = "//div[@data-for='infant']//div[@class='minus']")
	private WebElement infantsMinusButton;
	
	@FindBy(partialLinkText = "Show More Options")
	private WebElement showMoreOptionsLink;
	
	@FindBy(id = "prefCarrier")
	private WebElement preferredAirlineDropDown;
	
	@FindBy(id = "preferredClass")
	private WebElement classDropDown;
	
	@FindBy(id = "routingType")
	private WebElement routingDropDown;
	
	@FindBy(linkText = "YOUR RECENT SEARCHES")
	private WebElement yourRecentSearchesLink;
	
	@FindBy(id = "search-flight-btn")
	private WebElement searchFlightsButton;
	
	@FindBy(xpath = "//input[@id='departure']")
	private WebElement calenderIcon1;
	
	@FindBy(xpath = "//input[@id='return']")
	private WebElement calenderIcon2;
	
	@FindBy(xpath = "//*[@id=\"depart-cal\"]/div[3]/div[2]/div[6]/div[6]")
	private WebElement calenderDateFlight1;
	
	@FindBy(xpath = "//*[@id=\"return-cal\"]/div[4]/div[2]/div[5]/div[4]")
	private WebElement calenderDateFlight2;
	
	//validation for toTextField
	public String validateFrom() {
		return toTextField.getAttribute("id");
	}
	
	//validation for calendar
	public String validateDate() {
		return calenderDateFlight1.getAttribute("id");
	}
	
	//validation for showMoreLink
	public WebElement validateRouting() {
		return routingDropDown;
	}
	
	//constructors
	public FlightsPage(WebDriver driver) {
		super(driver);
	}
	
	public void clickOneWayButton() {
		//click on oneWay Button
		oneWayButton.click();
	}
	
	public void clickRoundTripButton() {
		//click on roundTrip button
		roundTripButton.click();
	}
	
	//create Explicit wait
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	
	//create object for basePage class
	BasePage base_Page = new BasePage(driver);
	
	public void enterTextInFromAndToTextField() throws EncryptedDocumentException, IOException, InterruptedException {
		//Enter the value in "from" textField
		fromTextField.sendKeys(data_Utility.readDataFromExcel("TestData", 2, 1));
		driver.findElement(By.xpath("//span[text()='"+data_Utility.readDataFromExcel("TestData", 1, 1) +"']")).click();
		
		//Enter the value in "to" textField
		toTextField.sendKeys(data_Utility.readDataFromExcel("TestData", 4, 2));
		driver.findElement(By.xpath("//span[text()='"+data_Utility.readDataFromExcel("TestData", 3, 2) +"']")).click();
	}
	
	public void clickDepartureDate() throws EncryptedDocumentException, IOException, InterruptedException {
		//click on Departure date
		FlightsPage flights = new FlightsPage(driver);
		jsClick(driver, calenderIcon1);
		wait.until(ExpectedConditions.elementToBeClickable(calenderDateFlight1)).click();
	}
	
	public void clickReturnDate() throws InterruptedException {
		//click on return date
		FlightsPage flights = new FlightsPage(driver);
		Thread.sleep(3000);
		jsClick(driver, calenderIcon2);
		
		//click on that particular date
		wait.until(ExpectedConditions.elementToBeClickable(calenderDateFlight2)).click();
	}
	
	public void enterShowMoreDetails() throws EncryptedDocumentException, IOException {
		//click on show more options
		showMoreOptionsLink.click();
		
		//Enter the Preferred AirLine details
		preferredAirlineDropDown.sendKeys(data_Utility.readDataFromExcel("TestData", 7, 1));
		
		//Enter the Class details
		wait.until(ExpectedConditions.elementToBeClickable(classDropDown)).click();
		base_Page.selectDropDownByValue(classDropDown, data_Utility.readDataFromExcel("TestData", 7, 2));
		
		//Enter the routing details
		wait.until(ExpectedConditions.elementToBeClickable(routingDropDown)).click();
		base_Page.selectDropDownByValue(routingDropDown, data_Utility.readDataFromExcel("TestData", 7, 3));
		wait.until(ExpectedConditions.elementToBeClickable(searchFlightsButton));
	}
	
	public void clickOnSearchButton() throws InterruptedException {
		//click on search button
		searchFlightsButton.click();
		Thread.sleep(3000);
	}
	
	public void selectFlightDepartureDate(WebDriver driver,String month, String day) {
		driver.findElement(By.xpath("//div[@id='depart-cal']//div[@data-month="+month+"]//div[@class='vc-row'][6]//div[@data-date="+day+"]")).click();
	}
	
	public void selectFlightReturnDate(WebDriver driver,String month, String day) {
		driver.findElement(By.xpath("//div[@id='return-cal']//div[@data-month="+month+"]//div[@class='vc-row'][6]//div[@data-date="+day+"]")).click();
	}

}	

package com.pages;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PassengersDetails extends BasePage{
	
	@FindBy(id ="adult1Title")
	private WebElement chooseTitle;

	@FindBy(id = "adult1FirstName")
	private WebElement firstNameTextField;

	@FindBy(id = "adult1Surname")
	private WebElement lastNameTextField;

	@FindBy(id = "contactMobile")
	private WebElement mobileNumberTextField;

	@FindBy(id = "contactEmail")
	private WebElement emailTextfield;

	@FindBy(id = "makePayCTA")
	private WebElement proceedToBookingButton;
	
	@FindBy(xpath = "//label[@for='refundProtectRadTrue']")
	private WebElement refundableClickOption;
	
	@FindBy(id = "confirmProceedPayBtn")
	private WebElement makePaymentButton;
	
	@FindBy(xpath = "//span[text()='REFUNDABLE BOOKING']")
	private WebElement refundable;
	
	@FindBy(xpath = "//span[text()='Make Payment']")
	private WebElement payment;
	
	//constructors
	public PassengersDetails(WebDriver driver) {
		super(driver);
	}
	
	public WebElement validatePassengersDetails() {
		return chooseTitle;
	}
	
	//create Explicit wait
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
	//create object basePage class
	BasePage base_Page = new BasePage(driver);

	public void enterPassengersDetails() throws EncryptedDocumentException, IOException, InterruptedException {
		//click on title
		Thread.sleep(4000);
		chooseTitle.click();
		
		//select the option from dropDown
		chooseTitle.sendKeys(data_Utility.readDataFromExcel("TestData", 8, 1));
		
		//click on firstName
		firstNameTextField.sendKeys(data_Utility.readDataFromExcel("TestData", 8, 2));
		
		//click on lastName
		lastNameTextField.sendKeys(data_Utility.readDataFromExcel("TestData", 8, 3));
		
		//click on mobile number
		String phno = data_Utility.readDataFromExcel("TestData", 8, 4);
		String phoneNumber = data_Utility.readDataFromExcel("TestData", 8, 4);

		//Mobile number is stored in form of long number so use big decimal to get mobile number
		BigDecimal decimal = new BigDecimal(phoneNumber);  // Ensure the number is treated as a full precision decimal
		String originalPhoneNumber = decimal.toPlainString();  // Get the number as a plain string (no scientific notation)
		mobileNumberTextField.sendKeys(originalPhoneNumber);
		
		//click on email
		emailTextfield.sendKeys(data_Utility.readDataFromExcel("TestData", 8, 5));
	}
	
	public String clickOnProceedToBooking() throws InterruptedException {
		//click on proceed to booking
		proceedToBookingButton.click();
		Thread.sleep(3000);
		return refundable.getText();
	}
	
	public WebElement clickOnRefundableBooking() throws InterruptedException {
		//click on refundableOption
		Thread.sleep(3000);
		jsClick(driver, refundableClickOption);
		return refundableClickOption;
	}
	
	public String clickOnMakePaymentButton() throws InterruptedException {
		//click on make payment button
		jsClick(driver, makePaymentButton);
		Thread.sleep(3000);
		return payment.getText();
	}
	
}

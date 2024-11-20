package com.testcases;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.pages.BasePage;
import com.pages.FlightsPage;
import com.pages.PassengersDetails;
import com.pages.SelectFlightsPage;
import com.setup.BaseTest;



public class Flights extends BaseTest{
	
	BasePage base_Page;
	FlightsPage flights;
	SelectFlightsPage select_Flights;
	PassengersDetails details;
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Navigating to home page, validated home page is displayed and popUp message is handled.
	 */
	
	@Test(priority = 1)
	public void Verify_user_is_able_to_view_flights_after_navigating_to_homePage() throws InterruptedException, EncryptedDocumentException, IOException {
		
		test = report.createTest("Flights Test Cases");
		
		base_Page = new BasePage(driver);
		
		Assert.assertEquals(driver.getTitle(),(data_Utility.readDataFromExcel("PageTitle", 1, 1)));
		Reporter.log("Navigated to Home Page",true);
		test.log(Status.PASS, "Navigated to Home Page");

		base_Page.handleNoThanksPopUpMsg();
		Reporter.log("No thanks message is handled", true);
		test.log(Status.INFO, "No thanks message is handled");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "Home Page");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user can successfully fill flights details in "From and To TextField" after navigating to the homePage.
	 */
	
	@Test(priority = 2)
	public void Verify_user_is_able_to_enter_location_in_from_and_to_textfield() throws EncryptedDocumentException, IOException, InterruptedException {
		
		flights = new FlightsPage(driver);
		
		flights.enterTextInFromAndToTextField();
		Reporter.log("Entered from and to key is displayed",true);
		test.log(Status.INFO, "Entered from and to key is Displayed");
		
		Assert.assertEquals(flights.validateFrom(), data_Utility.readDataFromExcel("TestData", 9, 1));
		Reporter.log("Entered fromToTextField value is displayed", true);
		test.log(Status.PASS, "Entered fromToTextField value is displayed");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "FROM and TO Location displays");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user can successfully fill flights details in "Departure Date" after navigating to the homePage.
	 */
	
	@Test(priority = 3)
	public void Verify_user_is_able_to_click_on_options_in_depature() throws EncryptedDocumentException, IOException, InterruptedException {
		
		flights.clickDepartureDate();
		Reporter.log("Departure date is displayed",true);
		test.log(Status.INFO, "Departure date is displayed");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "DEPARTURE date is selected");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user can successfully fill flights details in "Departure and Return Date" after navigating to the homePage.
	 * @throws InterruptedException 
	 */
	
	@Test(priority = 4)
	public void Verify_user_is_able_to_click_on_options_in_depature_and_return() throws InterruptedException {

		flights.clickReturnDate();
		Reporter.log("Return date is displayed",true);
		test.log(Status.INFO, "Return date is displayed");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "DEPARTURE and RETURN is selected");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user is able to click on the "Show More Options" link successfully.
	 */
	
	@Test(priority = 5)
	public void Verify_user_is_able_to_click_on_show_more_options_Link() throws EncryptedDocumentException, IOException {
		
		flights.enterShowMoreDetails();
		Reporter.log("Show more options is displayed",true);
		test.log(Status.INFO, "Show more options is displayed");
		
		String selectedLocation = (flights.validateRouting()).getAttribute("value");
		Assert.assertEquals(selectedLocation, data_Utility.readDataFromExcel("TestData", 7, 3));
		Reporter.log("Routing dropdown is verified",true);
		test.log(Status.PASS, "Routing dropdown is verified");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "SHOW MORE OPTIONS is displayed");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user is able to click on the "Search Flights Button" link successfully.
	 */
	
	@Test(priority = 6)
	public void Verify_user_is_able_to_click_on_Search_Flights_button() throws InterruptedException {
		
		flights.clickOnSearchButton();
		Reporter.log("Search flights button is clicked",true);
		test.log(Status.INFO, "Search flights button is clicked");

		Assert.assertTrue(driver.getCurrentUrl().contains("search"));
		Reporter.log("Navigated to lists of flights page",true);
		test.log(Status.PASS, "Navigated to lists of flights page");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "Lists of flights page is shown");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user is able to select a specific flight for "Departure and Return" from the search results.
	 */
	
	@Test(priority = 7)
	public void Verify_user_is_able_to_select_particular_flight_from_the_searched_result() throws InterruptedException {
		
		select_Flights = new SelectFlightsPage(driver);
		
		select_Flights.selectFlights();
		Reporter.log("Departure and return flights is selected",true);
		test.log(Status.INFO, "Departure and return flights is selected");
		
		Assert.assertEquals(select_Flights.validateSelected(), select_Flights.validateSelect());
		Reporter.log("selectFlights is validated",true);
		test.log(Status.PASS, "selectFlights is validated");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "DEPARTURE and RETURN flights is selected");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user can successfully click on the "Book Flights" button.
	 */
	
	@Test(priority = 8)
	public void Verify_user_is_able_to_click_on_book_Flights_button() throws InterruptedException {
		
		select_Flights.ClickOnBookFlights();
		Reporter.log("Book flights button is clicked",true);
		test.log(Status.INFO, "Book flights button is clicked");

		Assert.assertTrue(driver.getCurrentUrl().contains("https://in.via.com/flight/review/"));
		Reporter.log("Navigated to Passengers details page",true);
		test.log(Status.PASS, "Navigated to Passengers details page");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "Passengers details page is displayes");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user is able to enter passenger details successfully.
	 */
	
	@Test(priority = 9)
	public void Verify_user_is_able_to_enter_passengers_details() throws EncryptedDocumentException, IOException, InterruptedException {
		
		details = new PassengersDetails(driver);
		
		details.enterPassengersDetails();
		Reporter.log("Entered Passengers details is displayed",true);
		test.log(Status.INFO, "Entered Passengers details is displayed");
		
		String selectedLocation = (details.validatePassengersDetails()).getAttribute("value");
		Assert.assertEquals(selectedLocation, data_Utility.readDataFromExcel("TestData", 8, 1));
		Reporter.log("Passengers details is verified",true);
		test.log(Status.PASS, "Passengers details is verified");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "Passengers details is shows");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user can successfully click on the "Proceed to Booking" button.
	 */
	
	@Test(priority = 10)
	public void Verify_user_is_able_to_click_on_proceed_to_booking() throws InterruptedException {
		
		String result = details.clickOnProceedToBooking();
		Reporter.log("clicked on proceed to booking button",true);
		test.log(Status.INFO, "clicked on proceed to booking button");	

		Assert.assertEquals(result, "REFUNDABLE BOOKING");
		Reporter.log("Navigated to refundable page",true);
		test.log(Status.PASS, "Navigated to refundable page");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "Refundable booking page is displays");
		
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user is able to select either the "Refundable" or "Non-Refundable" option.
	 */
	
	@Test(priority = 11)
	public void Verify_user_is_able_to_able_to_click_on_refundable_or_non_refundable_options() throws InterruptedException {
		
		details.clickOnRefundableBooking();
		
		Reporter.log("clicked on refundable radio button",true);
		test.log(Status.INFO, "clicked on refundable radio button");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "Refundable option is selected");
	}
	
	/**
	 * 
	 * Created By : A Chennammal
	 * Reviewed By : 
	 * Test Scenario : Verify that the user is able to successfully book a round-trip flight.
	 */
	
	@Test(priority = 12)
	public void Verify_user_is_able_to_book_round_trip() throws InterruptedException {
		
		String result = details.clickOnMakePaymentButton();
		Reporter.log("clicked on make payment button",true);
		test.log(Status.INFO, "clicked on make payment button");

		Assert.assertEquals(result, "Make Payment");
		Reporter.log("Navigated to payment page", true);
		test.log(Status.PASS, "Navigated to payment page");
		
		test.addScreenCaptureFromBase64String(base_Page.captureScreenshotForReport(driver), "Payment page is displayes");
	}
}

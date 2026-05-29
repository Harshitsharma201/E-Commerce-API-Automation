package com.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.CreateBookingService;
import com.api.models.request.AuthRequest;
import com.api.models.request.CreateBookingRequest;
import com.api.models.response.AuthResponse;
import com.api.utilities.DataProviders;

import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class DataProviderTest {

	private String token;
	
	@BeforeClass()
	public void setUpAuth() {
		AuthService authService=new AuthService();
		
		AuthRequest authRequest=new AuthRequest("admin","password123");
		Response response=authService.auth(authRequest);
		AuthResponse authResponse=response.as(AuthResponse.class);
		
		this.token=authResponse.getToken();
		
		System.out.print(token);
	}
	@Test(dataProvider = "bookingDataProvider", dataProviderClass = DataProviders.class)
	public void verifyCreateBookingDataProvider(String fName, String lName, int price, 
            boolean deposit, String checkin, String checkout, String needs) {
		
		CreateBookingRequest.BookingDates dates = new CreateBookingRequest.BookingDates(checkin, checkout);
        CreateBookingRequest payload = new CreateBookingRequest(fName, lName, price, deposit, dates, needs);
		CreateBookingService createBookingService= new CreateBookingService();
		Response response=createBookingService.createBooking(payload,token);
		
		Assert.assertEquals(response.statusCode(), 200);
		response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/create-booking-schema.json"));
		
		System.out.println("✅ Schema contract validation passed perfectly for row dataset!");
		
	}
}

package com.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.CreateBookingService;
import com.api.base.UpdateBookingService;
import com.api.models.request.AuthRequest;
import com.api.models.request.CreateBookingRequest;
import com.api.models.request.UpdateBookingRequest;
import com.api.models.response.AuthResponse;
import com.api.models.response.CreateBookingResponse;
import com.api.models.response.UpdateBookingResponse;

import io.restassured.response.Response;

public class UpdateBookingTest {
	
	private int id;
	private String token;
	
	@BeforeMethod
	public void extractValues() {
		AuthService authService=new AuthService();
		AuthRequest request=new AuthRequest("admin","password123");
		Response response=authService.auth(request);
		
		AuthResponse authResponse=response.as(AuthResponse.class);
		this.token=authResponse.getToken();
		
		CreateBookingRequest.BookingDates initialDates = new CreateBookingRequest.BookingDates("2026-06-01", "2026-06-15");
		CreateBookingRequest setupPayload = new CreateBookingRequest("Setup", "User", 450, true, initialDates, "Breakfast");
		
		CreateBookingService createBookingService = new CreateBookingService();
		Response createResponse = createBookingService.createBooking(setupPayload, this.token);
		CreateBookingResponse bookingResponse = createResponse.as(CreateBookingResponse.class);
		this.id = bookingResponse.getId();
		
		System.out.println(">>> Prerequisite setup complete. Token: " + token + " | Booking ID: " + id);
		System.out.println("----------------------------------------------------------------------");
		
	}

	@Test
	public void updateBooking() {
		
		UpdateBookingRequest.BookingDates updatedDates = new UpdateBookingRequest.BookingDates("2026-06-01", "2026-06-15");
		UpdateBookingRequest payload = new UpdateBookingRequest(
				"Rahul",
				"Sharma",
				600,         // Modified Price (Updated from 450 to 600)
				true,
				updatedDates,
				"Lunch"      // Modified Amenities (Updated from Breakfast to Lunch)
		);
		
		UpdateBookingService updateBookingService=new UpdateBookingService();
		Response response=updateBookingService.update(payload, token, id);
		
		UpdateBookingResponse updateBookingResponse=response.as(UpdateBookingResponse.class);
		
		System.out.println(updateBookingResponse.getTotalprice());
		Assert.assertEquals(response.statusCode(), 200);
		
		
	}
}

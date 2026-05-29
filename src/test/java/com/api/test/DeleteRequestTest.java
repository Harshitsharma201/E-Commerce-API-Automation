package com.api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.CreateBookingService;
import com.api.base.DeleteBookingService;
import com.api.models.request.AuthRequest;
import com.api.models.request.CreateBookingRequest;
import com.api.models.response.AuthResponse;
import com.api.models.response.CreateBookingResponse;

import io.restassured.response.Response;

public class DeleteRequestTest {

	
	private int id;
	private String token;
	
	@BeforeMethod
	public void setupTestData() {
		System.out.println(">>> Setting up prerequisite data for independent Delete execution...");
		
		// 1. Generate active session authentication token
		AuthService authService = new AuthService();
		AuthRequest request = new AuthRequest("admin", "password123");
		Response response = authService.auth(request);
		AuthResponse authResponse = response.as(AuthResponse.class);
		this.token = authResponse.getToken();
		
		// 2. Create a temporary booking slot to acquire a target ID context
		CreateBookingRequest.BookingDates initialDates = new CreateBookingRequest.BookingDates("2026-06-01", "2026-06-15");
		CreateBookingRequest setupPayload = new CreateBookingRequest("Delete", "Target", 200, true, initialDates, "None");
		
		CreateBookingService createBookingService = new CreateBookingService();
		Response createResponse = createBookingService.createBooking(setupPayload, this.token);
		CreateBookingResponse bookingResponse = createResponse.as(CreateBookingResponse.class);
		this.id = bookingResponse.getId();
		
		System.out.println(">>> Target Setup Verified. Token: " + token + " | Deleting Room ID: " + id);
		System.out.println("----------------------------------------------------------------------");
	}

	@Test
	public void deleteBooking() {
		// 3. Trigger our clean service execution step
		DeleteBookingService deleteBookingService = new DeleteBookingService();
		Response response = deleteBookingService.delete(id,token);
		
		System.out.println("DELETE HTTP Status Code: " + response.statusCode());
		System.out.println("DELETE Raw Response Text: " + response.asString());
		
		// 4. Assertions: Restful-Booker returns a "221 Created" text body status on successful deletes!
		// However, it returns an HTTP status code 201 Created. Let's assert on the code:
		Assert.assertEquals(response.statusCode(), 201, "Server rejected the deletion sequence request!");
	}
}

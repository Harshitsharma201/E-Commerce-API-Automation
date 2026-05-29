package com.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.CreateBookingService;
import com.api.models.request.AuthRequest;
import com.api.models.request.CreateBookingRequest;
import com.api.models.response.AuthResponse;
import com.api.models.response.CreateBookingResponse;

import io.restassured.response.Response;

public class CreateBookingTest {

	public static String token;
	public static int id;

	@Test
	public void bookingTest() {
		AuthService authService=new AuthService();
		AuthRequest authRequest=new AuthRequest("admin","password123");
		
		Response response=authService.auth(authRequest);
		AuthResponse authResponse=response.as(AuthResponse.class);
		
		token=authResponse.getToken();
	
		// 1. Initialize the nested inner class parameters first
		CreateBookingRequest.BookingDates dates = new CreateBookingRequest.BookingDates("2026-06-01", "2026-06-15");

		// 2. Load all variables directly into your main class constructor
		CreateBookingRequest payload = new CreateBookingRequest(
		    "Rahul",      // firstname (String)
		    "Sharma",     // lastname (String)
		    450,          // totalprice (int)
		    true,         // depositpaid (boolean)
		    dates,        // bookingdates (Inner Class Object)
		    "Breakfast"   // additionalneeds (String)
		);
		CreateBookingService createBookingService=new CreateBookingService();
		Response responseOfCreateBookingRequest=createBookingService.createBooking(payload,token);

		CreateBookingResponse bookingResponse=responseOfCreateBookingRequest.as(CreateBookingResponse.class);
		
		id=bookingResponse.getId();
				
		System.out.println("The booking id is "+ bookingResponse.getId());
		
		System.out.println(responseOfCreateBookingRequest.asPrettyString());
		
		
	}
}

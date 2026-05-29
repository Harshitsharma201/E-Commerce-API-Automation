package com.api.base;

import com.api.models.request.CreateBookingRequest;

import io.restassured.response.Response;

public class CreateBookingService extends Base{

	public Response createBooking(CreateBookingRequest payload,String token) {
		
		String endpoint="/booking";
		setAuthToken(token);
		return postRequest(payload,endpoint);
	}
}

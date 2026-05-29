package com.api.base;

import com.api.models.request.UpdateBookingRequest;

import io.restassured.response.Response;

public class UpdateBookingService extends Base {

	public Response update(UpdateBookingRequest payload,String token,int id) {
		String endpoint="/booking/" + id;
		setCookieAuth(token);
		return putRequest(payload,endpoint);
	}
	
}

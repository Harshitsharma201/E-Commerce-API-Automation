package com.api.base;

import io.restassured.response.Response;

public class DeleteBookingService extends Base{
	
	public Response delete(int id,String token) {
		String endpoint="/booking/"+id;
		
		setCookieAuth(token);
		return deleteRequest(endpoint);
	}
}


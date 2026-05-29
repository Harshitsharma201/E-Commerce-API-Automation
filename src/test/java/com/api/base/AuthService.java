package com.api.base;

import com.api.models.request.AuthRequest;

import io.restassured.response.Response;

public class AuthService extends Base {
	
	public Response auth(AuthRequest payload) {
		String endpoint="/auth";

		return postRequest(payload,endpoint);
	}

	
}

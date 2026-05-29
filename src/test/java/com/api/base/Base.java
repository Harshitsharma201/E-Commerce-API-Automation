package com.api.base;

import com.api.filters.LoggingFilter;
import com.api.models.request.AuthRequest;
import com.api.utilities.ConfigReader;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Base {

	private static final String BASE_URI=ConfigReader.getProperty("base.url");
	
	private RequestSpecification requestSpecification;

	static {
        RestAssured.filters(new LoggingFilter());
    }
	public Base() {
		
		this.requestSpecification = RestAssured.given().baseUri(BASE_URI);
	}
	
	protected void setAuthToken(String token) {
		requestSpecification.header("Authorization","Bearer "+token);
	}
	
	// 🌟 ADDED: Restful-Booker specific cookie injection rule
		protected void setCookieAuth(String token) {
			requestSpecification.header("Cookie", "token=" + token);
		}
		
	public Response postRequest(Object payload,String endpoint) {
		return requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
	}
	
	public Response putRequest(Object payload,String endpoint) {
		return requestSpecification.contentType(ContentType.JSON).body(payload).put(endpoint);
	}

	public Response deleteRequest(String endpoint) {
		return requestSpecification.delete(endpoint);
	}

		
}

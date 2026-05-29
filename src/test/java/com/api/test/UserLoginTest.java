package com.api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.AuthRequest;
import com.api.models.response.AuthResponse;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserLoginTest {
	
	@Test
	public void loginTest() {
		
//		Response response = RestAssured.given()
//				.baseUri("https://restful-booker.herokuapp.com")
//				.header("Content-Type", "application/json") // 🌟 Capital 'T'
//				.body("{\"username\": \"admin\", \"password\": \"password123\"}")
//				.post("auth"); // 🌟 Capital 'L'
		AuthRequest authRequest=new AuthRequest("admin","password123");
		AuthService authService=new AuthService();
		Response response=authService.auth(authRequest);
		
		AuthResponse authResponse=response.as(AuthResponse.class);
		System.out.println("Status Code: " + response.statusCode());
		System.out.println("Response Body: " + authResponse.getToken());

		Assert.assertEquals(response.statusCode(), 200);
	}
}
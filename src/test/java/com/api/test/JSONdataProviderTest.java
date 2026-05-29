package com.api.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.CreateBookingService;
import com.api.models.request.AuthRequest;
import com.api.models.request.CreateBookingRequest;
import com.api.models.response.AuthResponse;
import com.api.utilities.DataProviders;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.restassured.response.Response;

public class JSONdataProviderTest {

	private String token;
	@BeforeClass()
	public void setupAuth() {
		AuthService authService=new AuthService();
		AuthRequest request=new AuthRequest("admin","password123");
		Response response=authService.auth(request);
		
		AuthResponse authResponse=response.as(AuthResponse.class);
		
		this.token=authResponse.getToken();
	}
	
	@Test(dataProvider = "bookingJsonProvider", dataProviderClass = DataProviders.class)
	public void verifyCreateBookingDataProvider(Map<String, Object> testData) {
	    
	    // 🌟 2. Extract nested objects inside the map to build your BookingDates POJO
	    CreateBookingRequest.BookingDates dates = new CreateBookingRequest.BookingDates(
	        testData.get("checkin").toString(), 
	        testData.get("checkout").toString()
	    );
	    
	    // 🌟 3. Extract the rest of the fields directly from the Map payload object
	    CreateBookingRequest payload = new CreateBookingRequest(
	        testData.get("firstname").toString(),
	        testData.get("lastname").toString(),
	        (Integer) testData.get("totalprice"),  // Casting to Integer
	        (Boolean) testData.get("depositpaid"), // Casting to Boolean
	        dates,
	        testData.get("additionalneeds").toString()
	    );
	    
	    // 4. Fire the API call using your existing core services
	    CreateBookingService createBookingService = new CreateBookingService();
	    Response response = createBookingService.createBooking(payload, token);
	    
	    // 5. Build assertions
	    Assert.assertEquals(response.statusCode(), 200);
	    response.then().assertThat().body(matchesJsonSchemaInClasspath("schemas/create-booking-schema.json"));
	    
	    System.out.println("✅ Pro JSON engine executed test successfully for user: " + testData.get("firstname"));
	}
	
	
}
